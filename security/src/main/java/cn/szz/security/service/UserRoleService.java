package cn.szz.security.service;

import java.util.List;
import java.util.Map;

import cn.szz.security.pojo.po.UserRole;

/**
 * 用户与角色关系Service
 * 
 * @author Shi Zezhu
 * @date 2017年8月29日 下午4:21:01
 */
public interface UserRoleService {

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
	 * @date 2017年10月13日 下午5:30:57
	 */
	List<Long> listUserIdByRoleId(long roleId);
	
	/**
	 * 根据用户id获取角色id
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:29:02
	 */
	Map<Long, Long> mapRoleIdByUserId(String userId);
	
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
	 * 保存
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月2日 下午7:16:36
	 */
	UserRole save(long userId, long roleId);
	
	/**
	 * 更新
	 * 
	 * @param userId
	 * @param roleIdArray
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午4:25:45
	 */
	void update(long userId, String[] roleIdArray);
}
