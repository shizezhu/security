package cn.szz.security.service;

import java.util.List;
import java.util.Map;

import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;

/**
 * 角色Service
 * 
 * @author Shi Zezhu
 * @date 2017年6月1日 上午10:06:41
 */
public interface RoleService {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Role> listAll();

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Role getById(String id);

	/**
	 * 根据用户ID获取
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午4:10:28
	 */
	List<Role> listByUserId(long userId);
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	Role getById(long id);

	/**
	 * 分页获取
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月13日 下午4:21:39
	 */
	PageData getByPage(PageData pageData);

	/**
	 * 保存
	 * 
	 * @param params
	 * @param menuIds
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:27:21
	 */
	AjaxResult save(Map<String, String> params, String menuIds) throws ResultException;

	/**
	 * 更新
	 * 
	 * @param params
	 * @param menuIds
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:27:27
	 */
	AjaxResult update(Map<String, String> params, String menuIds) throws ResultException;

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
	 * @author Shi Zezhu
	 * @date 2017年6月12日 下午7:36:51
	 */
	AjaxResult del(long id);
}
