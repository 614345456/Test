package com.javasj.share.basic.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RightInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception {
		return false;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object arg2, ModelAndView mav) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object obj, Exception exception)throws Exception {
	}

}
