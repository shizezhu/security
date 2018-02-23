package cn.szz.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.ProjectService;

/**
 * 角色controller
 *
 * @author Shi Zezhu
 * @date 2017年9月12日 下午4:20:51
 *
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Resource
	private ProjectService projectService;
	
	@RequestMapping(value = "/list")
	public String list() {
		return "project/list";
	}
	
	@RequestMapping(value = "/getByPage")
	@ResponseBody
	public PageData getByPage(PageData pageData) {
		return projectService.getByPage(pageData);
	}
	
	@RequestMapping(value = "/save")
	public String save() {
		return "project/save";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {
		String id = request.getParameter("id");
		Project project = projectService.getById(id);
		request.setAttribute("project", project);
		return "project/update";
	}
	
	@RequestMapping(value = "/smSave")
	@ResponseBody
	public AjaxResult smSave(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", request.getParameter("name"));
		params.put("projectUrl", request.getParameter("projectUrl"));
		params.put("reloadAuthUrl", request.getParameter("reloadAuthUrl"));
		params.put("status", request.getParameter("status"));
		return projectService.save(params);
	}
	
	@RequestMapping(value = "/smUpdate")
	@ResponseBody
	public AjaxResult smUpdate(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getParameter("id"));
		params.put("name", request.getParameter("name"));
		params.put("projectUrl", request.getParameter("projectUrl"));
		params.put("reloadAuthUrl", request.getParameter("reloadAuthUrl"));
		params.put("status", request.getParameter("status"));
		return projectService.update(params);
	}
	
	@RequestMapping(value = "/smDel")
	@ResponseBody
	public AjaxResult smDel(String id) {
		return projectService.del(id);
	}
}
