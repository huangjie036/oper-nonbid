package com.yyc.oper.nobid.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sgcc.isc.ualogin.client.vo.IscSSOUserBean;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;

/**
 * 子服务，拦截器，截取请求头用户信息放到本地线程。
 * 
 * @author Administrator
 *
 */
public class ISCInfoDeliveryFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ISCInfoDeliveryFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		IscSSOUserBean principal = null;
		// if (request.getMethod().equals("GET")) {
		principal = JSON.parseObject(request.getParameter("isc_userinfo"), new TypeReference<IscSSOUserBean>() {
		});

		// 审计日志需要获取用户信息 ，必须放置再该位置 。对应 审计配置中的 配置 获取用户表达示 session.userName
		request.getSession().setAttribute("userName", principal.getIscUserSourceId());
		LOGGER.info("请求头获取用户信息成功");
		CurrentPrincipalHolder.setUid(principal.getIscUserId());
		principal.getIscUserId();
		CurrentPrincipalHolder.getUid();
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("name", principal.getIscUserSourceId());
		attributes.put("pwd", principal.getPassword());
		attributes.put("orgId", principal.getBaseOrgId());
		attributes.put("ip", principal.getIp());
		CurrentPrincipalHolder.setAttributes(attributes);
		// 生成的会话ID就是用户的令牌
		CurrentPrincipalHolder.setAcessToken(request.getSession(true).getId());

		// } else {
		// try {
		// InputStream in = request.getInputStream();
		// String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
		// JSONObject jsonObj = JSONObject.parseObject(body);
		// principal = JSON.parseObject(jsonObj.getString("isc_userinfo"), new
		// TypeReference<IscSSOUserBean>() {
		// });
		//
		// if (principal != null) {
		// LOGGER.info("请求头获取用户信息成功");
		// CurrentPrincipalHolder.setUid(principal.getIscUserId());
		// principal.getIscUserId();
		// CurrentPrincipalHolder.getUid();
		// Map<String, Object> attributes = new HashMap<>();
		// attributes.put("name", principal.getIscUserSourceId());
		// attributes.put("pwd", principal.getPassword());
		// attributes.put("orgId", principal.getBaseOrgId());
		// CurrentPrincipalHolder.setAttributes(attributes);
		// // 生成的会话ID就是用户的令牌
		// CurrentPrincipalHolder.setAcessToken(request.getSession(true).getId());
		// } else {
		// LOGGER.info("请求头获取用户信息失败!!!!");
		// }
		// //还原 入参
		// if (body.contains("serverparam")) {
		//
		//// WrapperedResponse wrapResponse = new
		// WrapperedResponse((HttpServletResponse) res);
		// WrapperedRequest wrapRequest = new
		// WrapperedRequest((HttpServletRequest) req,
		// jsonObj.getString("serverparam"));
		// // 审计日志需要获取用户信息 ，必须放置再该位置 。对应 审计配置中的 配置 获取用户表达示 session.userName
		// request.getSession().setAttribute("userName",
		// principal.getIscUserSourceId());
		// chain.doFilter(wrapRequest, rsp);
		// return;
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		try {
			chain.doFilter(request, rsp);
		} finally {
			CurrentPrincipalHolder.clear();
		}
	}

	@Override
	public void destroy() {
	}
}
