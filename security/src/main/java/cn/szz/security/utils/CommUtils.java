package cn.szz.security.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 公共工具类
 *
 * @author Shi Zezhu
 * @date 2017年9月19日 上午11:39:40
 *
 */
public class CommUtils {

	/**
	 * String id array to long id list
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static List<Long> toIdList(String[] idArray) {
		List<Long> list = new ArrayList<Long>();
		if (CheckUtils.isEmpty(idArray)) return list;
		for (String id : idArray) {
			if (CheckUtils.isNotId(id)) continue;
			list.add(Long.parseLong(id));
		}
		return list;
	}
	
	/**
	 * String id list to long id list
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static List<Long> toIdList(List<String> idList) {
		List<Long> list = new ArrayList<Long>();
		if (CheckUtils.isEmpty(idList)) return list;
		for (String id : idList) {
			if (CheckUtils.isNotId(id)) continue;
			list.add(Long.parseLong(id));
		}
		return list;
	}
	
	/**
	 * String ids to long id list
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static List<Long> toIdList(String ids, String regex) {
		List<Long> list = new ArrayList<Long>();
		if (CheckUtils.isEmpty(ids)) return list;
		String[] idArray = ids.split(regex);
		return toIdList(idArray);
	}
	
	/**
	 * String id array to long id set
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static Set<Long> toIdSet(String[] idArray) {
		return new HashSet<Long>(toIdList(idArray));
	}
	
	/**
	 * String id list to long id set
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static Set<Long> toIdSet(List<String> idList) {
		return new HashSet<Long>(toIdList(idList));
	}
	
	/**
	 * String ids to long id set
	 * 
	 * @param idArray
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:36:05
	 */
	public static Set<Long> toIdSet(String ids, String regex) {
		return new HashSet<Long>(toIdList(ids, regex));
	}
	
	/**
	 * 获取差集
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月19日 上午11:37:46
	 */
	public static <T> Set<T> getDi(Collection<T> arg1, Collection<T> arg2) {
		Set<T> set = new HashSet<T>(arg1);
		Set<T> intersection = new HashSet<T>(arg1);
		intersection.retainAll(arg2);
		set.removeAll(intersection);
		return set;
	}
}
