package cn.szz.sdk.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.utils.CheckUtils;


/**
 * 自定义ShiroFilterFactory
 *
 * @author Shi Zezhu
 * @date 2017年9月22日 下午7:27:17
 *
 */
public class ShiroFilterFactory extends ShiroFilterFactoryBean {
	
	private static final Logger logger = Logger.getLogger(ShiroFilterFactory.class);
	
	/** 记录配置中的过滤链. */
	public static String definition = "";
	
	private SecuritySdkService securitySdkService;

	public ShiroFilterFactory() {
		super();
	}

	public ShiroFilterFactory(SecuritySdkService securitySdkService) {
		super();
		this.securitySdkService = securitySdkService;
	}

	@Override
	public void setFilterChainDefinitions(String definitions) {
		definition = definitions;//记录配置的静态过滤链
		Ini ini = new Ini();
		ini.load(definitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		//加载数据库过滤链,并放在配置的前面
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		Map<String, String> filterChain = new HashMap<String, String>();
		try {
			filterChain = securitySdkService.getSecurityHessianClient().mapFilterChainByProjectId(securitySdkService.getProjectId());
		} catch (Exception e) {
			logger.error("Remote access permission data exception", e);
		}
		if (CheckUtils.isNotEmpty(filterChain)) {
			for (Entry<String, String> entry : filterChain.entrySet()) {
				filterChainDefinitionMap.put(entry.getKey(), entry.getValue());
			}
		}
		filterChainDefinitionMap.putAll(section);
		logger.info("permission data : " + filterChainDefinitionMap);
		setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
	
	/**
	 * 重新加载权限链
	 * 
	 * @throws Exception
	 * @author Shi Zezhu
	 * @date 2018年1月23日 上午9:54:29
	 */
	public void reloadFilterChainDefinitions() throws Exception {
		synchronized (this) {
			 AbstractShiroFilter shiroFilter = (AbstractShiroFilter) this.getObject();
			 PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
			 // 过滤管理器.
			 DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
			 // 清除权限配置.
			 manager.getFilterChains().clear();
			 this.getFilterChainDefinitionMap().clear();
			 // 重新设置权限.
			 this.setFilterChainDefinitions(definition);//传入配置中的filterchains
			 Map<String, String> chains = this.getFilterChainDefinitionMap();
			 //重新生成过滤链.
			 if (!CollectionUtils.isEmpty(chains)) {
				 for (Entry<String, String> entry : chains.entrySet()) {
					 manager.createChain(entry.getKey(), entry.getValue().trim().replace(" ", ""));
				 }
			 }
		 }
	}

	public SecuritySdkService getSecuritySdkService() {
		return securitySdkService;
	}

	public void setSecuritySdkService(SecuritySdkService securitySdkService) {
		this.securitySdkService = securitySdkService;
	}
}
