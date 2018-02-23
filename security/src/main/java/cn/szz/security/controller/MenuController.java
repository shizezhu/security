package cn.szz.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ArrayNode;

import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.MenuService;
import cn.szz.security.service.ProjectService;

/**
 * 菜单controller
 *
 * @author Shi Zezhu
 * @date 2017年9月12日 下午4:20:43
 *
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	@Resource
	private MenuService menuService;
	
	@Resource
	private ProjectService projectService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "menu/list";
	}
	
	@RequestMapping(value = "/getByPage")
	@ResponseBody
	public PageData getByPage(String sort, String order, int limit, int offset, String search, String parentId) {
		PageData pageData = new PageData(sort, order, limit, offset, search);
		return menuService.getByPage(pageData, parentId);
	}
	
	@RequestMapping(value = "/getTreeNode")
	@ResponseBody
	public ArrayNode getTreeNode(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getParameter("id"));
		params.put("root", request.getParameter("root"));
		params.put("rootName", request.getParameter("rootName"));
		params.put("rootOpened", request.getParameter("rootOpened"));
		params.put("children", request.getParameter("children"));
		params.put("childrenOpened", request.getParameter("childrenOpened"));
		return menuService.getTreeNode(params);
	}
	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request) {
		String id = request.getParameter("id");
		Menu menu = menuService.getById(id);
		request.setAttribute("menu", menu);
		request.setAttribute("projectList", projectService.listAll());
		return "menu/save";
	}
	
	@RequestMapping(value = "/menutree")
	public String menutree() {
		return "menu/menutree";
	}
	
	@RequestMapping(value = "/icon")
	public String icon() {
		return "menu/icon";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {
		String id = request.getParameter("id");
		String isParent = request.getParameter("isParent");
		Menu menu = menuService.getById(id);
		request.setAttribute("menu", menu);
		request.setAttribute("isParent", isParent);
		request.setAttribute("projectList", projectService.listAll());
		return "menu/update";
	}
	
	@RequestMapping(value = "/smSave")
	@ResponseBody
	public AjaxResult smSave(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", request.getParameter("projectId"));
		params.put("type", request.getParameter("type"));
		params.put("parentId", request.getParameter("parentId"));
		params.put("name", request.getParameter("name"));
		params.put("url", request.getParameter("url"));
		params.put("authIden", request.getParameter("authIden"));
		params.put("icon", request.getParameter("icon"));
		params.put("sort", request.getParameter("sort"));
		return menuService.save(params);
	}
	
	@RequestMapping(value = "/smUpdate")
	@ResponseBody
	public AjaxResult smUpdate(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getParameter("id"));
		params.put("projectId", request.getParameter("projectId"));
		params.put("type", request.getParameter("type"));
		params.put("parentId", request.getParameter("parentId"));
		params.put("name", request.getParameter("name"));
		params.put("url", request.getParameter("url"));
		params.put("authIden", request.getParameter("authIden"));
		params.put("icon", request.getParameter("icon"));
		params.put("sort", request.getParameter("sort"));
		return menuService.update(params);
	}
	
	@RequestMapping(value = "/smDel")
	@ResponseBody
	public AjaxResult smDel(String id) {
		return menuService.del(id);
	}
}
