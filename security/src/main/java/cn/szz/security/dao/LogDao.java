package cn.szz.security.dao;

import java.util.List;

import cn.szz.security.pojo.po.Log;
import cn.szz.security.pojo.vo.PageData;

/**
 * 日志
 *
 * @author Shi Zezhu
 * @date 2018年2月5日 下午3:19:24
 *
 */
public interface LogDao {

	/**
	 * 保存
	 * 
	 * @param log
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月31日 下午5:22:26
	 */
    int save(Log log);

	/**
	 * 分页获取数据
	 * 
	 * @param projectId
	 * @param type
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<Log> listRows(Long projectId, Integer type, PageData pageData);
	
	/**
	 * 获取总数
	 * 
	 * @param projectId
	 * @param type
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:29
	 */
	long getTotal(Long projectId, Integer type, PageData pageData);
}