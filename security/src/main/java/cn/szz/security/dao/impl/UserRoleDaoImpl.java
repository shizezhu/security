package cn.szz.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.UserRoleDao;
import cn.szz.security.dao.db.UserRoleDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

	@Resource
	private UserRoleDb userRoleDb;
	
	@Override
	public UserRole get(long userId, long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		return userRoleDb.get(new MybatisParams(params));
	}

	@Override
	public List<UserRole> listByUserId(long userId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userId);
		return userRoleDb.list(new MybatisParams(params));
	}

	@Override
	public List<Long> listRoleIdByUserId(long userId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userId);
		return userRoleDb.listRoleId(new MybatisParams(params));
	}
	
	@Override
	public List<Long> listUserIdByRoleId(long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		return userRoleDb.listUserId(new MybatisParams(params));
	}

	@Override
	public int delByUserId(long userId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userId);
		return userRoleDb.del(new MybatisParams(params));
	}

	@Override
	public int delByRoleId(long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		return userRoleDb.del(new MybatisParams(params));
	}

	@Override
	public int del(long userId, long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		return userRoleDb.del(new MybatisParams(params));
	}

	@Override
	public int save(UserRole userRole) {
		return userRoleDb.save(userRole);
	}

	@Override
	public int delById(long id) {
		return userRoleDb.delById(id);
	}

	@Override
	public UserRole getById(long id) {
		return userRoleDb.getById(id);
	}

	@Override
	public int update(UserRole userRole) {
		return userRoleDb.update(userRole);
	}
}
