package cn.szz.sdk.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.entity.SecuritySdkUser;
import cn.szz.sdk.utils.CheckUtils;
import cn.szz.sdk.utils.CommUtils;


/**
 * 自定义JDBC Realm，本系统认证时使用
 *
 * @author Shi Zezhu
 * @date 2017年9月20日 下午6:21:15
 *
 */
public class CustomJdbcRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(CustomJdbcRealm.class);
	
	private SecuritySdkService securitySdkService;
	
	public CustomJdbcRealm() {
		super();
	}

	public CustomJdbcRealm(SecuritySdkService securitySdkService) {
		super();
		this.securitySdkService = securitySdkService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SecuritySdkUser user =  (SecuritySdkUser) principals.getPrimaryPrincipal();
		List<String> authIdenList = new ArrayList<String>();
		try {
			authIdenList = securitySdkService.getSecurityHessianClient().listAuthIdenByProjectIdAndUserId(securitySdkService.getProjectId(), user.getId());
			logger.info("auth data : " + authIdenList);
		} catch (Exception e) {
			logger.error("Remote access auth data exception", e);
		}
		Set<String> stringPermissions = new HashSet<String>(authIdenList);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (!(token instanceof UsernamePasswordToken)) {
			return null;
		}
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		SecuritySdkUser user = null;
		try {
			user = CommUtils.createUser(securitySdkService.getSecurityHessianClient().getUserByUsername(username));
			logger.info("user data : " + user);
		} catch (Exception e) {
			logger.error("Remote access user data exception", e);
		}
		if (CheckUtils.isNull(user)) {
			throw new UnknownAccountException();
		}
		if (!user.getStatus()) {
			throw new DisabledAccountException();
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
	}
	
	public void clearAllAuthorizationCache() {
		this.getAuthorizationCache().clear();
	}

	public SecuritySdkService getSecuritySdkService() {
		return securitySdkService;
	}

	public void setSecuritySdkService(SecuritySdkService securitySdkService) {
		this.securitySdkService = securitySdkService;
	}
}
