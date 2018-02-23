package cn.szz.sdk.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.szz.sdk.utils.CommUtils;


/**
 * perms filter
 *
 * @author Shi Zezhu
 * @date 2017年9月22日 下午7:27:38
 *
 */
public class PermissionsAuthorizationFilter extends AuthorizationFilter {

	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String DEFAULT_STATUS_KEY_NAME = "status";
	private static final String DEFAULT_MESSAGE_KEY_NAME = "message";
	private static final String DEFAULT_NO_LOGIN_STATUS = "NOLOGIN";
	private static final String DEFAULT_NO_LOGIN_MESSAGE = "no logon";
	private static final String DEFAULT_NO_AUTH_STATUS = "NOAUTH";
	private static final String DEFAULT_NO_AUTH_MESSAGE = "No auth";
	
	private String charset = DEFAULT_CHARSET;
	private String status_key_name = DEFAULT_STATUS_KEY_NAME;
	private String message_key_name = DEFAULT_MESSAGE_KEY_NAME;
	private String no_login_status = DEFAULT_NO_LOGIN_STATUS;
	private String no_login_message = DEFAULT_NO_LOGIN_MESSAGE;
	private String no_auth_status = DEFAULT_NO_AUTH_STATUS;
	private String no_auth_message = DEFAULT_NO_AUTH_MESSAGE;
	
	public PermissionsAuthorizationFilter() {
		super();
	}
	
	public PermissionsAuthorizationFilter(String charset, String status_key_name, String message_key_name,
			String no_login_status, String no_login_message, String no_auth_status, String no_auth_message) {
		super();
		this.charset = charset;
		this.status_key_name = status_key_name;
		this.message_key_name = message_key_name;
		this.no_login_status = no_login_status;
		this.no_login_message = no_login_message;
		this.no_auth_status = no_auth_status;
		this.no_auth_message = no_auth_message;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {
			if (CommUtils.isAjax(httpRequest)) {
				CommUtils.sendData(httpResponse, this.charset, new StringBuffer().append("{\"")
						.append(this.status_key_name)
						.append("\":\"")
						.append(this.no_login_status)
						.append("\",\"")
						.append(this.message_key_name)
						.append("\":\"")
						.append(this.no_login_message)
						.append("\"}").toString());
			} else {
				saveRequestAndRedirectToLogin(request, response);
			}
		} else {
			if (CommUtils.isAjax(httpRequest)) {
				CommUtils.sendData(httpResponse, this.charset, new StringBuffer().append("{\"")
						.append(this.status_key_name)
						.append("\":\"")
						.append(this.no_auth_status)
						.append("\",\"")
						.append(this.message_key_name)
						.append("\":\"")
						.append(this.no_auth_message)
						.append("\"}").toString());
			} else {
				String unauthorizedUrl = getUnauthorizedUrl();
				if (StringUtils.hasText(unauthorizedUrl)) {
					WebUtils.issueRedirect(request, response, unauthorizedUrl);
				} else {
					WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
		return false;
	}
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getNo_login_status() {
		return no_login_status;
	}

	public void setNo_login_status(String no_login_status) {
		this.no_login_status = no_login_status;
	}

	public String getNo_login_message() {
		return no_login_message;
	}

	public void setNo_login_message(String no_login_message) {
		this.no_login_message = no_login_message;
	}

	public String getStatus_key_name() {
		return status_key_name;
	}

	public void setStatus_key_name(String status_key_name) {
		this.status_key_name = status_key_name;
	}

	public String getMessage_key_name() {
		return message_key_name;
	}

	public void setMessage_key_name(String message_key_name) {
		this.message_key_name = message_key_name;
	}

	public String getNo_auth_status() {
		return no_auth_status;
	}

	public void setNo_auth_status(String no_auth_status) {
		this.no_auth_status = no_auth_status;
	}

	public String getNo_auth_message() {
		return no_auth_message;
	}

	public void setNo_auth_message(String no_auth_message) {
		this.no_auth_message = no_auth_message;
	}
}
