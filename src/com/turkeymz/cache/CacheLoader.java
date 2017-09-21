package com.turkeymz.cache;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 缓存加载
 * Created by turkeymz on 2017/8/9.
 */
public class CacheLoader implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
        ServiceMethodCache serviceMethodCache = new ServiceMethodCache();
        serviceMethodCache.load();
	}



}
