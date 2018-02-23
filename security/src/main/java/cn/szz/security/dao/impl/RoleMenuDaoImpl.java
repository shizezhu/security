package cn.szz.security.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.RoleMenuDao;
import cn.szz.security.dao.db.RoleMenuDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.RoleMenu;

@Repository
public class RoleMenuDaoImpl implements RoleMenuDao {

	@Resource
	private RoleMenuDb roleMenuDb;
	
	@Override
	public RoleMenu get(long roleId, long menuId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		params.put("menuId", menuId);
		return roleMenuDb.get(new MybatisParams(params));
	}

	@Override
	public List<RoleMenu> listByRoleId(long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		return roleMenuDb.list(new MybatisParams(params));
	}

	@Override
	public List<Long> listMenuIdByRoleId(long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		return roleMenuDb.listMenuId(new MybatisParams(params));
	}
	
	@Override
	public List<Long> listMenuIdByRoleId(Collection<Long> roleIds) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("roleIds", roleIds);
		return roleMenuDb.listMenuId(new MybatisParams(params));
	}

	@Override
	public int delByRoleId(long roleId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		return roleMenuDb.del(new MybatisParams(params));
	}

	@Override
	public int delByMenuId(long menuId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("menuId", menuId);
		return roleMenuDb.del(new MybatisParams(params));
	}
	
	@Override
	public int delByMenuId(Collection<Long> menuIds) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("menuIds", menuIds);
		return roleMenuDb.del(new MybatisParams(params));
	}

	@Override
	public int del(long roleId, long menuId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("roleId", roleId);
		params.put("menuId", menuId);
		return roleMenuDb.del(new MybatisParams(params));
	}

	@Override
	public int save(RoleMenu roleMenu) {
		return roleMenuDb.save(roleMenu);
	}

	@Override
	public int delById(long id) {
		return roleMenuDb.delById(id);
	}

	@Override
	public RoleMenu getById(long id) {
		return roleMenuDb.getById(id);
	}

	@Override
	public int update(RoleMenu roleMenu) {
		return roleMenuDb.update(roleMenu);
	}
}
