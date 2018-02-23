package cn.szz.security.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.szz.security.service.MenuService;

/**
 * 主页Controller
 *
 * @author Shi Zezhu
 * @date 2017年9月23日 上午11:43:12
 *
 */
@Controller
public class IndexController {

	@Resource
	private MenuService menuService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("menus", menuService.getIndexMenu());
		return "index";
	}
	
	@RequestMapping(value="/homepage")
	public String homepage() {
		return "homepage";
	}
}
