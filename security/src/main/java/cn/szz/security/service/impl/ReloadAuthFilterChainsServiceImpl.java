package cn.szz.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;

import cn.szz.sdk.rmi.service.ReloadHessianService;
import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.service.ProjectService;
import cn.szz.security.service.ReloadAuthFilterChainsService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;
import cn.szz.security.utils.PropertiesUtils;


@Service
public class ReloadAuthFilterChainsServiceImpl implements ReloadAuthFilterChainsService {

	private static final Logger logger = Logger.getLogger(ReloadAuthFilterChainsServiceImpl.class);
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ReloadHessianService reloadHessianService;
	
	@Resource
	private TaskExecutor taskExecutor;
	
	@Override
	public void reloadFilterChainDefinitions(Project project) {
		final Project pproject = project;
		taskExecutor.execute(() -> {
			try {
				CheckUtils.checkNotNull(pproject, ErrorCode.PR_NULL);
				if (CheckUtils.isEquals(CheckUtils.checkLong(PropertiesUtils.getProperty(PropertiesUtils.PROJECT_ID), ErrorCode.LOCAL_PROJECT_ID_ERROR), pproject.getId())) {
					reloadHessianService.reloadFilterChainDefinitions();
					return;
				}
				if (!pproject.getStatus()) {
					return;
				}
				String url = pproject.getProjectUrl() + pproject.getReloadAuthUrl();
				HessianProxyFactory factory = new HessianProxyFactory();
				ReloadHessianService remote = (ReloadHessianService) factory.create(ReloadHessianService.class, url);
				remote.reloadFilterChainDefinitions();
			} catch (ResultException e) {
				logger.error("调用远程刷新权限服务异常", e);
			} catch (Exception e) {
				logger.error("调用远程刷新权限服务异常, 项目名称:" + pproject.getName() + ", 代理地址:" + pproject.getProjectUrl() + pproject.getReloadAuthUrl(), e);
			}
		});
	}

	@Override
	public void clearAllAuthorizationCache() {
		taskExecutor.execute(() -> {
			reloadHessianService.clearAllAuthorizationCache();
			List<Project> projectList = projectService.listAll();
			HessianProxyFactory factory = new HessianProxyFactory();
			projectList.forEach(project -> {
				if (CheckUtils.isEquals(CheckUtils.checkLong(PropertiesUtils.getProperty(PropertiesUtils.PROJECT_ID), ErrorCode.LOCAL_PROJECT_ID_ERROR), project.getId())) {
					return;
				}
				if (!project.getStatus()) {
					return;
				}
				String url = project.getProjectUrl() + project.getReloadAuthUrl();
				try {
					ReloadHessianService remote = (ReloadHessianService) factory.create(ReloadHessianService.class, url);
					remote.clearAllAuthorizationCache();
				} catch (Exception e) {
					logger.error("调用远程清除授权服务异常, 远程接口URL:" + url, e);
					return;
				}
			});
		});
	}
}
