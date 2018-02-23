package cn.szz.security.dao;

import java.util.Collection;
import java.util.List;

import cn.szz.security.pojo.po.Role;
import cn.szz.security.pojo.vo.PageData;

/**
 * 角色Dao
 * 
 * @author Shi Zezhu
 * @date 2017年6月1日 上午10:06:12
 */
public interface RoleDao {

	/**
	 * 获取所有
	 * 
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月29日 下午3:19:01
	 */
	List<Role> listAll();
	
	/**
	 * 分页获取数据
	 * 
	 * @param pageData
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年6月12日 上午11:49:23
	 */
	List<Role> listRows(PageData pageData);

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
	 * 根据名称获取
	 * 
	 * @param name
	 * @return
	 * @author Shi Zezhu
	 * @date 2017年8月28日 下午4:47:46
	 */
	Role getByName(String name);
	
	/**
	 * 保存
	 * 
	 * @param role
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:09:36
	 */
	int save(Role role);
	
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
	Role getById(long id);
	
	/**
	 * 更新
	 * 
	 * @param role
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午11:11:16
	 */
	int update(Role role);
	
	/**
	 * 根据id获取
	 * 
	 * @param ids
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 下午4:13:23
	 */
	List<Role> listById(Collection<Long> ids);
	
	/**
	 * 根据用户id获取
	 * 
	 * @param userId
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月3日 下午4:59:16
	 */
	List<Role> listByUserId(long userId);
}
