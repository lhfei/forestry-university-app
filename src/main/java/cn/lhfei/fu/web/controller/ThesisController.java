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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.fu.service.ThesisBaseService;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */
@Controller
@RequestMapping("thesis")
public class ThesisController {

	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView view = new ModelAndView("student/thesis/list");
		
		return view;
	}
	
	
	@Autowired
	private ThesisBaseService thesisBaseService;
}
