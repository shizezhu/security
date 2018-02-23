package cn.szz.sdk.utils;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.szz.sdk.entity.SecuritySdkUser;

public class CommUtils {

	/**
	 * 判断是否ajax请求
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:35:16
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		return requestType != null && requestType != "" && requestType.equalsIgnoreCase("XMLHttpRequest");
	}
	
	/**
	 * 发送数据
	 * 
	 * @param response
	 * @param ajaxResult
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午5:29:03
	 */
	public static void sendData(HttpServletResponse response, String charset, String data) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding(charset);
			response.setContentType("application/json; charset=" + charset);
			pw = response.getWriter();
			pw.println(data);
			pw.flush();
		} catch (Exception e) {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	/**
	 * 创建User对象
	 * 
	 * @param data
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 下午3:16:28
	 */
	public static SecuritySdkUser createUser(Map<String, Object> data) {
		if (CheckUtils.isEmpty(data)) {
			return null;
		}
		SecuritySdkUser user = new SecuritySdkUser();
		user.setId(CheckUtils.isNull(data.get("id")) ? null : (Long) data.get("id"));
		user.setUsername(CheckUtils.isNull(data.get("username")) ? null : String.valueOf(data.get("username")));
		user.setPassword(CheckUtils.isNull(data.get("password")) ? null : String.valueOf(data.get("password")));
		user.setRealname(CheckUtils.isNull(data.get("realname")) ? null : String.valueOf(data.get("realname")));
		user.setPhone(CheckUtils.isNull(data.get("phone")) ? null : String.valueOf(data.get("phone")));
		user.setEmail(CheckUtils.isNull(data.get("email")) ? null : String.valueOf(data.get("email")));
		user.setStatus(CheckUtils.isNull(data.get("status")) ? false : Boolean.parseBoolean(String.valueOf(data.get("status"))));
		user.setIsSuperuser(CheckUtils.isNull(data.get("isSuperuser")) ? false : Boolean.parseBoolean(String.valueOf(data.get("isSuperuser"))));
		return user;
	}

}
