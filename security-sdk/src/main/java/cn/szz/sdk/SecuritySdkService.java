package cn.szz.sdk;

import java.util.List;

import cn.szz.sdk.entity.SecuritySdkUser;
import cn.szz.sdk.rmi.client.SecurityHessianClient;
import cn.szz.sdk.utils.LogType;

/**
 * sdk服务类
 *
 * @author Shi Zezhu
 * @date 2018年2月3日 下午5:25:49
 *
 */
public interface SecuritySdkService {

	/**
	 * 获取当前用户
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月26日 下午1:53:46
	 */
	SecuritySdkUser getCurrentUser();
	
	/**
	 * 根据ID获取用户
	 * 
	 * @param usernuserIdame
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 上午10:47:29
	 */
	SecuritySdkUser getUserById(long userId);
	
	/**
	 * 获取所有用户
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 上午10:50:54
	 */
	List<SecuritySdkUser> listUserAll();
	
	/**
	 * 保存日志
	 * 
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(String userIp, String name, String descr, LogType type, String content);
	

	/**
	 * 保存日志
	 * 
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(String name, String descr, LogType type, String content);

	/**
	 * 保存日志
	 * 
	 * @param userIp
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(String userIp, String name, String descr, LogType type);

	/**
	 * 保存日志
	 * 
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void saveLog(String name, String descr, LogType type);
	
	/**
	 * 获取security远程服务
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 下午2:56:08
	 */
	SecurityHessianClient getSecurityHessianClient();

	/**
	 * 获取项目ID
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 下午2:58:15
	 */
	long getProjectId();
}
