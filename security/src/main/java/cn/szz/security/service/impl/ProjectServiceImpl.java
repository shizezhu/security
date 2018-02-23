package cn.szz.security.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.szz.sdk.SecuritySdkService;
import cn.szz.sdk.utils.LogType;
import cn.szz.security.dao.ProjectDao;
import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.ProjectService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private SecuritySdkService securitySdkService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Project> listAll() {
		return projectDao.listAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Project getById(String id) {
		if (CheckUtils.isNotId(id)) {
			return null;
		}
		return this.getById(Long.parseLong(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Project getById(long id) {
		return projectDao.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public PageData getByPage(PageData pageData) {
		if (CheckUtils.isNull(pageData)) {
			return new PageData(0, new ArrayList<Project>());
		}
		pageData.setRows(projectDao.listRows(pageData));
		pageData.setTotal(projectDao.getTotal(pageData));
		return pageData;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult save(Map<String, String> params) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		String name = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.PR_NA_EMPTY), CheckUtils.P_NA_PATTERN, ErrorCode.PR_NA_FORMAT);
		String projectUrl = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("projectUrl"), ErrorCode.PR_PUR_EMPTY), CheckUtils.URL_PATTERN, ErrorCode.PR_PUR_ERROR);
		String reloadAuthUrl = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("reloadAuthUrl"), ErrorCode.PR_PRUR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.PR_PRUR_ERROR);
		boolean status = CheckUtils.checkStatus(params.get("status"));
		CheckUtils.checkNull(projectDao.getByName(name), ErrorCode.PR_NA_EXISTENCE);
		Project project = new Project();
		project.setName(name);
		project.setProjectUrl(projectUrl);
		project.setReloadAuthUrl(reloadAuthUrl);
		project.setStatus(status);
		project.setAddTime(LocalDateTime.now());
		projectDao.save(project);
		securitySdkService.saveLog("项目", "保存项目", LogType.SAVE, project.toString());
		return new AjaxResult(ErrorCode.SUCCESS, project);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult update(Map<String, String> params) throws ResultException {
		CheckUtils.checkNotEmpty(params, ErrorCode.PARAM_EMPTY);
		Project project = CheckUtils.checkNotNull(this.getById(CheckUtils.checkId(params.get("id"))), ErrorCode.PR_NULL);
		String name = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("name"), ErrorCode.PR_NA_EMPTY), CheckUtils.P_NA_PATTERN, ErrorCode.PR_NA_FORMAT);
		String projectUrl = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("projectUrl"), ErrorCode.PR_PUR_EMPTY), CheckUtils.URL_PATTERN, ErrorCode.PR_PUR_ERROR);
		String reloadAuthUrl = CheckUtils.checkReg(CheckUtils.checkNotEmpty(params.get("reloadAuthUrl"), ErrorCode.PR_PRUR_EMPTY), CheckUtils.URI_PATTERN, ErrorCode.PR_PRUR_ERROR);
		boolean status = CheckUtils.checkStatus(params.get("status"));
		if (CheckUtils.isNotEquals(project.getName(), name)) {
			CheckUtils.checkNull(projectDao.getByName(name), ErrorCode.PR_NA_EXISTENCE);
		}
		project.setName(name);
		project.setProjectUrl(projectUrl);
		project.setReloadAuthUrl(reloadAuthUrl);
		project.setStatus(status);
		projectDao.update(project);
		securitySdkService.saveLog("项目", "修改项目", LogType.UPDATE, project.toString());
		return new AjaxResult(ErrorCode.SUCCESS, project);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(String id) throws ResultException {
		return this.del(CheckUtils.checkId(id));
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AjaxResult del(long id) {
		projectDao.delById(id);
		securitySdkService.saveLog("项目", "删除项目", LogType.DELETE, String.valueOf(id));
		return new AjaxResult(ErrorCode.SUCCESS); 
	}

}
