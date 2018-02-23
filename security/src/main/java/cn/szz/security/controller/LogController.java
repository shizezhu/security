package cn.szz.security.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.LogService;
import cn.szz.security.service.ProjectService;

/**
 * 日志
 *
 * @author Shi Zezhu
 * @date 2018年2月5日 下午2:34:21
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogController {

	@Resource
	private LogService logService;
	
	@Resource
	private ProjectService projectService;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request) {
		List<Project> projectList = projectService.listAll();
		request.setAttribute("projectList", projectList);
		return "log/list";
	}
	
	@RequestMapping(value = "/getByPage")
	@ResponseBody
	public PageData getByPage(String projectId, String type, PageData pageData) {
		return logService.getByPage(projectId, type, pageData);
	}
	
}
