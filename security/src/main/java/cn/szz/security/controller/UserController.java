package cn.szz.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.RoleService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;
	
	//使用CAS时注释
	//@RequestMapping(value = "/smLogin")
	//@ResponseBody
	public AjaxResult smLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AjaxResult result = userService.smLogin(username, password);
		return result;
	}
	
	@RequestMapping(value = "/list")
	public String list() {
		return "user/list";
	}
	
	@RequestMapping(value = "/getByPage")
	@ResponseBody
	public PageData getByPage(PageData pageData) {
		return userService.getByPage(pageData);
	}
	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request) {
		List<Role> roleList = roleService.listAll();
		request.setAttribute("roleList", roleList);
		return "user/save";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {
		String id = request.getParameter("id");
		User user = userService.getById(id);
		request.setAttribute("up_user", user);
		List<Role> roleList = roleService.listAll();
		request.setAttribute("roleList", roleList);
		Map<Long, Long> roleIdMap = userRoleService.mapRoleIdByUserId(id);
		request.setAttribute("roleIdMap", roleIdMap);
		return "user/update";
	}
	
	@RequestMapping(value = "/updatePassword")
	public String updatePassword(HttpServletRequest request) {
		return "user/updatePassword";
	}
	
	@RequestMapping(value = "/smSave")
	@ResponseBody
	public AjaxResult smSave(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", request.getParameter("username"));
		params.put("realname", request.getParameter("realname"));
		params.put("phone", request.getParameter("phone"));
		params.put("email", request.getParameter("email"));
		params.put("status", request.getParameter("status"));
		String[] roleIdArray = request.getParameterValues("role");
		return userService.save(params, roleIdArray);
	}
	
	@RequestMapping(value = "/smUpdate")
	@ResponseBody
	public AjaxResult smUpdate(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getParameter("id"));
		params.put("username", request.getParameter("username"));
		params.put("realname", request.getParameter("realname"));
		params.put("phone", request.getParameter("phone"));
		params.put("email", request.getParameter("email"));
		params.put("status", request.getParameter("status"));
		String[] roleIdArray = request.getParameterValues("role");
		return userService.update(params, roleIdArray);
	}
	
	@RequestMapping(value = "/smUpdatePassword")
	@ResponseBody
	public AjaxResult smUpdatePassword(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("oldPassword", request.getParameter("oldPassword"));
		params.put("newPassword", request.getParameter("newPassword"));
		return userService.updatePassword(params);
	}
	
	@RequestMapping(value = "/smResetPassword")
	@ResponseBody
	public AjaxResult smResetPassword(String id) {
		return userService.resetPassword(id);
	}
	
	@RequestMapping(value = "/smDel")
	@ResponseBody
	public AjaxResult smDel(String id) {
		return userService.del(id);
	}
}
