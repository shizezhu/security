package cn.szz.security.springlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;


/**
 * spring 关闭监听器
 * 
 * @author Shi Zezhu
 * @data 2016年12月19日 下午2:49:11
 */
@Service
public class CloseListener implements ApplicationListener<ContextClosedEvent> {

	
	@Override
	public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
	}
}
