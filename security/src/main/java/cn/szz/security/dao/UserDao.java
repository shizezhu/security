package cn.szz.security.dao;

import java.util.Collection;
import java.util.List;

import cn.szz.security.pojo.po.User;
import cn.szz.security.pojo.vo.PageData;

/**
 * 用户Dao
 * 
 * @author Shi Zezhu
 * @date 2017年6月1日 上午10:06:28
 */
public interface UserDao {

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
	 * @date 2018年2月3日 上午11:14:03
	 */
	List<User> listByStatus(boolean status);
	
	/**
	 * 根据用户名获取
	 * 
	 * @param username
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月4日 下午4:20:59
	 */
	User getByUsername(String username);

	/**
	 * 分页获取数据
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<User> listRows(PageData pageData);

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
	 * 根据id获取
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年10月13日 下午5:39:07
	 */
	List<User> listById(Collection<Long> ids);
	
	/**
	 * 保存
	 * 
	 * @param user
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(User user);
	
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
	User getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(User user);
}
