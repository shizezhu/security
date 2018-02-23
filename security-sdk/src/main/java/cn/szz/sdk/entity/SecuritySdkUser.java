package cn.szz.sdk.entity;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author Shi Zezhu
 * @date 2017年5月31日 下午2:28:41
 */
public class SecuritySdkUser implements Serializable {

	private static final long serialVersionUID = 8921811877474318468L;

	private Long id;
	
	private String username;
	
	private String password;
	
	private String realname;
	
	private String phone;
	
	private String email;
	
	private Boolean status;
	
	private Boolean isSuperuser;
	
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

	public Boolean getIsSuperuser() {
		return isSuperuser;
	}

	public void setIsSuperuser(Boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}

	@Override
	public String toString() {
		return "SecuritySdkUser [id=" + id + ", username=" + username + ", password=" + password + ", realname="
				+ realname + ", phone=" + phone + ", email=" + email + ", status=" + status + ", isSuperuser="
				+ isSuperuser + "]";
	}
}