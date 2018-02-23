package cn.szz.security.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.RoleDao;
import cn.szz.security.dao.db.RoleDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.vo.PageData;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Resource
	private RoleDb roleDb;
	
	@Override
	public List<Role> listAll() {
		return roleDb.list(null);
	}

	@Override
	public List<Role> listRows(PageData pageData) {
		return roleDb.list(new MybatisParams(pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(PageData pageData) {
		return roleDb.count(new MybatisParams(pageData.getSearch()));
	}
	
	@Override
	public Role getByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return roleDb.get(new MybatisParams(params));
	}

	@Override
	public int save(Role role) {
		return roleDb.save(role);
	}

	@Override
	public int delById(long id) {
		return roleDb.delById(id);
	}

	@Override
	public Role getById(long id) {
		return roleDb.getById(id);
	}

	@Override
	public int update(Role role) {
		return roleDb.update(role);
	}

	@Override
	public List<Role> listById(Collection<Long> ids) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("ids", ids);
		return roleDb.list(new MybatisParams(params));
	}

	@Override
	public List<Role> listByUserId(long userId) {
		return roleDb.listByUserId(userId);
	}
}
