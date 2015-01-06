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

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.identity.orm.domain.User;
import cn.lhfei.identity.service.IdentityService;
import cn.lhfei.identity.util.JSONReturn;
import cn.lhfei.identity.web.model.UserModel;
import cn.lhfei.identity.web.model.UserSession;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 20, 2014
 */
@Controller
@RequestMapping("system")
public class SystemController extends AbstractController {
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		ModelAndView view = new ModelAndView();
		UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		String userId = userSession.getUser().getUserId();

		log.debug(marker, "User: {}", userId);

		if (userId.equals("admin")) {

			view.setViewName("system/main");

		} else if (userId.equals("teacher")) {
			view.setViewName("teacher/main");
		} else {
			session.setAttribute(CLASS_NAME, "园林建筑设计一班");	//check current student' class name.
			view.setViewName("student/main");
		}

		return view;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	@Transactional
	public @ResponseBody Map<String, Object> login(@RequestBody UserModel user, HttpSession session) {
		UserSession userSession = null;
		
		String userId = user.getUserId();
		String passWord = user.getPassWord();
		
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(passWord)){
			User userEntity = identityService.login(userId, passWord);
			
			if (null != userEntity) { // check user name and password.
				user.setAliasName(userEntity.getAliasName());
				user.setBirthday(userEntity.getBirthday());
				user.setEmail(userEntity.getEmail());
				user.setGender(userEntity.getGender());
				user.setId(userEntity.getId());
				user.setPassWord(userEntity.getPassWord());
				user.setRoleId(userEntity.getRole().getRoleId());
				user.setUserId(userEntity.getUserId());
				user.setUserName(userEntity.getUserName());
				user.setUserType(userEntity.getUserType());
				
				// cached the current user login info.
				userSession = new UserSession(session.getId());
				userSession.setUser(user);
				session.setAttribute(USER_SESSION, userSession);
				
				return JSONReturn.mapOK("0");
				
			}else {//userId and passWord not matched.
				return JSONReturn.mapError("\u7528\u6237\u540d\u548c\u5bc6\u7801\u4e0d\u5339\u914d!");
			}
			
		}else {// userId and passWord not entry.
			return JSONReturn.mapError("\u7528\u6237\u540d\u548c\u5bc6\u7801\u5fc5\u987b\u8f93\u5165!");
		}
	}
	
	@Autowired
	private IdentityService identityService;
}
