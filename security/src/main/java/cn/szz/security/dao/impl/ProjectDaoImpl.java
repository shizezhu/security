package cn.szz.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.ProjectDao;
import cn.szz.security.dao.db.ProjectDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.PageData;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Resource
	private ProjectDb projectDb;
	
	@Override
	public List<Project> listAll() {
		return projectDb.list(null);
	}

	@Override
	public Project getByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return projectDb.get(new MybatisParams(params));
	}

	@Override
	public List<Project> listRows(PageData pageData) {
		return projectDb.list(new MybatisParams(pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(PageData pageData) {
		return projectDb.count(new MybatisParams(pageData.getSearch()));
	}

	@Override
	public int save(Project project) {
		return projectDb.save(project);
	}

	@Override
	public int delById(long id) {
		return projectDb.delById(id);
	}

	@Override
	public Project getById(long id) {
		return projectDb.getById(id);
	}

	@Override
	public int update(Project project) {
		return projectDb.update(project);
	}
}
