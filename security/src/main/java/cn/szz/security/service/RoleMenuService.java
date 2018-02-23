package cn.szz.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.szz.security.pojo.po.RoleMenu;

/**
 * 角色与菜单关系Service
 *
 * @author Shi Zezhu
 * @date 2017年9月16日 下午3:48:24
 *
 */
public interface RoleMenuService {

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
	List<Long> listMenuIdByRoleId(String roleId);
	
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
	 * 根据角色id获取菜单id
	 * 
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	Map<Long, Long> mapMenuIdByRoleId(String roleId);
	
	/**
	 * 根据角色id删除
	 * 
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月16日 下午3:51:19
	 */
	int delByRoleId(long roleId);
	
	/**
	 * 根据菜单id删除
	 * 
	 * @param menuId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月16日 下午3:51:32
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
	 * 保存
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月2日 下午7:16:36
	 */
	RoleMenu save(long roleId, long menuId);
	
	/**
	 * 更新
	 * 
	 * @param roleId
	 * @param menuIds
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:25:45
	 */
	void update(long roleId, String menuIds);
}
