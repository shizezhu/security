package cn.szz.security.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 *
 * @author Shi Zezhu
 * @date 2018年1月17日 下午6:04:28
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 2471914717471022362L;

	private Long id;

    private String username;

    private String password;

    private String realname;

    private String phone;

    private String email;

    private Boolean status;

    private LocalDateTime addTime;

    private LocalDateTime modifyTime;
    
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", phone=" + phone + ", email=" + email + ", status=" + status + ", addTime=" + addTime
				+ ", modifyTime=" + modifyTime + ", roleList=" + roleList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifyTime == null) ? 0 : modifyTime.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((roleList == null) ? 0 : roleList.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (roleList == null) {
			if (other.roleList != null)
				return false;
		} else if (!roleList.equals(other.roleList))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}