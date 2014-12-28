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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("uname") String uname,
			@RequestParam("passwd") String passwd, HttpSession session) {
		ModelAndView view = new ModelAndView();
		UserSession userSession = null;

		if (true) { // check user name and password.
			UserModel user = new UserModel();
			user.setUserName(uname);
			user.setUserId(""+1);
			user.setAliasName(uname);

			userSession = new UserSession(session.getId());
			userSession.setUser(user);

			view.addObject("user", user);
			
			session.setAttribute(USER_SESSION, userSession);
		}

		log.debug(marker, "User: {}", uname);

		if (uname.equals("admin")) {

			view.setViewName("system/main");

		} else if (uname.equals("teacher")) {
			view.setViewName("teacher/main");
		} else {
			session.setAttribute(CLASS_NAME, "园林建筑设计一班");	//check current student' class name.
			view.setViewName("student/main");
		}

		return view;
	}
}
