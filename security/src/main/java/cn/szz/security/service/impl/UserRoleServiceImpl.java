package cn.szz.security.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.szz.security.dao.UserRoleDao;
import cn.szz.security.pojo.po.UserRole;
import cn.szz.security.service.ReloadAuthFilterChainsService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.CommUtils;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private ReloadAuthFilterChainsService reloadAuthFilterChainsService;
	
	@Override
	@Transactional(readOnly = true)
	public List<UserRole> listByUserId(long userId) {
		return userRoleDao.listByUserId(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> listRoleIdByUserId(long userId) {
		return userRoleDao.listRoleIdByUserId(userId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Long> listUserIdByRoleId(long roleId) {
		return userRoleDao.listUserIdByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Long, Long> mapRoleIdByUserId(String userId) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		if (CheckUtils.isNotId(userId)) {
			return new HashMap<Long, Long>();
		}
		List<Long> list = this.listRoleIdByUserId(Long.parseLong(userId));
		list.forEach(roleId -> map.put(roleId, roleId));
		return map;
	}

	@Override
	@Transactional(readOnly = false)
	public int delByUserId(long userId) {
		return userRoleDao.delByUserId(userId);
	}

	@Override
	@Transactional(readOnly = false)
	public int delByRoleId(long roleId) {
		return userRoleDao.delByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public UserRole save(long userId, long roleId) {
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleDao.save(userRole);
		return userRole;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void update(long userId, String[] roleIdArray) {
		Set<Long> newRoleIdSet = CommUtils.toIdSet(roleIdArray);
		Set<Long> oldRoleIdSet = new HashSet<Long>(this.listRoleIdByUserId(userId));
		Set<Long> addRoleIdSet = CommUtils.getDi(newRoleIdSet, oldRoleIdSet);
		Set<Long> removeRoleIdSet = CommUtils.getDi(oldRoleIdSet, newRoleIdSet);
		addRoleIdSet.forEach(roleId -> this.save(userId, roleId));
		removeRoleIdSet.forEach(roleId -> userRoleDao.del(userId, roleId));
		reloadAuthFilterChainsService.clearAllAuthorizationCache();
	}
}
