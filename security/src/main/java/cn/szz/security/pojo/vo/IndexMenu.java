package cn.szz.security.pojo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装主页菜单
 *
 * @author Shi Zezhu
 * @date 2017年9月23日 下午2:31:31
 *
 */
public class IndexMenu {

	private String name;
	
	private Integer type;
	
	private String url;
	
	private String icon;
	
	private List<IndexMenu> children;

	public IndexMenu() {
		super();
		this.children = new ArrayList<IndexMenu>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<IndexMenu> getChildren() {
		return children;
	}

	public void setChildren(List<IndexMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "IndexMenu [name=" + name + ", type=" + type + ", url=" + url + ", icon=" + icon + ", children="
				+ children + "]";
	}
}
