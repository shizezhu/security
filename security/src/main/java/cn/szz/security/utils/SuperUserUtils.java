package cn.szz.security.utils;

import java.time.LocalDateTime;

import cn.szz.security.pojo.po.User;

/**
 * 超级用户工具类
 *
 * @author Shi Zezhu
 * @date 2017年9月23日 下午5:42:45
 *
 */
public class SuperUserUtils {

	private static final long ID = -9999999999L;
	
	public static User createSuperUser(String username) {
		User user = new User();
		user.setId(ID);
		user.setUsername(username);
		user.setRealname("超级用户");
		user.setStatus(true);
		user.setEmail(PropertiesUtils.getProperty("mail.username"));
		user.setAddTime(LocalDateTime.now());
		user.setModifyTime(LocalDateTime.now());
		return user;
	}
	
	public static boolean isSuperuserId(long id) {
		return CheckUtils.isEquals(ID, id);
	}
}
