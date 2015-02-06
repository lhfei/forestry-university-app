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
package cn.lhfei.fu.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.service.IRESTService;
import cn.lhfei.fu.test.BaseTestSuite;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.rest.Student;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 5, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class RESTServiceImplTest extends BaseTestSuite {

	@Test
	public void findStudentByStudentId() {
		String studentId = "130335229";
		
		try {
			Student student = restService.findStudentByStudentId(studentId);
			
			log.info("Name: [{}], ClassName: [{}], UserID: [{}]",
					student.getName(), student.getClassName(),
					student.getStudentId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findHomeworkBaseByStudent() {
		String studentId = "110314217";
		
		try {
			List<HomeworkBaseModel> result = restService.findHomeworkBaseByStudent(studentId);
			
			log.info("Result size: [{}]", result.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Autowired
	private IRESTService restService;
}
