package cn.szz.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.szz.security.dao.RoleMenuDao;
import cn.szz.security.pojo.po.RoleMenu;
import cn.szz.security.service.ReloadAuthFilterChainsService;
import cn.szz.security.service.RoleMenuService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.service.UserService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.CommUtils;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	
	@Resource
	private RoleMenuDao roleMenuDao;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ReloadAuthFilterChainsService reloadAuthFilterChainsService;

	@Override
	@Transactional(readOnly = true)
	public List<RoleMenu> listByRoleId(long roleId) {
		return roleMenuDao.listByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> listMenuIdByRoleId(String roleId) {
		if (CheckUtils.isNotId(roleId)) {
			return new ArrayList<Long>();
		}
		return this.listMenuIdByRoleId(Long.parseLong(roleId));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Long> listMenuIdByRoleId(long roleId) {
		return roleMenuDao.listMenuIdByRoleId(roleId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Long> listMenuIdByRoleId(Collection<Long> roleIds) {
		if (CheckUtils.isEmpty(roleIds)) {
			return new ArrayList<Long>();
		}
		return roleMenuDao.listMenuIdByRoleId(roleIds);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, Long> mapMenuIdByRoleId(String roleId) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		if (CheckUtils.isNotId(roleId)) {
			return map;
		}
		List<Long> list = this.listMenuIdByRoleId(Long.parseLong(roleId));
		list.forEach(menuId -> map.put(menuId, menuId));
		return map;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delByRoleId(long roleId) {
		return roleMenuDao.delByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delByMenuId(long menuId) {
		return roleMenuDao.delByMenuId(menuId);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delByMenuId(Collection<Long> menuIds) {
		return roleMenuDao.delByMenuId(menuIds);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public RoleMenu save(long roleId, long menuId) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenu.setMenuId(menuId);
		roleMenuDao.save(roleMenu);
		return roleMenu;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void update(long roleId, String menuIds) {
		Set<Long> newMenuIdSet = CommUtils.toIdSet(menuIds, ",");
		Set<Long> oldMenuIdSet = new HashSet<Long>(this.listMenuIdByRoleId(roleId));
		Set<Long> addMenuIdSet = CommUtils.getDi(newMenuIdSet, oldMenuIdSet);
		Set<Long> removeMenuIdSet = CommUtils.getDi(oldMenuIdSet, newMenuIdSet);
		addMenuIdSet.forEach(menuId -> this.save(roleId, menuId));
		removeMenuIdSet.forEach(menuId -> roleMenuDao.del(roleId, menuId));
		reloadAuthFilterChainsService.clearAllAuthorizationCache();
	}
}
