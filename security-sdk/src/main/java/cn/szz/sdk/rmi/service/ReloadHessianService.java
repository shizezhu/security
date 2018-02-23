package cn.szz.sdk.rmi.service;

/**
 * 刷新数据服务
 *
 * @author Shi Zezhu
 * @date 2018年1月23日 上午10:00:32
 *
 */
public interface ReloadHessianService {

	/**
	 * 重新加载权限链
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 下午5:05:55
	 */
	boolean reloadFilterChainDefinitions();
	
	/**
	 * 重新加载授权
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月23日 上午9:49:31
	 */
	boolean clearAllAuthorizationCache();
	
}
