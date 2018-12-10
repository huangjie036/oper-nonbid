package com.yyc.oper.nobid.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aostar.audit.annotation.EnableSimpleSpringBootAudit;
import com.sgcc.isc.framework.common.constant.Constants;
import com.sgcc.isc.service.adapter.factory.AdapterFactory;
import com.sgcc.isc.service.adapter.helper.IDomainService;
import com.sgcc.isc.service.adapter.helper.IIdentityService;
import com.sgcc.isc.service.adapter.helper.IOrganizationService;
import com.sgcc.isc.service.adapter.helper.IResourceService;
import com.sgcc.isc.service.adapter.helper.IRoleService;
import com.yyc.brace.isc.EnableISCApiClient;
import com.yyc.oper.nobid.filter.ISCInfoDeliveryFilter;

/**
 * 一般配置
 * 
 * @EnableConfigurationProperties 从application.properties读取属性注解
 * @EnableSimpleSpringBootAudit 开启日志审计注解
 * @EnableISCSecurity 开启与isc集成安全注解
 * @author jooer
 *
 */
@Configuration
@EnableRedisHttpSession
//@EnableSimpleSpringBootAudit
//@EnableISCApiClient
@EnableConfigurationProperties(value = CustomRedisProperties.class)
public class OperConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOGGER = Logger.getLogger(OperConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 加入swagger-ui.html扫描
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/**").addResourceLocations("classpath:/");
		super.addResourceHandlers(registry);

	}

	

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	@Primary
	@Bean
	public RedisProperties redisProperties(CustomRedisProperties customRedisProperties) {
		return customRedisProperties;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IIdentityService iidentityService() {
		// 身份类
		IIdentityService service = (IIdentityService) AdapterFactory.getInstance(Constants.CLASS_IDENTITY);
		return service;
	}

	@Bean
	public IOrganizationService iorganizationService() {
		// 组织类
		IOrganizationService service = (IOrganizationService) AdapterFactory.getInstance(Constants.CLASS_ORGANIZATION);
		return service;
	}

	@Bean
	public IRoleService iroleService() {
		// 角色类
		IRoleService service = (IRoleService) AdapterFactory.getInstance(Constants.CLASS_ROLE);
		return service;
	}

	@Bean
	public IDomainService idomainService() {
		// 业务域类
		IDomainService service = (IDomainService) AdapterFactory.getInstance(Constants.CLASS_DOMAIN);
		return service;
	}

	@Bean
	public IResourceService iresourceService() {
		// 资源类
		IResourceService service = (IResourceService) AdapterFactory.getInstance(Constants.CLASS_RESOURCE);
		return service;
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcatServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatServletContainerFactory.addInitializers(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {

			}
		});
		tomcatServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				SecurityCollection collection = new SecurityCollection();
				// url匹配表达式
				collection.addPattern("/*");
				// 不允许http方法
				collection.addMethod("PUT");
				collection.addMethod("DELETE");
				collection.addMethod("HEAD");
				collection.addMethod("OPTIONS");
				collection.addMethod("TRACE");
				collection.addMethod("PATCH");
				constraint.addCollection(collection);
				constraint.setAuthConstraint(true);
				// constraint.
				SecurityConstraint[] securityConstraints = context.findConstraints();
				for (SecurityConstraint securityConstraint1 : securityConstraints)
					context.removeConstraint(securityConstraint1);
				context.addConstraint(constraint);
				// 设置使用httpOnly
				context.setUseHttpOnly(true);
			}
		});
		return tomcatServletContainerFactory;
	}

//	@Bean
//	public FilterRegistrationBean testFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new ISCInfoDeliveryFilter());// 添加过滤器
//		registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
//		registration.addInitParameter("name", "alue");// 添加默认参数
//		registration.setName("iSCInfoDeliveryFilter");// 设置优先级
//		registration.setOrder(1);// 设置优先级
//		return registration;
//	}


	
}
