package cn.szz.security.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.entity.SecuritySdkUser;
import cn.szz.sdk.utils.LogType;
import cn.szz.security.dao.MenuDao;
import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.IndexMenu;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.MenuService;
import cn.szz.security.service.ProjectService;
import cn.szz.security.service.ReloadAuthFilterChainsService;
import cn.szz.security.service.RoleMenuService;
import cn.szz.security.service.UserRoleService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ReloadAuthFilterChainsService reloadAuthFilterChainsService;
	
	@Resource
	private SecuritySdkService securitySdkService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Menu> listAll() {
		return menuDao.listAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Menu> listByProjectId(long projectId) {
		return menuDao.listByProjectId(projectId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Menu getById(String id) {
		if (CheckUtils.isNotId(id)) {
			return null;
		}
		return this.getById(Long.parseLong(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Menu getById(long id) {
		return menuDao.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public PageData getByPage(PageData pageData, String parentIdStr) {
		if (CheckUtils.isNull(pageData) || CheckUtils.isNotId(parentIdStr)) {
			return new PageData(0, new ArrayList<Menu>());
		}
		long parentId = Long.parseLong(parentIdStr);
		if (parentId < 0) {
			pageData.setRows(menuDao.listRows(pageData));
			pageData.setTotal(menuDao.getTotal(pageData));
		} else {
			pageData.setRows(menuDao.listRows(pageData, parentId));
			pageData.setTotal(menuDao.getTotal(pageData, parentId));
		}
		return pageData;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ArrayNode getTreeNode(Map<String, String> params) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jstreeNodes = mapper.createArrayNode();
		if (CheckUtils.isEmpty(params)) {
			return jstreeNodes;
		}
		if (CheckUtils.isNotId(params.get("id"))) {
			return jstreeNodes;
		}
		long id = Long.parseLong(params.get("id"));
		String rootName = params.get("rootName");
		boolean root = false, rootOpened = false, children = false, childrenOpened = false;
		if (CheckUtils.isNotEmpty(params.get("root"))) {
			root = true;
		}
		if (CheckUtils.isEmpty(rootName)) {
			rootName = "所有菜单";
		}
		if (CheckUtils.isNotEmpty(params.get("rootOpened"))) {
			rootOpened = true;
		}
		if (CheckUtils.isNotEmpty(params.get("children"))) {
			children = true;
		}
		if (CheckUtils.isNotEmpty(params.get("childrenOpened"))) {
			childrenOpened = true;
		}
		if (!root) {
			return this.createTreeNodeChildren(mapper, id, children, childrenOpened);
		}
		ObjectNode rootNode = this.createTreeNodeRoot(mapper, rootName, rootOpened);
		rootNode.set("children", this.createTreeNodeChildren(mapper, id, children, childrenOpened));
		jstreeNodes.add(rootNode);
		return jstreeNodes;
	}
	
	private ObjectNode createTreeNodeRoot(ObjectMapper mapper, String name, boolean rootOpened) {
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", "0");
		rootNode.put("text", name);
		ObjectNode rootNodeState = mapper.createObjectNode();
		rootNodeState.put("opened", rootOpened);
		rootNode.set("state", rootNodeState);
		return rootNode;
	}
	
	private ArrayNode createTreeNodeChildren(ObjectMapper mapper, long id, boolean children, boolean childrenOpened) {
		ArrayNode childrenNode = mapper.createArrayNode();
		List<Menu> list = menuDao.listByParentId(id);
		list.forEach(menu -> {
			ObjectNode node = mapper.createObjectNode();
			node.put("id", menu.getId());
			node.put("text", menu.getName());
			node.put("type", menu.getType());
			ObjectNode nodeState = mapper.createObjectNode();
			nodeState.put("opened", childrenOpened);
			node.set("state", nodeState);
			if (children) {
				node.set("children", createTreeNodeChildren(mapper, menu.getId(), children, childrenOpened));
			} else {
				node.put("children", menuDao.listByParentId(menu.getId()).size() > 0);
			}
			childrenNode.add(node);
		});
		return childrenNode;
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult save(Map<String, String> params) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		long projectId = CheckUtils.checkLong(params.get("projectId"), ErrorCode.PR_ID_ERROR);
		String name = CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.ME_NA_EMPTY);
		int type = CheckUtils.checkInt(params.get("type"), ErrorCode.ME_TY_ERROR);
		Menu parentMenu = null;
		if (CheckUtils.isNotEmpty(params.get("parentId")) && CheckUtils.isNotEquals(params.get("parentId"), "0")) {
			parentMenu = this.getById(CheckUtils.checkLong(params.get("parentId"), ErrorCode.PARENT_ID_ERROR));
		}
		String url = null;
		String authIden = null;
		String icon = null;
		Integer sort = null;
		switch (type) {
		case Menu.TYPE_DIRECTORY:
			url = "#";
			icon = params.get("icon");
			sort = CheckUtils.checkInt(params.get("sort"), ErrorCode.ME_SO_ERROR);
			if (CheckUtils.isNotNull(parentMenu)) {
				CheckUtils.checkEquals(parentMenu.getType(), Menu.TYPE_DIRECTORY, ErrorCode.ME_PA_TY_DIRECTORY);
				CheckUtils.checkNull(parentMenu.getParentMenu(), ErrorCode.ME_CANNOT_ADD_DIRECTORY);
			}
			break;
		case Menu.TYPE_VIEW:
			url = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("url"), ErrorCode.ME_UR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.ME_UR_EMPTY);
			authIden = CheckUtils.checkNotEmpty(params.get("authIden"), ErrorCode.ME_AU_EMPTY);
			sort = CheckUtils.checkInt(params.get("sort"), ErrorCode.ME_SO_ERROR);
			CheckUtils.checkNull(menuDao.getByAuthIden(projectId, authIden), ErrorCode.ME_AU_EXISTENCE);
			CheckUtils.checkTrue(CheckUtils.isNotNull(parentMenu) && (CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_DIRECTORY) || CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_VIEW)), ErrorCode.ME_PA_TY_DIRECTORY_OR_VIEW);
			break;
		case Menu.TYPE_BUTTON:
			url = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("url"), ErrorCode.ME_UR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.ME_UR_EMPTY);
			authIden = CheckUtils.checkNotEmpty(params.get("authIden"), ErrorCode.ME_AU_EMPTY);
			CheckUtils.checkNull(menuDao.getByAuthIden(projectId, authIden), ErrorCode.ME_AU_EXISTENCE);
			CheckUtils.checkTrue(CheckUtils.isNotNull(parentMenu) && (CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_VIEW) || CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_BUTTON)), ErrorCode.ME_PA_TY_VIEW_OR_BUTTON);
			break;
		default:
			throw new ResultException(ErrorCode.ME_TY_NOT_EXISTENCE);
		}
		Project project = CheckUtils.checkNotNull(projectService.getById(projectId), ErrorCode.PR_NOT_EXISTENCE);
		CheckUtils.checkNull(menuDao.get(CheckUtils.isNull(parentMenu) ? 0L : parentMenu.getId(), name), ErrorCode.ME_NA_EXISTENCE);
		Menu menu = new Menu();
		menu.setProject(project);
		menu.setParentMenu(parentMenu);
		menu.setName(name);
		menu.setType(type);
		menu.setUrl(url);
		menu.setAuthIden(authIden);
		menu.setIcon(icon);
		menu.setSort(sort);
		menu.setAddTime(LocalDateTime.now());
		menu.setModifyTime(LocalDateTime.now());
		menuDao.save(menu);
		reloadAuthFilterChainsService.reloadFilterChainDefinitions(project);
		reloadAuthFilterChainsService.clearAllAuthorizationCache();
		securitySdkService.saveLog("菜单", "保存菜单", LogType.SAVE, menu.toString());
		return new AjaxResult(ErrorCode.SUCCESS, menu);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult update(Map<String, String> params) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		Menu menu = CheckUtils.checkNotNull(this.getById(CheckUtils.checkId(params.get("id"))), ErrorCode.ME_NOT_EXISTENCE);
		long projectId = CheckUtils.checkLong(params.get("projectId"), ErrorCode.PR_ID_ERROR);
		String name = CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.ME_NA_EMPTY);
		int type = CheckUtils.checkInt(params.get("type"), ErrorCode.ME_TY_ERROR);
		Menu parentMenu = null;
		if (CheckUtils.isNotEmpty(params.get("parentId")) && CheckUtils.isNotEquals(params.get("parentId"), "0")) {
			parentMenu = this.getById(CheckUtils.checkLong(params.get("parentId"), ErrorCode.PARENT_ID_ERROR));
		}
		String url = null;
		String authIden = null;
		String icon = null;
		Integer sort = null;
		switch (type) {
		case Menu.TYPE_DIRECTORY:
			url = "#";
			icon = params.get("icon");
			sort = CheckUtils.checkInt(params.get("sort"), ErrorCode.ME_SO_ERROR);
			if (CheckUtils.isNotNull(parentMenu)) {
				CheckUtils.checkEquals(parentMenu.getType(), Menu.TYPE_DIRECTORY, ErrorCode.ME_PA_TY_DIRECTORY);
				CheckUtils.checkNull(parentMenu.getParentMenu(), ErrorCode.ME_CANNOT_ADD_DIRECTORY);
			}
			break;
		case Menu.TYPE_VIEW:
			url = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("url"), ErrorCode.ME_UR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.ME_UR_EMPTY);
			authIden = CheckUtils.checkNotEmpty(params.get("authIden"), ErrorCode.ME_AU_EMPTY);
			sort = CheckUtils.checkInt(params.get("sort"), ErrorCode.ME_SO_ERROR);
			if (CheckUtils.isNotEquals(authIden, menu.getAuthIden()) || CheckUtils.isNull(menu.getProject()) || CheckUtils.isNotEquals(menu.getProject().getId(), projectId)) {
				CheckUtils.checkNull(menuDao.getByAuthIden(projectId, authIden), ErrorCode.ME_AU_EXISTENCE);
			}
			CheckUtils.checkTrue(CheckUtils.isNotNull(parentMenu) && (CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_DIRECTORY) || CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_VIEW)), ErrorCode.ME_PA_TY_DIRECTORY_OR_VIEW);
			break;
		case Menu.TYPE_BUTTON:
			url = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("url"), ErrorCode.ME_UR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.ME_UR_EMPTY);
			authIden = CheckUtils.checkNotEmpty(params.get("authIden"), ErrorCode.ME_AU_EMPTY);
			if (CheckUtils.isNotEquals(authIden, menu.getAuthIden()) || CheckUtils.isNull(menu.getProject()) || CheckUtils.isNotEquals(menu.getProject().getId(), projectId)) {
				CheckUtils.checkNull(menuDao.getByAuthIden(projectId, authIden), ErrorCode.ME_AU_EXISTENCE);
			}
			CheckUtils.checkTrue(CheckUtils.isNotNull(parentMenu) && (CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_VIEW) || CheckUtils.isEquals(parentMenu.getType(), Menu.TYPE_BUTTON)), ErrorCode.ME_PA_TY_VIEW_OR_BUTTON);
			break;
		default:
			throw new ResultException(ErrorCode.ME_TY_NOT_EXISTENCE);
		}
		Project project = CheckUtils.checkNotNull(projectService.getById(projectId), ErrorCode.PR_NOT_EXISTENCE);
		if (CheckUtils.isNotEquals(menu.getParentMenu(), parentMenu) || CheckUtils.isNotEquals(menu.getName(), name)) {
			CheckUtils.checkNull(menuDao.get(CheckUtils.isNull(parentMenu) ? 0L : parentMenu.getId(), name), ErrorCode.ME_NA_EXISTENCE);
		}
		Set<Long> childrenIdSet = this.listChildrenIds(menu.getId());
		CheckUtils.checkNotEquals(menu.getId(), CheckUtils.isNull(parentMenu) ? 0L : parentMenu.getId(), ErrorCode.PARENT_IS_MYSELF);
		CheckUtils.checkFalse(childrenIdSet.contains(CheckUtils.isNull(parentMenu) ? 0L : parentMenu.getId()), ErrorCode.PARENT_IS_CHILDREN);
		if (CheckUtils.isNotEquals(menu.getType(), type)) {
			CheckUtils.checkFalse(childrenIdSet.size() > 0, ErrorCode.ME_TY_NO_UPDATE);
		}
		menu.setProject(project);
		menu.setParentMenu(parentMenu);
		menu.setName(name);
		menu.setType(type);
		menu.setUrl(url);
		menu.setAuthIden(authIden);
		menu.setIcon(icon);
		menu.setSort(sort);
		menu.setModifyTime(LocalDateTime.now());
		menuDao.update(menu);
		reloadAuthFilterChainsService.reloadFilterChainDefinitions(project);
		reloadAuthFilterChainsService.clearAllAuthorizationCache();
		securitySdkService.saveLog("菜单", "修改菜单", LogType.UPDATE, menu.toString());
		return new AjaxResult(ErrorCode.SUCCESS, menu);
	}
	
	private Set<Long> listChildrenIds(long id) {
		Set<Long> set = new HashSet<Long>();
		List<Menu> list = menuDao.listByParentId(id);
		list.forEach(child -> {
			set.add(child.getId());
			set.addAll(listChildrenIds(child.getId()));
		});
		return set;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(String id) throws ResultException {
		return this.del(CheckUtils.checkId(id));
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(long id) throws ResultException {
		Menu menu = CheckUtils.checkNotNull(this.getById(id), ErrorCode.ME_NULL);
		Set<Long> idSet = this.listChildrenIds(id);
		idSet.add(id);
		roleMenuService.delByMenuId(idSet);
		menuDao.delById(idSet);
		reloadAuthFilterChainsService.reloadFilterChainDefinitions(menu.getProject());
		reloadAuthFilterChainsService.clearAllAuthorizationCache();
		securitySdkService.saveLog("菜单", "删除菜单", LogType.DELETE, menu.toString());
		return new AjaxResult(ErrorCode.SUCCESS);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> listAuthIdenByProjectIdAndId(long projectId, Collection<Long> ids) {
		if (CheckUtils.isEmpty(ids)) {
			return new ArrayList<String>();
		}
		return menuDao.listAuthIdenByProjectIdAndId(projectId, ids);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> listAuthIdenByProjectId(long projectId) {
		return menuDao.listAuthIdenByProjectId(projectId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> listById(Collection<Long> ids) {
		if (CheckUtils.isEmpty(ids)) {
			return new ArrayList<Menu>();
		}
		return menuDao.listById(ids);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<IndexMenu> getIndexMenu() {
		SecuritySdkUser user = securitySdkService.getCurrentUser();
		List<Long> menuIdList = new ArrayList<Long>();
		if (!user.getIsSuperuser()) {
			menuIdList = roleMenuService.listMenuIdByRoleId(userRoleService.listRoleIdByUserId(user.getId()));
		} else {
			menuIdList = menuDao.listIdAll();
		}
		return this.getIndexMenu(null, menuIdList);
	}
	
	private List<IndexMenu> getIndexMenu(Long parentId, List<Long> menuIdList) {
		List<IndexMenu> indexMenuList = new ArrayList<IndexMenu>();
		if (CheckUtils.isEmpty(menuIdList)) {
			return indexMenuList;
		}
		List<Menu> menuList = menuDao.listByParentIdAndId(CheckUtils.isNull(parentId) ? 0L : parentId, menuIdList);
		menuList.forEach(menu -> {
			if (CheckUtils.isEquals(menu.getType(), Menu.TYPE_BUTTON) || (CheckUtils.isNotNull(menu.getParentMenu()) && CheckUtils.isEquals(menu.getParentMenu().getType(), Menu.TYPE_VIEW)) || CheckUtils.isNull(menu.getProject())) {
				return;
			}
			IndexMenu indexMenu = new IndexMenu();
			indexMenu.setName(menu.getName());
			indexMenu.setUrl(menu.getProject().getProjectUrl() + menu.getUrl());
			indexMenu.setType(menu.getType());
			indexMenu.setIcon(menu.getIcon());
			indexMenu.setChildren(getIndexMenu(menu.getId(), menuIdList));
			indexMenuList.add(indexMenu);
		});
		return indexMenuList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> listAuthIdenByProjectIdAndUserId(long projectId, long userId) {
		List<Long> roleIdList = userRoleService.listRoleIdByUserId(userId);
		List<Long> menuIdList = roleMenuService.listMenuIdByRoleId(roleIdList);
		return this.listAuthIdenByProjectIdAndId(projectId, menuIdList);
	}
}
