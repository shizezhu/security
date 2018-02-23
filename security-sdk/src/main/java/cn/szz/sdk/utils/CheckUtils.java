package cn.szz.sdk.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import cn.szz.sdk.exception.SecuritySdkException;


/**
 * 验证数据工具类
 *
 * @author Shi Zezhu
 * @date 2018年1月17日 下午4:10:50
 *
 */
public class CheckUtils {

	public static final String NUMBER_PATTERN = "[+-]?\\d+";
	public static final String INT_PATTERN = "[+-]?\\d{1,8}";
	public static final String LONG_PATTERN = "[+-]?\\d{1,18}";
	public static final String STATUS_PATTERN = "[01]";
	
	public static boolean isNull(Object object) {
		return object == null;
	}
	
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}
	
	public static <T> T ifNull(T arg1, T arg2) {
		return isNotNull(arg1) ? arg1 : arg2;
	}
	
	public static void checkNull(Object object, String message) throws SecuritySdkException {
		if (isNotNull(object)) 
			throw new SecuritySdkException(message);
	}
	
	public static <T> T checkNotNull(T t, String message) throws SecuritySdkException {
		if (isNull(t)) 
			throw new SecuritySdkException(message);
		return t;
	}
	
	public static boolean isEmpty(Collection<?> collection) {
		return isNull(collection) || collection.isEmpty();
	}
	
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	public static <T> Collection<T> ifEmpty(Collection<T> arg1, Collection<T> arg2) {
		return isNotEmpty(arg1) ? arg1 : arg2;
	}
	
	public static void checkEmpty(Collection<?> collection, String message) throws SecuritySdkException {
		if (isNotEmpty(collection)) 
			throw new SecuritySdkException(message);
	}
	
	public static <T> Collection<T> checkNotEmpty(Collection<T> collection, String message) throws SecuritySdkException {
		if (isEmpty(collection)) 
			throw new SecuritySdkException(message);
		return collection;
	}
	
	public static boolean isEmpty(Object[] array) {
		return isNull(array) || array.length == 0;
	}
	
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}
	
	public static <T> T[] ifEmpty(T[] arg1, T[] arg2) {
		return isNotEmpty(arg1) ? arg1 : arg2;
	}
	
	public static void checkEmpty(Object[] array, String message) throws SecuritySdkException {
		if (isNotEmpty(array)) 
			throw new SecuritySdkException(message);
	}
	
	public static <T> T[] checkNotEmpty(T[] array, String message) throws SecuritySdkException {
		if (isEmpty(array)) 
			throw new SecuritySdkException(message);
		return array;
	}
	
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}
	
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
	
	public static <K, V> Map<K, V> ifEmpty(Map<K, V> arg1, Map<K, V> arg2) {
		return isNotEmpty(arg1) ? arg1 : arg2;
	}
	
	public static void checkEmpty(Map<?, ?> map, String message) throws SecuritySdkException {
		if (isNotEmpty(map)) 
			throw new SecuritySdkException(message);
	}
	
	public static <T, V> Map<T, V> checkNotEmpty(Map<T, V> map, String message) throws SecuritySdkException {
		if (isEmpty(map)) 
			throw new SecuritySdkException(message);
		return map;
	}
	
	public static boolean isEmpty(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
		    return true;
		}
		for (int i = 0; i < strLen; i++) {
		    if (Character.isWhitespace(str.charAt(i)) == false) {
		        return false;
		    }
		}
		return true;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static String ifEmpty(String arg1, String arg2) {
		return isNotEmpty(arg1) ? arg1 : arg2;
	}
	
	public static void checkEmpty(String str, String message) throws SecuritySdkException {
		if (isNotEmpty(str)) 
			throw new SecuritySdkException(message);
	}
	
	public static String checkNotEmpty(String str, String message) throws SecuritySdkException {
		if (isEmpty(str)) 
			throw new SecuritySdkException(message);
		return str.trim();
	}
	
	public static boolean isReg(String str, String reg) {
		return isNotNull(reg) && isNotNull(str) && str.matches(reg);
	}
	
	public static boolean isNotReg(String str, String reg) {
		return !isReg(str, reg);
	}
	
	public static String ifNotReg(String arg1, String reg, String arg2) {
		return isReg(arg1, reg) ? arg1 : arg2;
	}
	
	public static String checkReg(String str, String reg, String message) throws SecuritySdkException {
		if (isNull(reg))
			throw new SecuritySdkException("正则表达式不能为空");
		if (isNotReg(str, reg)) {
			throw new SecuritySdkException(message);
		}
		return str;
	}
	
	public static void checkNotReg(String str, String reg, String message) throws SecuritySdkException {
		if (isNull(reg))
			throw new SecuritySdkException("正则表达式不能为空");
		if (isReg(str, reg)) {
			throw new SecuritySdkException(message);
		}
	}
	
	public static boolean isEquals(Object arg1, Object arg2) {
		return Objects.equals(arg1, arg2);
	}
	
	public static boolean isNotEquals(Object arg1, Object arg2) {
		return !isEquals(arg1, arg2);
	}
	
	public static void checkEquals(Object arg1, Object arg2, String message) throws SecuritySdkException {
		if (isNotEquals(arg1, arg2))
			throw new SecuritySdkException(message);
	}
	
	public static void checkNotEquals(Object arg1, Object arg2, String message) throws SecuritySdkException {
		if (isEquals(arg1, arg2))
			throw new SecuritySdkException(message);
	}
	
	public static boolean isEqualsIgnoreCase(String arg1, String arg2) {
		return arg1 == arg2 || (isNotNull(arg1) && arg1.equalsIgnoreCase(arg2));
	}
	
	public static boolean isNotEqualsIgnoreCase(String arg1, String arg2) {
		return !isEqualsIgnoreCase(arg1, arg2);
	}
	
	public static void checkEqualsIgnoreCase(String arg1, String arg2, String message) throws SecuritySdkException {
		if (isNotEqualsIgnoreCase(arg1, arg2))
			throw new SecuritySdkException(message);
	}
	
	public static void checkNotEqualsIgnoreCase(String arg1, String arg2, String message) throws SecuritySdkException {
		if (isEqualsIgnoreCase(arg1, arg2))
			throw new SecuritySdkException(message);
	}
	
	public static boolean checkTrue(boolean arg, String message) throws SecuritySdkException {
		if (!arg)
			throw new SecuritySdkException(message);
		return arg;
	}
	
	public static boolean checkFalse(boolean arg, String message) throws SecuritySdkException {
		if (arg)
			throw new SecuritySdkException(message);
		return arg;
	}
	
	public static boolean isLong(String str) {
		return isNotEmpty(str) && isReg(str.trim(), LONG_PATTERN);
	}
	
	public static boolean isNotLong(String str) {
		return !isLong(str);
	}
	
	public static long checkLong(String str, String message) {
		if (isNotLong(str)) {
			throw new SecuritySdkException(message);
		}
		return Long.parseLong(str);
	}
	
	public static boolean isInt(String str) {
		return isNotEmpty(str) && isReg(str.trim(), INT_PATTERN);
	}
	
	public static boolean isNotInt(String str) {
		return !isInt(str);
	}
	
	public static int checkInt(String str, String message) {
		if (isNotInt(str)) {
			throw new SecuritySdkException(message);
		}
		return Integer.parseInt(str);
	}
	
	public static boolean isId(String id) {
		return isLong(id);
	}
	
	public static boolean isNotId(String id) {
		return !isId(id);
	}
	
	public static long checkId(String id) throws SecuritySdkException {
		if (isNotId(id)) {
			throw new SecuritySdkException("ID不正确");
		}
		return Long.parseLong(id);
	}
	
	public static boolean isStatus(String status) {
		return isReg(status, STATUS_PATTERN);
	}
	
	public static boolean isNotStatus(String status) {
		return !isStatus(status);
	}
	
	public static boolean checkStatus(String status) throws SecuritySdkException {
		if (isNotStatus(status)) {
			throw new SecuritySdkException("状态不正确");
		}
		return isEqualsIgnoreCase("0", status.trim()) ? false : true;
	}
}
