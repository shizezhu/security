package cn.szz.security.utils;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.szz.security.pojo.Result;


/**
 * HTTP工具类
 *
 * @author Shi Zezhu
 * @date 2017年9月19日 上午11:35:03
 *
 */
public class HttpUtils {

	private static final String CHARSET = "UTF-8";
	
	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:34:40
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

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
		return StringUtils.isNotBlank(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest");
	}

	/**
	 * 发送数据
	 * 
	 * @param response
	 * @param ajaxResult
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午5:29:03
	 */
	public static void sendData(HttpServletResponse response, ErrorCode errorCode) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding(CHARSET);
			response.setContentType("application/json; charset=" + CHARSET);
			pw = response.getWriter();
			pw.println(new ObjectMapper().writeValueAsString(new Result(errorCode)));
			pw.flush();
		} catch (Exception e) {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 发送数据
	 * 
	 * @param response
	 * @param ajaxResult
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午5:29:03
	 */
	public static void sendData(HttpServletResponse response, Result result) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding(CHARSET);
			response.setContentType("application/json; charset=" + CHARSET);
			pw = response.getWriter();
			pw.println(new ObjectMapper().writeValueAsString(result));
			pw.flush();
		} catch (Exception e) {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 发送数据
	 * 
	 * @param response
	 * @param data
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午5:29:03
	 */
	public static void sendData(HttpServletResponse response, String data) {
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding(CHARSET);
			response.setContentType("application/json; charset=" + CHARSET);
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
	 * 获取UA信息
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年11月17日 上午10:43:39
	 */
	public static String getUa(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * 获取referer
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年11月17日 上午10:44:45
	 */
	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}

	/**
	 * 获取URI
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年11月17日 上午10:44:45
	 */
	public static String getUri(HttpServletRequest request) {
		return request.getRequestURI();
	}

	/**
	 * 获取参数列表 key1=value1&key2=value2
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年11月17日 上午10:44:45
	 */
	public static String getParamsStr(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String[]> entry : params.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			for (int i = 0; i < values.length; i++) {
				sb.append(key).append("=").append(values[i]).append("&");
			}
		}
		String str = sb.toString();
		if ((str).endsWith("&")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 判断是否手机端
	 * 
	 * @param ua
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月6日 下午3:16:02
	 */
	public static boolean isMoblie(String ua) {
		if (CheckUtils.isEmpty(ua)) {
			return false;
		}
		ua = ua.toLowerCase();
		String[] agents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
				"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
				"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
				"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech",
				"gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320",
				"176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz",
				"brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
				"keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-",
				"moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port", "prox",
				"qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal",
				"smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", /* "tosh", */ "tsm-", "upg1", "upsi", "vk-v",
				"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		for (String agent : agents) {
			if (ua.indexOf(agent) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为iphone
	 * 
	 * @param ua
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月6日 下午3:21:12
	 */
	public static boolean isIphone(String ua) {
		if (CheckUtils.isEmpty(ua)) {
			return false;
		}
		ua = ua.toLowerCase();
		String[] agents = { "iphone", "phone" };
		for (String agent : agents) {
			if (ua.indexOf(agent) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断微信浏览器
	 * 
	 * @param ua
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月14日 上午11:47:35
	 */
	public static boolean isWechat(String ua) {
		if (CheckUtils.isEmpty(ua)) {
			return false;
		}
		ua = ua.toLowerCase();
		String[] agents = { "micromessenger" };
		for (String agent : agents) {
			if (ua.indexOf(agent) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断微信浏览器 比较版本
	 * 
	 * @param ua
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月12日 下午5:18:37
	 */
	public static boolean isWechatThanVersion(String ua, String version) {
		if (CheckUtils.isEmpty(ua) || !isWechat(ua) || CheckUtils.isNotReg(version, "\\d+(.\\d+)?(.\\d+)?")) {
			return false;
		}
		ua = ua.toLowerCase();
		Pattern pattern = Pattern.compile("micromessenger/\\d+(.\\d+)?(.\\d+)?");
        Matcher matcher = pattern.matcher(ua);
        if (!matcher.find()) {
        	return false;
        }
        String wechatVersion = matcher.group();
        wechatVersion = wechatVersion.replace("micromessenger/", "");
        String[] versionArray = version.split("\\.");
        String[] wechatVersionArray = wechatVersion.split("\\.");
        List<Integer> versionList = new ArrayList<Integer>();
        List<Integer> wechatVersionList = new ArrayList<Integer>();
        for (int i = 0; i < versionArray.length; i++) {
        	versionList.add(Integer.parseInt(versionArray[i]));
        }
        for (int i = 0; i < wechatVersionArray.length; i++) {
        	wechatVersionList.add(Integer.parseInt(wechatVersionArray[i]));
        }
        while (versionList.size() < 3) {
        	versionList.add(0);
        }
        while (wechatVersionList.size() < 3) {
        	wechatVersionList.add(0);
        }
        for (int i = 0; i < 3; i++) {
             if (versionList.get(i) > wechatVersionList.get(i)) {
             	return false;
             }
             if (versionList.get(i) < wechatVersionList.get(i)) {
             	return true;
             }
        }
		return true;
	}
	
	/**
	 * 判断是否手机端
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月6日 下午3:16:16
	 */
	public static boolean isMoblie(HttpServletRequest request) {
		return isMoblie(getUa(request));
	}
	
	/**
	 * 判断是否为iphone
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月6日 下午3:21:28
	 */
	public static boolean isIphone(HttpServletRequest request) {
		return isIphone(getUa(request));
	}

	/**
	 * 判断微信浏览器
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月14日 上午11:47:35
	 */
	public static boolean isWechat(HttpServletRequest request) {
		return isWechat(getUa(request));
	}
	
	/**
	 * 判断微信浏览器 比较版本
	 * 
	 * @param ua
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月12日 下午5:18:37
	 */
	public static boolean isWechatThanVersion(HttpServletRequest request, String version) {
		return isWechatThanVersion(getUa(request), version);
	}
	
	/**
	 * 获取参数map
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @throws UnsupportedEncodingException
	 * @date 2017年12月6日 下午3:16:36
	 */
	public static Map<String, String> getMapFromParameter(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> params = new HashMap<String, String>();
		for (Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = "";
			String[] values = entry.getValue();
			for (int i = 0; i < values.length; i++) {
				String v = values[i];
				try {
					if (v.equals(new String(v.getBytes("iso8859-1"), "iso8859-1"))) {
						v = new String(v.getBytes("iso8859-1"), CHARSET);
					}
				} catch (UnsupportedEncodingException e) {
				}
				value = (i == values.length - 1) ? value + v : value + v + ",";
			}
			params.put(key, value);
		}
		return params;
	}

	public static String getParameter(HttpServletRequest request, String key) {
		String value = request.getParameter(key);
		if (CheckUtils.isEmpty(value)) {
			return "";
		}
		try {
			if (value.equals(new String(value.getBytes("iso8859-1"), "iso8859-1"))) {
				value = new String(value.getBytes("iso8859-1"), CHARSET);
			}
		} catch (UnsupportedEncodingException e) {
		}
		return value;
	}

	/**
	 * 获取请求方式
	 * 
	 * @param request
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年12月7日 下午12:25:44
	 */
	public static String getMethod(HttpServletRequest request) {
		return request.getMethod();
	}
}
