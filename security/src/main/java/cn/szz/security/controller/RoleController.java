package cn.szz.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.RoleService;

/**
 * 角色controller
 *
 * @author Shi Zezhu
 * @date 2017年9月12日 下午4:20:51
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "role/list";
	}
	
	@RequestMapping(value = "/getByPage")
	@ResponseBody
	public PageData getByPage(PageData pageData) {
		return roleService.getByPage(pageData);
	}
	
	@RequestMapping(value = "/save")
	public String save() {
		return "role/save";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {
		String id = request.getParameter("id");
		Role role = roleService.getById(id);
		request.setAttribute("role", role);
		return "role/update";
	}
	
	@RequestMapping(value = "/smSave")
	@ResponseBody
	public AjaxResult smSave(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", request.getParameter("name"));
		params.put("descr", request.getParameter("descr"));
		String menuIds = request.getParameter("menu");
		return roleService.save(params, menuIds);
	}
	
	@RequestMapping(value = "/smUpdate")
	@ResponseBody
	public AjaxResult smUpdate(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getParameter("id"));
		params.put("name", request.getParameter("name"));
		params.put("descr", request.getParameter("descr"));
		String menuIds = request.getParameter("menu");
		return roleService.update(params, menuIds);
	}
	
	@RequestMapping(value = "/smDel")
	@ResponseBody
	public AjaxResult smDel(String id) {
		return roleService.del(id);
	}
}
