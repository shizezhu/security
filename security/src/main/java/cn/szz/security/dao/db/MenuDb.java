package cn.szz.security.dao.db;

import java.util.List;

import cn.szz.security.pojo.dto.MybatisParams;
import cn.szz.security.pojo.po.Menu;

/**
 * 菜单mybatis映射方法
 *
 * @author Shi Zezhu
 * @date 2018年2月1日 下午3:00:40
 *
 */
public interface MenuDb extends BaseDb<Menu> {

	/**
	 * 获取id
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午10:29:24
	 */
	List<Long> listId(MybatisParams mybatisParams);
	
	/**
	 * 获取权限标识
	 * 
	 * @param mybatisParams
	 * @return
	 * @author Shi Zezhu
	 * @date 2018年2月1日 上午10:29:24
	 */
	List<String> listAuthIden(MybatisParams mybatisParams);
}
