package cn.szz.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.AjaxResult;
import cn.szz.security.pojo.vo.PageData;

/**
 * 用户Service
 * 
 * @author Shi Zezhu
 * @date 2017年6月1日 上午10:06:55
 */
public interface UserService {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<User> listAll();
	
	/**
	 * 根据状态获取
	 * 
	 * @param status
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 上午11:13:24
	 */
	List<User> listByStatus(boolean status);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	User getById(String id);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:37:30
	 */
	User getById(long id);

	/**
	 * 根据用户名获取
	 * 
	 * @param username
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年9月20日 下午6:22:05
	 */
	User getByUsername(String username);

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:25:40
	 */
	AjaxResult smLogin(String username, String password) throws ResultException;

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
	 * @param roleIdArray
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:25:48
	 */
	AjaxResult save(Map<String, String> params, String[] roleIdArray) throws ResultException;

	/**
	 * 更新
	 * 
	 * @param params
	 * @param roleIdArray
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:25:55
	 */
	AjaxResult update(Map<String, String> params, String[] roleIdArray) throws ResultException;

	/**
	 * 更新密码
	 * 
	 * @param params
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:26:02
	 */
	AjaxResult updatePassword(Map<String, String> params) throws ResultException;

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午5:26:07
	 */
	AjaxResult resetPassword(String id) throws ResultException;

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

	/**
	 * 根据id获取
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年10月13日 下午5:36:04
	 */
	List<User> listById(Collection<Long> ids);
}
