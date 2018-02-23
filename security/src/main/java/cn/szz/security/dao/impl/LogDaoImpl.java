package cn.szz.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.szz.security.dao.LogDao;
import cn.szz.security.dao.db.LogDb;
import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.Log;
import cn.szz.security.pojo.vo.PageData;

@Repository
public class LogDaoImpl implements LogDao {

	@Resource
	private LogDb logDb;
	
	@Override
	public int save(Log log) {
		return logDb.save(log);
	}

	@Override
	public List<Log> listRows(Long projectId, Integer type, PageData pageData) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("type", type);
		return logDb.list(new MybatisParams(params, pageData.getSearch(), pageData.getSort(), pageData.getOrder(), pageData.getOffset(), pageData.getLimit()));
	}

	@Override
	public long getTotal(Long projectId, Integer type, PageData pageData) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("type", type);
		return logDb.count(new MybatisParams(params, pageData.getSearch()));
	}
}
