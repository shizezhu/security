package cn.szz.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import cn.szz.sdk.entity.SecuritySdkUser;
import cn.szz.sdk.rmi.client.SecurityHessianClient;
import cn.szz.sdk.utils.CheckUtils;
import cn.szz.sdk.utils.CommUtils;
import cn.szz.sdk.utils.LogType;

public class DefultSecuritySdkServiceImpl implements SecuritySdkService {

	private String projectId;
	
	private SecurityHessianClient securityHessianClient;
	
	public DefultSecuritySdkServiceImpl() {
		super();
	}

	public DefultSecuritySdkServiceImpl(String projectId, SecurityHessianClient securityHessianClient) {
		super();
		this.projectId = projectId;
		this.securityHessianClient = securityHessianClient;
	}

	@Override
	public SecuritySdkUser getCurrentUser() {
		return (SecuritySdkUser) SecurityUtils.getSubject().getPrincipal();
	}

	@Override
	public SecuritySdkUser getUserById(long userId) {
		return CommUtils.createUser(securityHessianClient.getUserById(userId));
	}

	@Override
	public List<SecuritySdkUser> listUserAll() {
		List<SecuritySdkUser> userList = new ArrayList<SecuritySdkUser>();
		List<Map<String, Object>> userDataList = securityHessianClient.listUserAll();
		for (Map<String, Object> userData : userDataList) {
			userList.add(CommUtils.createUser(userData));
		}
		return userList;
	}

	@Override
	public void saveLog(String userIp, String name, String descr, LogType type, String content) {
		SecuritySdkUser user = this.getCurrentUser();
		if (CheckUtils.isNotNull(user)) {
			securityHessianClient.saveLog(this.getProjectId(), user.getId(), userIp, name, descr, type.getType(), content);
		} else {
			securityHessianClient.saveLog(this.getProjectId(), userIp, name, descr, type.getType(), content);
		}
	}

	@Override
	public void saveLog(String name, String descr, LogType type, String content) {
		SecuritySdkUser user = this.getCurrentUser();
		if (CheckUtils.isNotNull(user)) {
			securityHessianClient.saveLog(this.getProjectId(), user.getId(),  name, descr, type.getType(), content);
		} else {
			securityHessianClient.saveLog(this.getProjectId(),  name, descr, type.getType(), content);
		}
	}

	@Override
	public void saveLog(String userIp, String name, String descr, LogType type) {
		SecuritySdkUser user = this.getCurrentUser();
		if (CheckUtils.isNotNull(user)) {
			securityHessianClient.saveLog(this.getProjectId(), user.getId(), userIp, name, descr, type.getType());
		} else {
			securityHessianClient.saveLog(this.getProjectId(), userIp, name, descr, type.getType());
		}
	}

	@Override
	public void saveLog(String name, String descr, LogType type) {
		SecuritySdkUser user = this.getCurrentUser();
		if (CheckUtils.isNotNull(user)) {
			securityHessianClient.saveLog(this.getProjectId(), user.getId(), name, descr, type.getType());
		} else {
			securityHessianClient.saveLog(this.getProjectId(), name, descr, type.getType());
		}
	}

	@Override
	public long getProjectId() {
		return CheckUtils.checkLong(CheckUtils.checkNotEmpty(projectId, "Project id is not configured"), "Project id is error");
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Override
	public SecurityHessianClient getSecurityHessianClient() {
		return CheckUtils.checkNotNull(securityHessianClient, "Security hessian client is null");
	}

	public void setSecurityHessianClient(SecurityHessianClient securityHessianClient) {
		this.securityHessianClient = securityHessianClient;
	}
}
