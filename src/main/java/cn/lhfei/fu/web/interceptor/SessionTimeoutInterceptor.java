/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.lhfei.fu.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.lhfei.fu.web.controller.AbstractController;
import cn.lhfei.identity.web.model.UserSession;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 6, 2015
 */

public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(SessionTimeoutInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Session expired prehandler.");
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute(AbstractController.USER_SESSION);
		
		log.info("Request URL: {}", request.getRequestURL());
		
		if(userSession == null){
			response.sendRedirect("../system/login.do");
			
			log.info("Session expired. Then will redirect to home page.");
			
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute(AbstractController.USER_SESSION);
		
		if(userSession == null){
			response.sendRedirect("system/index");
			
			log.info("Session expired. Then will redirect to home page.");
		}
		
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Session expired posthandler.");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Session expired afterCompletion.");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Session expired afterConcurrentHandlingStarted.");
	}

}
