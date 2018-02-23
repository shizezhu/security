package cn.szz.security.dao;

import java.util.List;

import cn.szz.security.pojo.po.Project;
import cn.szz.security.pojo.vo.PageData;

/**
 * 项目DAO
 *
 * @author Shi Zezhu
 * @date 2018年1月17日 下午6:07:36
 *
 */
public interface ProjectDao {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Project> listAll();

	/**
	 * 根据名称获取
	 * 
	 * @param name
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:20:59
	 */
	Project getByName(String name);

	/**
	 * 分页获取数据
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<Project> listRows(PageData pageData);

	/**
	 * 获取总数
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:29
	 */
	long getTotal(PageData pageData);
	
	/**
	 * 保存
	 * 
	 * @param project
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(Project project);
	
	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:54
	 */
	int delById(long id);
	
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:10:38
	 */
	Project getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param project
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(Project project);
}
