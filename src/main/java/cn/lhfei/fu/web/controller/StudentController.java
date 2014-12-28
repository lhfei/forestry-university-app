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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.fu.demo.DataFactory;
import cn.lhfei.fu.orm.domain.ApproveStatus;
import cn.lhfei.fu.service.ComboboxService;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 22, 2014
 */
@Controller
@RequestMapping("student")
public class StudentController extends AbstractController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("student/list");

		log.debug(marker, "student list.");
		
		List<ApproveStatus> list = comboboxService.getApproveStatus();
		view.addObject("statusList", list);

		return view;
	}

	@RequestMapping(value = "/getAssignments", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String[] getT() {

		return DataFactory.ASSIGNMENTS;
	}
	
	
	@Autowired
	private ComboboxService comboboxService;

}
