package cn.szz.security.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import cn.szz.security.exception.ResultException;

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
	public static final String U_UN_PATTERN = "[\\w-]{2,18}";
	public static final String U_RN_PATTERN = "[\u4E00-\u9FA5\\w-]{2,10}";
	public static final String U_PW_PATTERN = "[A-Za-z0-9*]{6,18}";
	public static final String U_PH_PATTERN = "((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}";
	public static final String U_EM_PATTERN = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
	public static final String URL_PATTERN = "https?://[A-Za-z0-9]+.[A-Za-z0-9]+.[A-Za-z0-9]+(.[A-Za-z0-9]+)?[:0-9]?(/[A-Za-z0-9_-]+)*";
	public static final String URI_PATTERN = "(/[A-Za-z0-9_-]+)+";
	public static final String R_NA_PATTERN = "[\u4E00-\u9FA5\\w-]{1,15}";
	public static final String P_NA_PATTERN = "[\u4E00-\u9FA5\\w-]{1,15}";
	
	
	public static boolean isNull(Object object) {
		return object == null;
	}
	
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}
	
	public static <T> T ifNull(T arg1, T arg2) {
		return isNotNull(arg1) ? arg1 : arg2;
	}
	
	public static void checkNull(Object object, ErrorCode errorCode) throws ResultException {
		if (isNotNull(object)) 
			throw new ResultException(errorCode);
	}
	
	public static <T> T checkNotNull(T t, ErrorCode errorCode) throws ResultException {
		if (isNull(t)) 
			throw new ResultException(errorCode);
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
	
	public static void checkEmpty(Collection<?> collection, ErrorCode errorCode) throws ResultException {
		if (isNotEmpty(collection)) 
			throw new ResultException(errorCode);
	}
	
	public static <T> Collection<T> checkNotEmpty(Collection<T> collection, ErrorCode errorCode) throws ResultException {
		if (isEmpty(collection)) 
			throw new ResultException(errorCode);
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
	
	public static void checkEmpty(Object[] array, ErrorCode errorCode) throws ResultException {
		if (isNotEmpty(array)) 
			throw new ResultException(errorCode);
	}
	
	public static <T> T[] checkNotEmpty(T[] array, ErrorCode errorCode) throws ResultException {
		if (isEmpty(array)) 
			throw new ResultException(errorCode);
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
	
	public static void checkEmpty(Map<?, ?> map, ErrorCode errorCode) throws ResultException {
		if (isNotEmpty(map)) 
			throw new ResultException(errorCode);
	}
	
	public static <T, V> Map<T, V> checkNotEmpty(Map<T, V> map, ErrorCode errorCode) throws ResultException {
		if (isEmpty(map)) 
			throw new ResultException(errorCode);
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
	
	public static void checkEmpty(String str, ErrorCode errorCode) throws ResultException {
		if (isNotEmpty(str)) 
			throw new ResultException(errorCode);
	}
	
	public static String checkNotEmpty(String str, ErrorCode errorCode) throws ResultException {
		if (isEmpty(str)) 
			throw new ResultException(errorCode);
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
	
	public static String checkReg(String str, String reg, ErrorCode errorCode) throws ResultException {
		if (isNull(reg))
			throw new ResultException(ErrorCode.REG_NULL);
		if (isNotReg(str, reg)) {
			throw new ResultException(errorCode);
		}
		return str;
	}
	
	public static void checkNotReg(String str, String reg, ErrorCode errorCode) throws ResultException {
		if (isNull(reg))
			throw new ResultException(ErrorCode.REG_NULL);
		if (isReg(str, reg)) {
			throw new ResultException(errorCode);
		}
	}
	
	public static boolean isEquals(Object arg1, Object arg2) {
		return Objects.equals(arg1, arg2);
	}
	
	public static boolean isNotEquals(Object arg1, Object arg2) {
		return !isEquals(arg1, arg2);
	}
	
	public static void checkEquals(Object arg1, Object arg2, ErrorCode errorCode) throws ResultException {
		if (isNotEquals(arg1, arg2))
			throw new ResultException(errorCode);
	}
	
	public static void checkNotEquals(Object arg1, Object arg2, ErrorCode errorCode) throws ResultException {
		if (isEquals(arg1, arg2))
			throw new ResultException(errorCode);
	}
	
	public static boolean isEqualsIgnoreCase(String arg1, String arg2) {
		return arg1 == arg2 || (isNotNull(arg1) && arg1.equalsIgnoreCase(arg2));
	}
	
	public static boolean isNotEqualsIgnoreCase(String arg1, String arg2) {
		return !isEqualsIgnoreCase(arg1, arg2);
	}
	
	public static void checkEqualsIgnoreCase(String arg1, String arg2, ErrorCode errorCode) throws ResultException {
		if (isNotEqualsIgnoreCase(arg1, arg2))
			throw new ResultException(errorCode);
	}
	
	public static void checkNotEqualsIgnoreCase(String arg1, String arg2, ErrorCode errorCode) throws ResultException {
		if (isEqualsIgnoreCase(arg1, arg2))
			throw new ResultException(errorCode);
	}
	
	public static boolean checkTrue(boolean arg, ErrorCode errorCode) throws ResultException {
		if (!arg)
			throw new ResultException(errorCode);
		return arg;
	}
	
	public static boolean checkFalse(boolean arg, ErrorCode errorCode) throws ResultException {
		if (arg)
			throw new ResultException(errorCode);
		return arg;
	}
	
	public static boolean isLong(String str) {
		return isNotEmpty(str) && isReg(str.trim(), LONG_PATTERN);
	}
	
	public static boolean isNotLong(String str) {
		return !isLong(str);
	}
	
	public static long checkLong(String str, ErrorCode errorCode) {
		if (isNotLong(str)) {
			throw new ResultException(errorCode);
		}
		return Long.parseLong(str);
	}
	
	public static boolean isInt(String str) {
		return isNotEmpty(str) && isReg(str.trim(), INT_PATTERN);
	}
	
	public static boolean isNotInt(String str) {
		return !isInt(str);
	}
	
	public static int checkInt(String str, ErrorCode errorCode) {
		if (isNotInt(str)) {
			throw new ResultException(errorCode);
		}
		return Integer.parseInt(str);
	}
	
	public static boolean isId(String id) {
		return isLong(id);
	}
	
	public static boolean isNotId(String id) {
		return !isId(id);
	}
	
	public static long checkId(String id) throws ResultException {
		if (isNotId(id)) {
			throw new ResultException(ErrorCode.ID_ERROR);
		}
		return Long.parseLong(id);
	}
	
	public static boolean isStatus(String status) {
		return isReg(status, STATUS_PATTERN);
	}
	
	public static boolean isNotStatus(String status) {
		return !isStatus(status);
	}
	
	public static boolean checkStatus(String status) throws ResultException {
		if (isNotStatus(status)) {
			throw new ResultException(ErrorCode.ST_ERROR);
		}
		return isEqualsIgnoreCase("0", status.trim()) ? false : true;
	}
}
