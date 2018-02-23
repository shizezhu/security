package cn.szz.security.dao.db;

import java.util.List;

import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.RoleMenu;

/**
 * 角色与菜单mybatis映射方法
 *
 * @author Shi Zezhu
 * @date 2018年2月1日 下午3:00:40
 *
 */
public interface RoleMenuDb extends BaseDb<RoleMenu> {

	/**
	 * 获取角色ID
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午2:54:32
	 */
	List<Long> listRoleId(MybatisParams mybatisParams);
	
	/**
	 * 获取菜单ID
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午2:54:32
	 */
	List<Long> listMenuId(MybatisParams mybatisParams);
}
