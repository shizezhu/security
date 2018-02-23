package cn.szz.security.dao;

import java.util.Collection;
import java.util.List;

import cn.szz.security.pojo.po.RoleMenu;

/**
 * 角色与菜单关系Dao
 *
 * @author Shi Zezhu
 * @date 2017年9月16日 下午3:45:56
 *
 */
public interface RoleMenuDao {

	/**
	 * 根据角色id和菜单id获取
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午7:20:52
	 */
	RoleMenu get(long roleId, long menuId);
	
	/**
	 * 根据角色id获取
	 * 
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<RoleMenu> listByRoleId(long roleId);

	/**
	 * 根据角色id获取菜单id
	 * 
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<Long> listMenuIdByRoleId(long roleId);
	
	/**
	 * 根据角色id获取菜单id
	 * 
	 * @param roleIds
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<Long> listMenuIdByRoleId(Collection<Long> roleIds);

	/**
	 * 根据角色id删除
	 * 
	 * @param roleId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int delByRoleId(long roleId);

	/**
	 * 根据菜单id删除
	 * 
	 * @param menuId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int delByMenuId(long menuId);
	
	/**
	 * 根据菜单id删除
	 * 
	 * @param menuIds
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int delByMenuId(Collection<Long> menuIds);

	/**
	 * 根据角色id和菜单id删除
	 * 
	 * @param roleId
	 * @param menuId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int del(long roleId, long menuId);
	
	/**
	 * 保存
	 * 
	 * @param roleMenu
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(RoleMenu roleMenu);
	
	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:54
	 */
	int delById(long id);
	
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:10:38
	 */
	RoleMenu getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param roleMenu
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(RoleMenu roleMenu);
}
