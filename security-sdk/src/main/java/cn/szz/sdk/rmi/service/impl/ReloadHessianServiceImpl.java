package cn.szz.sdk.rmi.service.impl;

import cn.szz.sdk.factory.ShiroFilterFactory;
import cn.szz.sdk.realm.CustomCasRealm;
import cn.szz.sdk.rmi.service.ReloadHessianService;

public class ReloadHessianServiceImpl implements ReloadHessianService {

	private ShiroFilterFactory shiroFilterFactory;
	
	private CustomCasRealm customCasRealm;
	
	public ReloadHessianServiceImpl() {
		super();
	}

	public ReloadHessianServiceImpl(ShiroFilterFactory shiroFilterFactory, CustomCasRealm customCasRealm) {
		super();
		this.shiroFilterFactory = shiroFilterFactory;
		this.customCasRealm = customCasRealm;
	}

	@Override
	public boolean reloadFilterChainDefinitions() {
		try {
			shiroFilterFactory.reloadFilterChainDefinitions();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean clearAllAuthorizationCache() {
		customCasRealm.clearAllAuthorizationCache();
		return true;
	}

	public ShiroFilterFactory getShiroFilterFactory() {
		return shiroFilterFactory;
	}

	public void setShiroFilterFactory(ShiroFilterFactory shiroFilterFactory) {
		this.shiroFilterFactory = shiroFilterFactory;
	}

	public CustomCasRealm getCustomCasRealm() {
		return customCasRealm;
	}

	public void setCustomCasRealm(CustomCasRealm customCasRealm) {
		this.customCasRealm = customCasRealm;
	}
}
