package cn.szz.sdk.rmi.client;

import java.util.List;
import java.util.Map;

public interface SecurityHessianClient {
	
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月18日 下午8:09:18
	 */
	Map<String, Object> getUserByUsername(String username);
	
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param username
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月18日 下午8:09:18
	 */
	Map<String, Object> getUserById(long userId);
	
	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 上午11:12:02
	 */
	List<Map<String, Object>> listUserAll();
	
	/**
	 * 获取超级用户
	 * 
	 * @param username
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 上午10:32:08
	 */
	Map<String, Object> getSuperuser(String username);
	
	/**
	 * 根据项目id和用户id获取授权标识
	 * 
	 * @param projectId
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月22日 下午3:29:53
	 */
	List<String> listAuthIdenByProjectIdAndUserId(long projectId, long userId);
	
	/**
	 * 根据项目id获取授权标识
	 * 
	 * @param projectId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 上午10:34:50
	 */
	List<String> listAuthIdenByProjectId(long projectId);
	
	/**
	 * 根据项目ID获取权限链
	 * 
	 * @param projectId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 下午12:13:48
	 */
	Map<String, String> mapFilterChainByProjectId(long projectId);
	
	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param userId
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, long userId, String userIp, String name, String descr, int type, String content);
	
	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, String userIp, String name, String descr, int type, String content);
	

	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param userId
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, long userId, String name, String descr, int type, String content);
	

	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param userId
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, long userId, String userIp, String name, String descr, int type);
	

	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, String name, String descr, int type, String content);
	

	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param userId
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, long userId, String name, String descr, int type);
	
	/**
	 * 保存
	 * 
	 * @param projectId
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, String userIp, String name, String descr, int type);

	/**
	 * 保存日志
	 * 
	 * @param projectId
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(long projectId, String name, String descr, int type);
}
