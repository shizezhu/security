package cn.szz.security.service;

import cn.szz.security.pojo.vo.PageData;

/**
 * 日志
 *
 * @author Shi Zezhu
 * @date 2018年2月3日 上午10:36:44
 *
 */
public interface LogService {

	/**
	 * 保存
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
	void save(long projectId, long userId, String userIp, String name, String descr, int type, String content);
	
	/**
	 * 保存
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
	void save(long projectId, String userIp, String name, String descr, int type, String content);
	
	/**
	 * 保存
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
	void save(long projectId, long userId, String name, String descr, int type, String content);

	/**
	 * 保存
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
	void save(long projectId, long userId, String userIp, String name, String descr, int type);

	/**
	 * 保存
	 * 
	 * @param projectId
	 * @param name
	 * @param descr
	 * @param type
	 * @param content
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void save(long projectId, String name, String descr, int type, String content);

	/**
	 * 保存
	 * 
	 * @param projectId
	 * @param userId
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void save(long projectId, long userId, String name, String descr, int type);
	
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
	void save(long projectId, String userIp, String name, String descr, int type);
	
	/**
	 * 保存
	 * 
	 * @param projectId
	 * @param name
	 * @param descr
	 * @param type
	 * @author Shi Zezhu
	 * @date 2018年2月2日 下午6:46:53
	 */
	void save(long projectId, String name, String descr, int type);

	/**
	 * 分页获取
	 * 
	 * @param projectId
	 * @param type
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月5日 下午2:44:40
	 */
	PageData getByPage(String projectId, String type, PageData pageData);
}
