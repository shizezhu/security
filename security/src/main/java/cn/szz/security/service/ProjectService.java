package cn.szz.security.service;

import java.util.List;
import java.util.Map;

import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;

public interface ProjectService {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Project> listAll();

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Project getById(String id);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Project getById(long id);
	
	/**
	 * 分页获取
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月13日 下午4:21:56
	 */
	PageData getByPage(PageData pageData);

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:25:48
	 */
	AjaxResult save(Map<String, String> params) throws ResultException;

	/**
	 * 更新
	 * 
	 * @param params
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:25:55
	 */
	AjaxResult update(Map<String, String> params) throws ResultException;
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:26:14
	 */
	AjaxResult del(String id) throws ResultException;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 下午7:36:51
	 */
	AjaxResult del(long id);
}
