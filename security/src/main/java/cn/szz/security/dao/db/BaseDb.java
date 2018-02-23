package cn.szz.security.dao.db;

import java.util.List;

import cn.szz.security.pojo.dto.MybatisParams;

public interface BaseDb<T> {

	/**
	 * 获取
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月31日 下午9:30:28
	 */
	List<T> list(MybatisParams mybatisParams);
	
	/**
	 * 获取总数
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月31日 下午9:30:28
	 */
	long count(MybatisParams mybatisParams);
	
	/**
	 * 获取
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午10:32:52
	 */
	T get(MybatisParams mybatisParams);

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:45:09
	 */
	T getById(long id);
	
	/**
	 * 删除
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月8日 下午2:18:26
	 */
	int del(MybatisParams mybatisParams);
	
	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月8日 下午2:18:26
	 */
	int delById(long id);
	
	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午10:30:47
	 */
	int save(T t);
	
	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午10:31:07
	 */
	int update(T t);
}
