package cn.szz.cas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.PersonDirectoryPrincipalResolver;

/**
 * 自定义Principal Resolver
 *
 * @author Shi Zezhu
 * @date 2017年10月20日 下午4:30:16
 *
 */
public class CasCustomPersonDirectoryPrincipalResolver extends PersonDirectoryPrincipalResolver {

	private CasCustomAuthenticationHandler casCustomAuthenticationHandler;
	
	public CasCustomPersonDirectoryPrincipalResolver() {
		super();
	}

	public CasCustomPersonDirectoryPrincipalResolver(CasCustomAuthenticationHandler casCustomAuthenticationHandler) {
		super();
		this.casCustomAuthenticationHandler = casCustomAuthenticationHandler;
	}

	@Override
	protected Map<String, List<Object>> retrievePersonAttributes(String principalId, Credential credential) {
		Map<String, List<Object>> newAttributes = new HashMap<String, List<Object>>();
		Map<String, List<Object>> attributes =  super.retrievePersonAttributes(principalId, credential);
		if (attributes != null) {
			newAttributes.putAll(attributes);
		}
		if (credential instanceof UsernamePasswordCredential) {
			UsernamePasswordCredential transformedCredential = (UsernamePasswordCredential) credential;
			final String username = transformedCredential.getUsername();
			final String password = transformedCredential.getPassword();
			List<Object> list = new ArrayList<Object>();
			list.add(casCustomAuthenticationHandler.isSuperuser(username, password));
			newAttributes.put(casCustomAuthenticationHandler.getSuperuserAttributesKey(), list);
		}
		return newAttributes;
	}

	public CasCustomAuthenticationHandler getCasCustomAuthenticationHandler() {
		return casCustomAuthenticationHandler;
	}

	public void setCasCustomAuthenticationHandler(CasCustomAuthenticationHandler casCustomAuthenticationHandler) {
		this.casCustomAuthenticationHandler = casCustomAuthenticationHandler;
	}
}
