package com.yyc.oper.nobid.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sgcc.uap.safe.filter.SafeFilterDelegate;
import com.sgcc.uap.safe.listener.ConfigListener;

/**
 * 安全组件配置
 * 
 * @author jooer
 *
 */
@Configuration
public class SafeConfig {

	@Bean
	public ServletListenerRegistrationBean<ConfigListener> configListenerRegistrationBean() {
		ConfigListener configListener = new ConfigListener();
		ServletListenerRegistrationBean<ConfigListener> bean = new ServletListenerRegistrationBean<ConfigListener>();
		bean.setListener(configListener);
		return bean;
	}

	@Bean
	public FilterRegistrationBean safeFilterRegistrationBean() {
		SafeFilterDelegate safeFilterDelegate = new SafeFilterDelegate();
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(safeFilterDelegate);
		bean.addInitParameter("safe_config_file", "classpath:/safeFilterConfig.properties");
		bean.addUrlPatterns("/*");
		bean.setOrder(0);
		return bean;
	}
}
