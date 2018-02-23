package cn.szz.security.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import cn.szz.sdk.utils.CheckUtils;
import cn.szz.security.dao.LogDao;
import cn.szz.security.pojo.po.Log;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.PageData;
import cn.szz.security.service.LogService;
import cn.szz.security.service.ProjectService;
import cn.szz.security.service.UserService;
import cn.szz.security.utils.SuperUserUtils;

@Service
public class LogServiceImpl implements LogService {

	@Resource
	private LogDao logDao;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private TaskExecutor taskExecutor;
	
	@Override
	public void save(long projectId, long userId, String userIp, String name, String descr, int type, String content) {
		this.threadSave(projectId, userId, userIp, name, descr, type, content);
	}

	@Override
	public void save(long projectId, String userIp, String name, String descr, int type, String content) {
		this.threadSave(projectId, null, userIp, name, descr, type, content);
	}

	@Override
	public void save(long projectId, long userId, String name, String descr, int type, String content) {
		this.threadSave(projectId, userId, null, name, descr, type, content);
	}

	@Override
	public void save(long projectId, long userId, String userIp, String name, String descr, int type) {
		this.threadSave(projectId, userId, userIp, name, descr, type, null);
	}

	@Override
	public void save(long projectId, String name, String descr, int type, String content) {
		this.threadSave(projectId, null, null, name, descr, type, content);
	}

	@Override
	public void save(long projectId, long userId, String name, String descr, int type) {
		this.threadSave(projectId, userId, null, name, descr, type, null);
	}
	
	@Override
	public void save(long projectId, String userIp, String name, String descr, int type) {
		this.threadSave(projectId, null, userIp, name, descr, type, null);
	}

	@Override
	public void save(long projectId, String name, String descr, int type) {
		this.threadSave(projectId, null, null, name, descr, type, null);
	}

	private void threadSave(long projectId, Long userId, String userIp, String name, String descr, int type, String content) {
		if (SuperUserUtils.isSuperuserId(projectId)) {
			return;
		}
		final long fprojectId = projectId;
		final Long fuserId = userId;
		final String fuserIp = userIp;
		final String fname = name;
		final String fdescr = descr;
		final int ftype = type;
		final String fcontent = content;
		taskExecutor.execute(() -> {
			Log log = new Log();
			Project project = projectService.getById(fprojectId);
			if (CheckUtils.isNull(project)) {
				return;
			}
			if (CheckUtils.isEmpty(fname)) {
				return;
			}
			log.setProject(project);
			log.setName(fname);
			log.setType(ftype);
			if (CheckUtils.isNotNull(fuserId)) {
				User user = userService.getById(fuserId);
				if (CheckUtils.isNotNull(user)) {
					log.setUser(user);
				}
			}
			log.setDescr(fdescr);
			log.setUserIp(fuserIp);
			log.setContent(fcontent);
			log.setAddTime(LocalDateTime.now());
			logDao.save(log);
		});
	}

	@Override
	public PageData getByPage(String projectIdStr, String typeStr, PageData pageData) {
		if (CheckUtils.isNull(pageData)) {
			return new PageData(0, new ArrayList<Project>());
		}
		Long projectId = null;
		Integer type = null;
		if (CheckUtils.isLong(projectIdStr)) {
			projectId = Long.parseLong(projectIdStr);
		}
		if (CheckUtils.isInt(typeStr)) {
			type = Integer.parseInt(typeStr);
		}
		pageData.setRows(logDao.listRows(projectId, type, pageData));
		pageData.setTotal(logDao.getTotal(projectId, type, pageData));
		return pageData;
	}
}
