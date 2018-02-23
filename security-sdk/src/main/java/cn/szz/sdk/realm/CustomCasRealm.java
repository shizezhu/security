package cn.szz.sdk.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.entity.SecuritySdkUser;
import cn.szz.sdk.utils.CheckUtils;
import cn.szz.sdk.utils.CommUtils;

/**
 * 自定义Cas Realm，使用Cas认证时使用
 *
 * @author Shi Zezhu
 * @date 2017年10月20日 下午3:20:52
 *
 */
public class CustomCasRealm extends CasRealm {

	private static final Logger logger = Logger.getLogger(CustomCasRealm.class);
	
	private static final String DEFAULT_SUPER_USER_ATTRIBUTES_KEY = "isSuperuser";

	private String superuserAttributesKey = DEFAULT_SUPER_USER_ATTRIBUTES_KEY;
	
	private SecuritySdkService securitySdkService;
	
	public CustomCasRealm() {
		super();
	}
	
	public CustomCasRealm(String superuserAttributesKey, SecuritySdkService securitySdkService) {
		super();
		this.superuserAttributesKey = superuserAttributesKey;
		this.securitySdkService = securitySdkService;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (!(token instanceof CasToken)) {
			return null;
		}
		CasToken casToken = (CasToken) token;
        if (CheckUtils.isNull(casToken)) {
            return null;
        }
        String ticket = (String)casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        TicketValidator ticketValidator = ensureTicketValidator();
        try {
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String username = casPrincipal.getName();
            Map<String, Object> attributes = casPrincipal.getAttributes();
            casToken.setUserId(username);
            String rememberMeAttributeName = getRememberMeAttributeName();
            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
            boolean isRemembered = CheckUtils.isNotEmpty(rememberMeStringValue) && Boolean.parseBoolean(rememberMeStringValue);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }
            SecuritySdkUser user = null;
            String isSuperuserStringValue = (String) attributes.get(getSuperuserAttributesKey());
            boolean isSuperuser = CheckUtils.isNotEmpty(isSuperuserStringValue) && Boolean.parseBoolean(isSuperuserStringValue);
			try {
				if (isSuperuser) {
					user = CommUtils.createUser(securitySdkService.getSecurityHessianClient().getSuperuser(username));
					logger.info("superuser data : " + user);
				} else {
					user = CommUtils.createUser(securitySdkService.getSecurityHessianClient().getUserByUsername(username));
					logger.info("user data : " + user);
				}
			} catch (Exception e) {
				logger.error("Remote access user data exception", e);
				return null;
			}
    		if (CheckUtils.isNull(user)) {
    			throw new UnknownAccountException();
    		}
    		if (!user.getStatus()) {
    			throw new DisabledAccountException();
    		}
    		return new SimpleAuthenticationInfo(user, ticket, this.getName());
        } catch (TicketValidationException e) {
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SecuritySdkUser user =  (SecuritySdkUser) principals.getPrimaryPrincipal();
		List<String> authIdenList = new ArrayList<String>();
		try {
			if (user.getIsSuperuser()) {
				authIdenList = securitySdkService.getSecurityHessianClient().listAuthIdenByProjectId(securitySdkService.getProjectId());
				logger.info("superuser auth data : " + authIdenList);
			} else {
				authIdenList = securitySdkService.getSecurityHessianClient().listAuthIdenByProjectIdAndUserId(securitySdkService.getProjectId(), user.getId());
				logger.info("user auth data : " + authIdenList);
			}
		} catch (Exception e) {
			logger.error("Remote access auth data exception", e);
		}
		Set<String> stringPermissions = new HashSet<String>(authIdenList);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
	}

	/**
	 * 清除所有授权
	 * 
	 * @author Shi Zezhu
	 * @date 2018年1月23日 上午9:58:05
	 */
	public void clearAllAuthorizationCache() {
		this.getAuthorizationCache().clear();
	}

	public String getSuperuserAttributesKey() {
		return superuserAttributesKey;
	}

	public void setSuperuserAttributesKey(String superuserAttributesKey) {
		this.superuserAttributesKey = superuserAttributesKey;
	}

	public SecuritySdkService getSecuritySdkService() {
		return securitySdkService;
	}

	public void setSecuritySdkService(SecuritySdkService securitySdkService) {
		this.securitySdkService = securitySdkService;
	}
}
