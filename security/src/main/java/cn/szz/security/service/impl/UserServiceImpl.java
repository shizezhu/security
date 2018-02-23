package cn.szz.security.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.utils.LogType;
import cn.szz.security.dao.UserDao;
import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.MailService;
import cn.szz.security.service.RoleService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.service.UserService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;

	@Resource
	private MailService mailService;
	
	@Resource
	private SecuritySdkService securitySdkService;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> listAll() {
		return userDao.listAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> listByStatus(boolean status) {
		return userDao.listByStatus(status);
	}

	@Override
	@Transactional(readOnly = true)
	public User getById(String id) {
		if (CheckUtils.isNotId(id)) {
			return null;
		}
		return this.getById(Long.parseLong(id));
	}

	@Override
	@Transactional(readOnly = true)
	public User getById(long id) {
		return userDao.getById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getByUsername(String username) {
		return userDao.getByUsername(CheckUtils.checkNotEmpty(username, ErrorCode.U_UN_EMPTY));
	}

	@Override
	@Transactional(readOnly = true)
	public AjaxResult smLogin(String username, String password) throws ResultException {
		username = CheckUtils.checkNotEmpty(username, ErrorCode.U_UN_EMPTY);
		password = this.md5Password(CheckUtils.checkNotEmpty(password, ErrorCode.U_PW_EMPTY), username);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username, password));
		} catch (UnknownAccountException e) {
			throw new ResultException(ErrorCode.U_UN_NOT_EXISTENCE);
		} catch (DisabledAccountException e) {
			throw new ResultException(ErrorCode.U_LO_DISABLE);
		} catch (AuthenticationException e) {
			throw new ResultException(ErrorCode.U_PW_ERROR);
		}
		return new AjaxResult(ErrorCode.SUCCESS);
	}

	@Override
	@Transactional(readOnly = true)
	public PageData getByPage(PageData pageData) {
		if (CheckUtils.isNull(pageData)) {
			return new PageData(0, new ArrayList<User>());
		}
		pageData.setRows(userDao.listRows(pageData));
		pageData.setTotal(userDao.getTotal(pageData));
		return pageData;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult save(Map<String, String> params, String[] roleIdArray) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		String username = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("username"), ErrorCode.U_UN_EMPTY), CheckUtils.U_UN_PATTERN, ErrorCode.U_UN_FORMAT);
		String realname = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("realname"), ErrorCode.U_RN_EMPTY), CheckUtils.U_RN_PATTERN, ErrorCode.U_RN_FORMAT);
		String phone = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("phone"), ErrorCode.U_PH_EMPTY), CheckUtils.U_PH_PATTERN, ErrorCode.U_PH_ERROR);
		String email = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("email"), ErrorCode.U_EM_EMPTY), CheckUtils.U_EM_PATTERN, ErrorCode.U_EM_ERROR);
		boolean status = CheckUtils.checkStatus(params.get("status"));
		CheckUtils.checkNull(userDao.getByUsername(username), ErrorCode.U_UN_EXISTENCE);
		String password = this.createPassword();
		User user = new User();
		user.setUsername(username);
		user.setPassword(this.md5Password(password, username));
		user.setRealname(realname);
		user.setPhone(phone);
		user.setEmail(email);
		user.setStatus(status);
		user.setAddTime(LocalDateTime.now());
		user.setModifyTime(LocalDateTime.now());
		userDao.save(user);
		userRoleService.update(user.getId(), roleIdArray);
		try {
			mailService.sendMail(new InternetAddress(email, realname, "utf-8"), "初始登录密码", false, "您的初始登录密码为:" + password);
		} catch (MessagingException | IOException e) {
			throw new ResultException(ErrorCode.EM_SEND_ERROR);
		}
		user.setRoleList(roleService.listByUserId(user.getId()));
		securitySdkService.saveLog("用户", "保存用户", LogType.SAVE, user.toString());
		return new AjaxResult(ErrorCode.SUCCESS, user);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult update(Map<String, String> params, String[] roleIdArray) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		User user = CheckUtils.checkNotNull(this.getById(CheckUtils.checkId(params.get("id"))), ErrorCode.U_NOT_EXISTENCE);
		String username = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("username"), ErrorCode.U_UN_EMPTY), CheckUtils.U_UN_PATTERN, ErrorCode.U_UN_FORMAT);
		String realname = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("realname"), ErrorCode.U_RN_EMPTY), CheckUtils.U_RN_PATTERN, ErrorCode.U_RN_FORMAT);
		String phone = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("phone"), ErrorCode.U_PH_EMPTY), CheckUtils.U_PH_PATTERN, ErrorCode.U_PH_ERROR);
		String email = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("email"), ErrorCode.U_EM_EMPTY), CheckUtils.U_EM_PATTERN, ErrorCode.U_EM_ERROR);
		boolean status = CheckUtils.checkStatus(params.get("status"));
		if (CheckUtils.isNotEquals(username, user.getUsername())) {
			CheckUtils.checkNull(userDao.getByUsername(username), ErrorCode.U_UN_EXISTENCE);
		}
		user.setUsername(username);
		user.setRealname(realname);
		user.setPhone(phone);
		user.setEmail(email);
		user.setStatus(status);
		user.setModifyTime(LocalDateTime.now());
		userDao.update(user);
		userRoleService.update(user.getId(), roleIdArray);
		user.setRoleList(roleService.listByUserId(user.getId()));
		securitySdkService.saveLog("用户", "修改用户信息", LogType.UPDATE, user.toString());
		return new AjaxResult(ErrorCode.SUCCESS, user);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult updatePassword(Map<String, String> params) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);;
		User user = CheckUtils.checkNotNull(this.getById(securitySdkService.getCurrentUser().getId()), ErrorCode.U_NOT_EXISTENCE);
		String oldPassword = CheckUtils.checkNotEmpty(params.get("oldPassword"), ErrorCode.U_OPW_EMPTY);
		String newPassword = CheckUtils.checkNotEmpty(params.get("newPassword"), ErrorCode.U_NPW_EMPTY);
		CheckUtils.checkEquals(this.md5Password(oldPassword, user.getUsername()), user.getPassword(), ErrorCode.U_OPW_ERROR);
		CheckUtils.checkReg(newPassword, CheckUtils.U_PW_PATTERN, ErrorCode.U_NPW_FORMAT);
		user.setPassword(this.md5Password(newPassword, user.getUsername()));
		user.setModifyTime(LocalDateTime.now());
		userDao.update(user);
		securitySdkService.saveLog("用户", "修改用户密码", LogType.UPDATE, user.toString());
		return new AjaxResult(ErrorCode.SUCCESS);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult resetPassword(String id) throws ResultException {
		User user = CheckUtils.checkNotNull(this.getById(CheckUtils.checkId(id)), ErrorCode.U_NOT_EXISTENCE);
		String password = this.createPassword();
		user.setPassword(this.md5Password(password, user.getUsername()));
		user.setModifyTime(LocalDateTime.now());
		userDao.update(user);
		try {
			mailService.sendMail(new InternetAddress(user.getEmail(), user.getRealname(), "utf-8"), "重置登录密码", false, "您的重置登录密码为:" + password);
		} catch (MessagingException | IOException e) {
			throw new ResultException(ErrorCode.EM_SEND_ERROR);
		}
		securitySdkService.saveLog("用户", "重置用户密码", LogType.UPDATE, user.toString());
		return new AjaxResult(ErrorCode.SUCCESS);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(String id) throws ResultException {
		return this.del(CheckUtils.checkId(id));
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(long id) {
		userDao.delById(id);
		userRoleService.delByUserId(id);
		securitySdkService.saveLog("用户", "删除用户", LogType.DELETE, String.valueOf(id));
		return new AjaxResult(ErrorCode.SUCCESS);
	}
	
	private String md5Password(String password, String salt) {
		return new SimpleHash("MD5", password, salt).toString();
	}
	
	private String createPassword() {
		return RandomStringUtils.random(6, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789*");
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listById(Collection<Long> ids) {
		if (CheckUtils.isEmpty(ids)) {
			return new ArrayList<User>();
		}
		return userDao.listById(ids);
	}
}
