package cn.szz.security.dao;

import java.util.List;

import cn.szz.security.pojo.po.UserRole;

/**
 * 用户与角色关系Dao
 * 
 * @author Shi Zezhu
 * @date 2017年8月29日 下午4:20:31
 */
public interface UserRoleDao {

	/**
	 * 根据用户id和角色id获取
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午7:20:52
	 */
	UserRole get(long userId, long roleId);
	
	/**
	 * 根据用户id获取
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<UserRole> listByUserId(long userId);

	/**
	 * 根据用户id获取角色id
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<Long> listRoleIdByUserId(long userId);
	
	/**
	 * 根据角色id获取用户id
	 * 
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	List<Long> listUserIdByRoleId(long roleId);

	/**
	 * 根据用户id删除
	 * 
	 * @param userId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int delByUserId(long userId);

	/**
	 * 根据角色id删除
	 * 
	 * @param roleId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int delByRoleId(long roleId);

	/**
	 * 根据用户id和角色id删除
	 * 
	 * @param userId
	 * @param roleId
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:34:26
	 */
	int del(long userId, long roleId);
	/**
	 * 保存
	 * 
	 * @param userRole
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(UserRole userRole);
	
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
	UserRole getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param userRole
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(UserRole userRole);
}
