package cn.szz.security.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szz.security.service.RoleMenuService;

/**
 * 角色与菜单关系Controller
 *
 * @author Shi Zezhu
 * @date 2017年9月18日 下午7:45:23
 *
 */
@Controller
@RequestMapping(value = "/rolemenu")
public class RoleMenuController {

	@Resource
	private RoleMenuService roleMenuService;
	
	@RequestMapping(value = "/listMenuIdByRoleId")
	@ResponseBody
	public List<Long> listMenuIdByRoleId(String roleId) {
		return roleMenuService.listMenuIdByRoleId(roleId);
	}
}
