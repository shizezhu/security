package cn.szz.security.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.UserDao;
import cn.szz.security.dao.db.UserDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.PageData;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private UserDb userDb;
	
	@Override
	public List<User> listAll() {
		return userDb.list(null);
	}
	
	@Override
	public List<User> listByStatus(boolean status) {
		Map<String, Boolean> params = new HashMap<String, Boolean>();
		params.put("status", status);
		return userDb.list(new MybatisParams(params));
	}

	@Override
	public List<User> listRows(PageData pageData) {
		return userDb.list(new MybatisParams(pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(PageData pageData) {
		return userDb.count(new MybatisParams(pageData.getSearch()));
	}
	
	@Override
	public User getByUsername(String username) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		return userDb.get(new MybatisParams(params));
	}

	@Override
	public List<User> listById(Collection<Long> ids) {
		Map<String, Collection<Long>> params = new HashMap<String, Collection<Long>>();
		params.put("ids", ids);
		return userDb.list(new MybatisParams(params));
	}

	@Override
	public int save(User user) {
		return userDb.save(user);
	}

	@Override
	public int delById(long id) {
		return userDb.delById(id);
	}

	@Override
	public User getById(long id) {
		return userDb.getById(id);
	}

	@Override
	public int update(User user) {
		return userDb.update(user);
	}
}
