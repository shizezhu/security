package cn.szz.security.dao;

import java.util.Collection;
import java.util.List;

import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.vo.PageData;

/**
 * 菜单
 *
 * @author Shi Zezhu
 * @date 2017年9月8日 下午2:54:58
 *
 */
public interface MenuDao {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月8日 下午2:10:05
	 */
	public List<Menu> listAll();
	
	/**
	 * 根据项目ID获取
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Menu> listByProjectId(long projectId);
	
	/**
	 * 获取所有id
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月23日 下午5:58:15
	 */
	public List<Long> listIdAll();
	
	/**
	 * 分页获取数据
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<Menu> listRows(PageData pageData);

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
	 * 分页获取数据
	 * 
	 * @param pageData
	 * @param parentId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<Menu> listRows(PageData pageData, long parentId);
	
	/**
	 * 获取总数
	 * 
	 * @param pageData
	 * @param parentId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:29
	 */
	long getTotal(PageData pageData, long parentId);

	/**
	 * 根据父级和名称获取,parentId: 0:添加条件parentId is null
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月8日 下午2:16:23
	 */
	Menu get(long parentId, String name);

	/**
	 * 根据项目ID和授权标识获取
	 * 
	 * @param projectId
	 * @param authIden
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月12日 下午4:48:13
	 */
	Menu getByAuthIden(long projectId, String authIden);

	/**
	 * 根据父级删除,parentId: 0:添加条件parentId is null
	 * 
	 * @param parentId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月8日 下午2:18:26
	 */
	int delByParentId(long parentId);

	/**
	 * 根据父级ID获取,parentId: 0:添加条件parentId is null
	 * 
	 * @param parentId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月13日 上午11:39:23
	 */
	List<Menu> listByParentId(long parentId);

	/**
	 * 根据项id和id获取权限标识
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月21日 上午11:05:03
	 */
	List<String> listAuthIdenByProjectIdAndId(long projectId, Collection<Long> ids);
	
	/**
	 * 根据项目id获取权限标识
	 * 
	 * @param projectId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月22日 下午3:40:37
	 */
	List<String> listAuthIdenByProjectId(long projectId);

	/**
	 * 根据id获取
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午8:24:17
	 */
	List<Menu> listById(Collection<Long> ids);
	
	/**
	 * 获取,parentId: 0:添加条件parentId is null
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月22日 下午8:24:17
	 */
	List<Menu> listByParentIdAndId(long parentId,  Collection<Long> ids);
	
	/**
	 * 保存
	 * 
	 * @param menu
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(Menu menu);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:54
	 */
	int delById(long id);
	
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:10:38
	 */
	Menu getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param menu
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(Menu menu);
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:54
	 */
	int delById(Collection<Long> ids);
}
