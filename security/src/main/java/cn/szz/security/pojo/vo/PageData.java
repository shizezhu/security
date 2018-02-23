package cn.szz.security.pojo.vo;

import java.io.Serializable;

/**
 * 封装Bootstrap-table分页请求数据
 * 
 * @author Shi Zezhu
 * @date 2017年4月19日 下午7:54:10
 */
public class PageData implements Serializable {

	private static final long serialVersionUID = -4433986995943705205L;

	private String sort; //排序字段
	
	private String order; //排序方式
	
	private int limit; // firstResult
	
	private int offset; // maxResult
	
	private String search;// 搜索关键字
	
	private long total; //总数
	
	private Object rows; //数据

	public PageData() {
		super();
	}
	
	public PageData(String sort, String order, int limit, int offset, String search) {
		super();
		this.sort = sort;
		this.order = order;
		this.limit = limit;
		this.offset = offset;
		this.search = search;
	}

	public PageData(long total, Object rows) {
		super();
		this.total = total;
		this.rows = rows;
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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageData [sort=" + sort + ", order=" + order + ", limit=" + limit + ", offset=" + offset + ", search="
				+ search + ", total=" + total + ", rows=" + rows + "]";
	}
}
