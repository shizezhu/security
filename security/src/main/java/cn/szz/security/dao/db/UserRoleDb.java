package cn.szz.security.dao.db;

import java.util.List;

import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.UserRole;

/**
 * 用户与角色mybatis映射方法
 *
 * @author Shi Zezhu
 * @date 2018年2月1日 下午3:00:40
 *
 */
public interface UserRoleDb extends BaseDb<UserRole> {

	/**
	 * 获取用户ID
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午2:54:32
	 */
	List<Long> listUserId(MybatisParams mybatisParams);
	
	/**
	 * 获取角色ID
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午2:54:32
	 */
	List<Long> listRoleId(MybatisParams mybatisParams);
}
