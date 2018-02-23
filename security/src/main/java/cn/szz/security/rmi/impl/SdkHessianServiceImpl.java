package cn.szz.security.rmi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.szz.sdk.rmi.client.SecurityHessianClient;
import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.po.User;
import cn.szz.security.rmi.SdkHessianService;
import cn.szz.security.service.LogService;
import cn.szz.security.service.MenuService;
import cn.szz.security.service.UserService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.SuperUserUtils;

@Service
public class SdkHessianServiceImpl implements SdkHessianService, SecurityHessianClient {

	@Resource
	@Lazy
	private UserService userService;
	
	@Resource
	@Lazy
	private MenuService menuService;
	
	@Resource
	@Lazy
	private LogService logService;
	
	@Override
	public Map<String, Object> getSuperuser(String username) {
		User user = SuperUserUtils.createSuperUser(username);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", user.getId());
		result.put("username", user.getUsername());
		result.put("realname", user.getRealname());
		result.put("email", user.getEmail());
		result.put("status", user.getStatus());
		result.put("isSuperuser", true);
		return result;
	}

	@Override
	public Map<String, Object> getUserByUsername(String username) {
		if (CheckUtils.isEmpty(username)) {
			return null;
		}
		User user = userService.getByUsername(username);
		if (CheckUtils.isNull(user)) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", user.getId());
		result.put("username", user.getUsername());
		result.put("password", user.getPassword());
		result.put("realname", user.getRealname());
		result.put("phone", user.getPhone());
		result.put("email", user.getEmail());
		result.put("status", user.getStatus());
		result.put("isSuperuser", false);
		return result;
	}
	
	@Override
	public Map<String, Object> getUserById(long userId) {
		User user = userService.getById(userId);
		if (CheckUtils.isNull(user)) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", user.getId());
		result.put("username", user.getUsername());
		result.put("password", user.getPassword());
		result.put("realname", user.getRealname());
		result.put("phone", user.getPhone());
		result.put("email", user.getEmail());
		result.put("status", user.getStatus());
		result.put("isSuperuser", false);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> listUserAll() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<User> userList = userService.listByStatus(true);
		userList.forEach(user -> {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", user.getId());
			result.put("username", user.getUsername());
			result.put("password", user.getPassword());
			result.put("realname", user.getRealname());
			result.put("phone", user.getPhone());
			result.put("email", user.getEmail());
			result.put("status", user.getStatus());
			result.put("isSuperuser", false);
			resultList.add(result);
		});
		return resultList;
	}

	@Override
	public List<String> listAuthIdenByProjectId(long projectId) {
		return menuService.listAuthIdenByProjectId(projectId);
	}

	@Override
	public List<String> listAuthIdenByProjectIdAndUserId(long projectId, long userId) {
		return menuService.listAuthIdenByProjectIdAndUserId(projectId, userId);
	}

	@Override
	public Map<String, String> mapFilterChainByProjectId(long projectId) {
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		List<Menu> menuList = menuService.listByProjectId(projectId);
		menuList.forEach(menu -> {
			if (CheckUtils.isNotEquals(menu.getType(), Menu.TYPE_DIRECTORY) && CheckUtils.isNotEmpty(menu.getAuthIden()) && CheckUtils.isNotEmpty(menu.getUrl())) {
				filterChainDefinitionMap.put(menu.getUrl(), "perms[" + menu.getAuthIden() + "]");
			}
		});
		return filterChainDefinitionMap;
	}

	@Override
	public void saveLog(long projectId, long userId, String userIp, String name, String descr, int type, String content) {
		logService.save(projectId, userId, userIp, name, descr, type, content);
	}

	@Override
	public void saveLog(long projectId, String userIp, String name, String descr, int type, String content) {
		logService.save(projectId, userIp, name, descr, type, content);
	}

	@Override
	public void saveLog(long projectId, long userId, String name, String descr, int type, String content) {
		logService.save(projectId, userId, name, descr, type, content);
	}

	@Override
	public void saveLog(long projectId, long userId, String userIp, String name, String descr, int type) {
		logService.save(projectId, userId, userIp, name, descr, type);
	}

	@Override
	public void saveLog(long projectId, String name, String descr, int type, String content) {
		logService.save(projectId, name, descr, type, content);
	}

	@Override
	public void saveLog(long projectId, long userId, String name, String descr, int type) {
		logService.save(projectId, userId, name, descr, type);
	}

	@Override
	public void saveLog(long projectId, String userIp, String name, String descr, int type) {
		logService.save(projectId, userIp, name, descr, type);
	}

	@Override
	public void saveLog(long projectId, String name, String descr, int type) {
		logService.save(projectId, name, descr, type);
	}
}
