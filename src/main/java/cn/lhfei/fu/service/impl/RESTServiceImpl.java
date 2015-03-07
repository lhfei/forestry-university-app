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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.orm.domain.StudentBase;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.orm.mybatis.mapper.IStudentMapper;
import cn.lhfei.fu.service.HomeworkArchiveService;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.service.IRESTService;
import cn.lhfei.fu.service.ISystemService;
import cn.lhfei.fu.service.StudentService;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.StudentBaseModel;
import cn.lhfei.fu.web.model.rest.HomeworkArchiveModel;
import cn.lhfei.fu.web.model.rest.Student;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 5, 2015
 */
@Service("restService")
public class RESTServiceImpl implements IRESTService {

	private static final Logger log = LoggerFactory
			.getLogger(RESTServiceImpl.class);

	@Override
	public StudentBase findStudentInfo(String studentId) throws Exception {
		StudentBase student = null;
		StudentBaseModel model = new StudentBaseModel();
		model.setStudentId(studentId);

		List<StudentBase> list = studentService.search(model);

		if (list != null && list.size() > 0) {
			student = list.get(0);

			log.debug("Name: [{}], ClassName: [{}], UserID: [{}]",
					student.getName(), student.getClassName(),
					student.getStudentId());
		}

		return student;
	}

	@Override
	public Student findStudentByStudentId(String studentId) throws Exception {
		Student student = studentMapper.findStudentById(studentId);

		return student;
	}

	@Override
	public List<HomeworkBaseModel> findHomeworkBaseByStudent(String studentId)
			throws Exception {
		// get current teaching period info.
		TeachingPeriods period = systemService.searchCurrentTeachingPeriods();

		// get current student info by studentId
		Student student = studentMapper.findStudentById(studentId);
		
		if(student != null){
			HomeworkBaseModel homework = new HomeworkBaseModel();
			homework.setAcademicYear(period.getAcademicYear());
			homework.setSemester(period.getSemester());
			homework.setClassName(student.getClassName());
			homework.setPageNum(0);
			homework.setPageSize(Integer.MAX_VALUE);
			homework.setStudentId(studentId);
			
			List<HomeworkBaseModel> result = homeworkBaseService
					.getHomeworkByStudent(homework);
			
			return result;
		}else{
			throw new java.lang.IllegalArgumentException("\u53c2\u6570\u65e0\u6548\uff1b\u5b66\u53f7\u4e0d\u5b58\u5728.");
		}

	}
	
	@Override
	public void saveHomeWorkArchive(HomeworkArchiveModel archive) {
		homeworkArchiveService.saveHomeWorkArchive(archive.getBaseId(),
				archive.getTeacherId(), archive.getStudentId(),
				archive.getFileName(), archive.getFilePath(), archive.getName());
	}

	@Autowired
	private StudentService studentService;

	@Autowired
	private IStudentMapper studentMapper;

	@Autowired
	private ISystemService systemService;

	@Autowired
	private HomeworkBaseService homeworkBaseService;

	@Autowired
	private HomeworkArchiveService homeworkArchiveService;
}
