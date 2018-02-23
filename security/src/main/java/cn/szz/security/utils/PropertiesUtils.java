package cn.szz.security.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.szz.security.exception.ResultException;


/**
 * Properties工具类
 * 
 * @author Shi Zezhu
 * @date 2016年5月25日 上午10:31:12
 */
public class PropertiesUtils {

	private static final Logger logger = Logger.getLogger(PropertiesUtils.class);
	
	public static final String PROJECT_ID = "project.id";
	
	private static Properties prop = null;
	
	static {
		prop = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
			prop.load(is);
		} catch (IOException e) {
			logger.error("加载配置文件异常", e);
			throw new ResultException(ErrorCode.PROPERTIES_ERROR);
		} finally {
			if (is != null) {
				try {is.close();} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * 获取单个
	 * 
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	public static String getProperty(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		return prop.getProperty(key);
	}

	/**
	 * 获取多个
	 * 
	 * @param keys
	 * @return map<key, value>
	 * @throws IOException
	 */
	public static Map<String, String> getProperties(String... keys) {
		Map<String, String> map = new HashMap<String, String>();
		if (keys == null || keys.length == 0) {
			return map;
		}
		for (String key : keys) {
			String value = getProperty(key);
			map.put(key, value);
		}
		return map;
	}
}
