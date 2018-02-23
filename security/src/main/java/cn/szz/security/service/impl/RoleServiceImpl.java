package cn.szz.security.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.utils.LogType;
import cn.szz.security.dao.RoleDao;
import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.RoleMenuService;
import cn.szz.security.service.RoleService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@Resource
	private SecuritySdkService securitySdkService;

	@Override
	@Transactional(readOnly = true)
	public List<Role> listAll() {
		return roleDao.listAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> listByUserId(long userId) {
		return roleDao.listByUserId(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public Role getById(String id) {
		if (CheckUtils.isNotId(id)) {
			return null;
		}
		return this.getById(Long.parseLong(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Role getById(long id) {
		return roleDao.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public PageData getByPage(PageData pageData) {
		if (CheckUtils.isNull(pageData)) {
			return new PageData(0, new ArrayList<Role>());
		}
		pageData.setRows(roleDao.listRows(pageData));
		pageData.setTotal(roleDao.getTotal(pageData));
		return pageData;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult save(Map<String, String> params, String menuIds) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		String name = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.R_NA_EMPTY), CheckUtils.R_NA_PATTERN, ErrorCode.R_NA_FORMAT);
		String descr = CheckUtils.ifEmpty(params.get("descr"), "").trim();
		CheckUtils.checkNull(roleDao.getByName(name), ErrorCode.R_NA_EXISTENCE);
		Role role = new Role();
		role.setName(name);
		role.setDescr(descr);
		role.setAddTime(LocalDateTime.now());
		role.setModifyTime(LocalDateTime.now());
		roleDao.save(role);
		roleMenuService.update(role.getId(), menuIds);
		securitySdkService.saveLog("角色", "保存角色", LogType.SAVE, role.toString());
		return new AjaxResult(ErrorCode.SUCCESS, role);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult update(Map<String, String> params, String menuIds) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		Role role = CheckUtils.checkNotNull(this.getById(CheckUtils.checkId(params.get("id"))), ErrorCode.R_NOT_EXISTENCE);
		String name = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.R_NA_EMPTY), CheckUtils.R_NA_PATTERN, ErrorCode.R_NA_FORMAT);
		String descr = CheckUtils.ifEmpty(params.get("descr"), "").trim();
		if (CheckUtils.isNotEquals(role.getName(), name)) {
			CheckUtils.checkNull(roleDao.getByName(name), ErrorCode.R_NA_EXISTENCE);
		}
		role.setName(name);
		role.setDescr(descr);
		role.setModifyTime(LocalDateTime.now());
		roleDao.update(role);
		roleMenuService.update(role.getId(), menuIds);
		securitySdkService.saveLog("角色", "修改角色", LogType.UPDATE, role.toString());
		return new AjaxResult(ErrorCode.SUCCESS, role);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(String id) throws ResultException {
		return this.del(CheckUtils.checkId(id));
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(long id) {
		roleDao.delById(id);
		userRoleService.delByRoleId(id);
		roleMenuService.delByRoleId(id);
		securitySdkService.saveLog("角色", "删除角色", LogType.DELETE, String.valueOf(id));
		return new AjaxResult(ErrorCode.SUCCESS);
	}
}
