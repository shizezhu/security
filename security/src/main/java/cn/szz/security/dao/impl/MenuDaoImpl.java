package cn.szz.security.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.MenuDao;
import cn.szz.security.dao.db.MenuDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.vo.PageData;

@Repository
public class MenuDaoImpl implements MenuDao {

	@Resource
	private MenuDb menuDb;
	
	@Override
	public List<Menu> listAll() {
		return menuDb.list(null);
	}
	
	@Override
	public List<Menu> listByProjectId(long projectId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("projectId", projectId);
		return menuDb.list(new MybatisParams(params));
	}
	
	@Override
	public List<Long> listIdAll() {
		return menuDb.listId(null);
	}
	
	@Override
	public List<Menu> listRows(PageData pageData) {
		return menuDb.list(new MybatisParams(pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(PageData pageData) {
		return menuDb.count(new MybatisParams(pageData.getSearch()));
	}

	@Override
	public List<Menu> listRows(PageData pageData, long parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return menuDb.list(new MybatisParams(params, pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(PageData pageData, long parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return menuDb.count(new MybatisParams(params, pageData.getSearch()));
	}
	
	@Override
	public Menu get(long parentId, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("parentId", parentId);
		return menuDb.get(new MybatisParams(params));
	}
	
	@Override
	public Menu getByAuthIden(long projectId, String authIden) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("authIden", authIden);
		return menuDb.get(new MybatisParams(params));
	}

	@Override
	public int delByParentId(long parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return menuDb.del(new MybatisParams(params));
	}

	@Override
	public List<Menu> listByParentId(long parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return menuDb.list(new MybatisParams(params));
	}

	@Override
	public List<String> listAuthIdenByProjectIdAndId(long projectId, Collection<Long> ids) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("ids", ids);
		return menuDb.listAuthIden(new MybatisParams(params));
	}
	
	@Override
	public List<String> listAuthIdenByProjectId(long projectId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		return menuDb.listAuthIden(new MybatisParams(params));
	}

	@Override
	public List<Menu> listById(Collection<Long> ids) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("ids", ids);
		return menuDb.list(new MybatisParams(params));
	}

	@Override
	public List<Menu> listByParentIdAndId(long parentId, Collection<Long> ids) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("ids", ids);
		return menuDb.list(new MybatisParams(params, "sort", "asc"));
	}

	@Override
	public int save(Menu menu) {
		return menuDb.save(menu);
	}

	@Override
	public int delById(long id) {
		return menuDb.delById(id);
	}

	@Override
	public int delById(Collection<Long> ids) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("ids", ids);
		return menuDb.del(new MybatisParams(params));
	}

	@Override
	public Menu getById(long id) {
		return menuDb.getById(id);
	}

	@Override
	public int update(Menu menu) {
		return menuDb.update(menu);
	}
}
