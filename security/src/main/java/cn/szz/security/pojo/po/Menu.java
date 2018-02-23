package cn.szz.security.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单
 *
 * @author Shi Zezhu
 * @date 2018年1月17日 下午6:04:28
 *
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -3120120313824498757L;
	
	public static final int TYPE_DIRECTORY = 1;
	public static final int TYPE_VIEW = 2;
	public static final int TYPE_BUTTON = 3;

    private Long id;

    private Project project;

    private Integer type;

    private Menu parentMenu;

    private String name;

    private String url;

    private String authIden;

    private String icon;

    private Integer sort;

    private LocalDateTime addTime;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAuthIden() {
        return authIden;
    }

    public void setAuthIden(String authIden) {
        this.authIden = authIden == null ? null : authIden.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

	@Override
	public String toString() {
		return "Menu [id=" + id + ", project=" + project + ", type=" + type + ", parentMenu=" + parentMenu + ", name="
				+ name + ", url=" + url + ", authIden=" + authIden + ", icon=" + icon + ", sort=" + sort + ", addTime="
				+ addTime + ", modifyTime=" + modifyTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((authIden == null) ? 0 : authIden.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifyTime == null) ? 0 : modifyTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentMenu == null) ? 0 : parentMenu.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (authIden == null) {
			if (other.authIden != null)
				return false;
		} else if (!authIden.equals(other.authIden))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifyTime == null) {
			if (other.modifyTime != null)
				return false;
		} else if (!modifyTime.equals(other.modifyTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentMenu == null) {
			if (other.parentMenu != null)
				return false;
		} else if (!parentMenu.equals(other.parentMenu))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}