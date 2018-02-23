package cn.szz.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;

import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Menu;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.IndexMenu;
import cn.szz.security.pojo.vo.PageData;

/**
 * 菜单
 *
 * @author Shi Zezhu
 * @date 2017年9月8日 下午2:54:25
 *
 */
public interface MenuService {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Menu> listAll();
	
	/**
	 * 根据项目ID获取
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Menu> listByProjectId(long projectId);
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Menu getById(String id);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Menu getById(long id);

	/**
	 * 分页获取,parentId: 负数:不添加条件;0:添加条件parentId is null;正数:添加条件
	 * 
	 * @param pageData
	 * @param parentId
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月13日 下午4:20:05
	 */
	PageData getByPage(PageData pageData, String parentId);

	/**
	 * 获取tree节点
	 * 
	 * @param params
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月15日 下午3:25:43
	 */
	ArrayNode getTreeNode(Map<String, String> params);
	
	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:27:21
	 */
	AjaxResult save(Map<String, String> params) throws ResultException;

	/**
	 * 更新
	 * 
	 * @param params
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:27:27
	 */
	AjaxResult update(Map<String, String> params) throws ResultException;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:27:33
	 */
	AjaxResult del(String id) throws ResultException;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年6月12日 下午7:36:51
	 */
	AjaxResult del(long id) throws ResultException;
	
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
	 * 获取主页菜单
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月19日 下午2:42:11
	 */
	List<IndexMenu> getIndexMenu();
	
	/**
	 * 根据项目id和用户id获取授权
	 * 
	 * @param projectId
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年1月24日 上午10:15:31
	 */
	List<String> listAuthIdenByProjectIdAndUserId(long projectId, long userId);
}
