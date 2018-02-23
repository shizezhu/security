package cn.szz.security.dao.db;

import java.util.List;

import cn.szz.security.pojo.po.Role;

/**
 * 角色mybatis映射方法
 *
 * @author Shi Zezhu
 * @date 2018年2月1日 下午3:00:40
 *
 */
public interface RoleDb extends BaseDb<Role> {

	/**
	 * 根据用户id获取
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 下午4:59:16
	 */
	List<Role> listByUserId(long userId);
}
