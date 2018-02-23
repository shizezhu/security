package cn.szz.security.springlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * spring 启动监听器
 * 
 * @author Shi Zezhu
 * @data 2016年12月19日 下午2:49:11
 */
@Service
public class RefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
	}
}
