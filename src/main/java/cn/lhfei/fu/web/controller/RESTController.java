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
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lhfei.fu.orm.domain.StudentBase;
import cn.lhfei.fu.service.impl.RESTServiceImpl;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.rest.Student;
import cn.lhfei.identity.util.JSONReturn;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 5, 2015
 */
@Controller
@RequestMapping(value = "rest", produces = "application/json")
public class RESTController {

	private static final Logger log = LoggerFactory
			.getLogger(RESTController.class);

	@RequestMapping(value = "/{id}/findStudentInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findStudentInfo(
			@PathVariable("id") String studentId) {
		
		log.debug("ID: [{}]", studentId);

		boolean result = false;
		String message = "\u67e5\u8be2\u6210\u529f";

		Student student = null;

		try {
			StudentBase base = restService.findStudentInfo(studentId);

			student = new Student();

			student.setClassName(base.getClassName());

			student.setId(base.getId());
			student.setName(base.getName());
			student.setStudentId(base.getStudentId());

			if (base.getGender() == 0) {// gender code: 0(男) | 1(女)
				student.setGender("\u7537");
			} else {
				student.setGender("\u5973");
			}

			result = true;

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			message = "\u67e5\u8be2\u5931\u8d25";
		}

		return JSONReturn.map(result, student, message);
	}

	@RequestMapping(value = "/{id}/findHomework", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findHomework(
			@PathVariable("id") String studentId) {
		
		log.debug("ID: [{}]", studentId);

		try {
			List<HomeworkBaseModel> list = restService
					.findHomeworkBaseByStudent(studentId);

			return JSONReturn.mapOK(list);

		} catch (Exception e) {

			log.error(e.getMessage(), e);

			return JSONReturn.mapError("");
		}
	}

	@Autowired
	private RESTServiceImpl restService;

}
