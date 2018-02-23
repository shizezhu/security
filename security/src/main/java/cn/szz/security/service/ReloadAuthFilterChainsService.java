package cn.szz.security.service;

import cn.szz.security.pojo.po.Project;

/**
 * 刷新权限链
 *
 * @author Shi Zezhu
 * @date 2018年1月20日 下午4:23:50
 *
 */
public interface ReloadAuthFilterChainsService {

	/**
	 * 刷新权限链
	 * 
	 * @param project
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月20日 下午4:23:33
	 */
	void reloadFilterChainDefinitions(Project project);
	
	/**
	 * 清除所有授权
	 * 
	 * @param project
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月23日 上午9:52:02
	 */
	void clearAllAuthorizationCache();
}
