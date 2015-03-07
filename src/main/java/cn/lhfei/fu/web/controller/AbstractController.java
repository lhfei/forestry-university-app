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
package cn.lhfei.fu.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import cn.lhfei.identity.web.model.UserSession;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public abstract class AbstractController {
	protected Marker marker = MarkerFactory.getMarker("_HOMEWORK_THESIS");
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	protected String getUserId(HttpSession session) {
		UserSession userSession = (UserSession) session
				.getAttribute(USER_SESSION);
		
		String userId = null;
		userId = userSession.getUser().getUserId();
		
		return userId;
	}
	
	/**
	 * User session key
	 */
	public static final String USER_SESSION = "USER_SESSION";
	
	protected static String CLASS_NAME = "className";		//'班级名称'
	
	
	protected static final String SUCCESS = "0";
	
	protected static final String FAILUE = "-1";
	
	
	/**
	 * 作业审批状态
	 */
	protected static final String SPZT = "statusList";
	
	
	/**
	 * 学年
	 */
	protected static final String XN = "XN";
	
	/**
	 * 学期
	 * 
	 */
	protected static final String XQ = "XQ";
	
	/**
	 * 教师当前学年教授的全部班级
	 */
	protected static final String CLASS = "CLASS";
	
	/**
	 * 当前教学学年、学期
	 */
	protected static final String CURRENT_ACADEMICYEAR_SEMESTER = "CURRENT_ACADEMICYEAR_SEMESTER";
	
	
	/**
	 * 全部课程列表
	 */
	protected static final String COURSE = "KC";
	
	protected static final String USER_TYPE = "userType";
	
	/**
	 * 下拉框默认值  <tt> "全选|-1" </tt>
	 */
	protected static final String COMBOBOX_DEFAULT_VALUE = "-1";
	
	
}
