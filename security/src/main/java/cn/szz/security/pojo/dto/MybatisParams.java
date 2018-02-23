package cn.szz.security.pojo.dto;

import java.util.Map;

/**
 * mybatis参数封装
 *
 * @author Shi Zezhu
 * @date 2018年2月2日 上午10:43:50
 *
 */
public class MybatisParams {

	private Map<String, ? extends Object> params; //条件参数
	
	private String search; //搜索参数
	
	private String sort; //排序字段
	
	private String order; //排序规则
	
	private Integer firstResult; //分页开始
	
	private Integer maxResult; //分页条数

	public MybatisParams() {
		super();
	}

	public MybatisParams(Map<String, ? extends Object> params) {
		super();
		this.params = params;
	}
	
	public MybatisParams(String search) {
		super();
		this.search = search;
	}
	
	public MybatisParams(Map<String, ? extends Object> params, String search) {
		super();
		this.params = params;
		this.search = search;
	}
	
	public MybatisParams(String sort, String order) {
		super();
		this.sort = sort;
		this.order = order;
	}

	public MybatisParams(Map<String, ? extends Object> params, String sort, String order) {
		super();
		this.params = params;
		this.sort = sort;
		this.order = order;
	}
	
	public MybatisParams(String search, String sort, String order) {
		super();
		this.search = search;
		this.sort = sort;
		this.order = order;
	}
	
	public MybatisParams(Integer firstResult, Integer maxResult) {
		super();
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}
	
	public MybatisParams(Map<String, ? extends Object> params, String sort, String order, Integer firstResult,
			Integer maxResult) {
		super();
		this.params = params;
		this.sort = sort;
		this.order = order;
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}
	
	public MybatisParams(String sort, String order, Integer firstResult, Integer maxResult) {
		super();
		this.sort = sort;
		this.order = order;
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}
	
	public MybatisParams(String search, Integer firstResult, Integer maxResult) {
		super();
		this.search = search;
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}

	public MybatisParams(String search, String sort, String order, Integer firstResult, Integer maxResult) {
		super();
		this.search = search;
		this.sort = sort;
		this.order = order;
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}
	
	public MybatisParams(Map<String, ? extends Object> params, String search, String sort, String order,
			Integer firstResult, Integer maxResult) {
		super();
		this.params = params;
		this.search = search;
		this.sort = sort;
		this.order = order;
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}

	public Map<String, ? extends Object> getParams() {
		return params;
	}

	public void setParams(Map<String, ? extends Object> params) {
		this.params = params;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	@Override
	public String toString() {
		return "MybatisParams [params=" + params + ", search=" + search + ", sort=" + sort + ", order=" + order
				+ ", firstResult=" + firstResult + ", maxResult=" + maxResult + "]";
	}
}
