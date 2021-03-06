package com.sxt.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.sxt.pojo.User;
/**
 * 自定义表单认证过滤器
 * @author Administrator
 *
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	/**
	 * 登录认证 成功后将信息保存到session（shiro）中
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Session session = subject.getSession();
		User user = (User) subject.getPrincipal();
		session.setAttribute("user", user);
		return super.onLoginSuccess(token, subject, request, response);
	}
	/**
	 * 记住我（不用登录认证）将用户信息保存到session中
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// TODO Auto-generated method stub
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		//记住密码 登录没有认证 isAuthenticated()肯定为false
		if (!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("user")==null) {
			User user = (User) subject.getPrincipal();
			session.setAttribute("user", user);
		}
		//认证过或者记住我
		return subject.isAuthenticated()||subject.isRemembered();
	}
	
	
		
}
