package cn.szz.security.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目
 *
 * @author Shi Zezhu
 * @date 2018年1月17日 下午6:04:28
 *
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 7555884554120388067L;

	private Long id;

    private String name;

    private String projectUrl;

    private String reloadAuthUrl;

    private Boolean status;

    private LocalDateTime addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl == null ? null : projectUrl.trim();
    }

    public String getReloadAuthUrl() {
        return reloadAuthUrl;
    }

    public void setReloadAuthUrl(String reloadAuthUrl) {
        this.reloadAuthUrl = reloadAuthUrl == null ? null : reloadAuthUrl.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", projectUrl=" + projectUrl + ", reloadAuthUrl="
				+ reloadAuthUrl + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectUrl == null) ? 0 : projectUrl.hashCode());
		result = prime * result + ((reloadAuthUrl == null) ? 0 : reloadAuthUrl.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Project other = (Project) obj;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectUrl == null) {
			if (other.projectUrl != null)
				return false;
		} else if (!projectUrl.equals(other.projectUrl))
			return false;
		if (reloadAuthUrl == null) {
			if (other.reloadAuthUrl != null)
				return false;
		} else if (!reloadAuthUrl.equals(other.reloadAuthUrl))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}