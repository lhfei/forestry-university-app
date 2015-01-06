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

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.lhfei.fu.common.util.TDateUtils;
import cn.lhfei.fu.service.IFilePathBuilder;
import cn.lhfei.fu.web.model.HomeworkBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 5, 2014
 */
@Component("filePathBuilder")
public class FilePathBuilder implements IFilePathBuilder {
	private static final Logger log = LoggerFactory
			.getLogger(FilePathBuilder.class);

	@Override
	public String getRootPath() {
		return rootPath;
	}

	@Override
	public String mkdirs(String path) {
		String currentYear = TDateUtils.getFullYear(null);
		
		File file = new File(rootPath + File.separator + currentYear
				+ File.separator + path);

		if (!file.exists()) {
			log.info("Path [{}] was created.");
			file.mkdirs();
		}
		
		return file.getAbsolutePath();
	}
	
	/* (non-Javadoc)
	 * @see cn.lhfei.fu.service.IFilePathBuilder#buildFileName(cn.lhfei.fu.web.model.HomeworkBaseModel, java.lang.String)
	 */
	@Override
	public String buildFileName(HomeworkBaseModel model, String studentName) {
		String fileName = "";	//作业命名后完整名称

		String studentId = model.getStudentId(); // 学号
		String academicYear = model.getAcademicYear(); // 学年
		String semester = model.getSemester(); // 学期
		String className = model.getClassName(); // 班级名称
		String name = model.getName(); // 作业名称
		String courseName = model.getCourseName();	//课程名称
		
		StringBuilder sb = new StringBuilder(academicYear);
		
	
		//section 1： {学号班级姓名}
		sb.append(studentId);
		sb.append(className);
		sb.append(studentName);
		
		sb.append(".");
		
		// section 2： {学年学期}
		sb.append(academicYear);
		sb.append(semester);
		
		sb.append(".");
		
		// section 3: {课程}
		sb.append(courseName);
		
		sb.append(".");
		
		// section 4: {作业名称}
		sb.append(name);
		
		
		fileName = sb.toString();
		
		return fileName;
	}
	
	/* (non-Javadoc)
	 * @see cn.lhfei.fu.service.IFilePathBuilder#buildFilePath(cn.lhfei.fu.web.model.HomeworkBaseModel, java.lang.String)
	 */
	@Override
	public String buildFilePath(HomeworkBaseModel model, String studentName) {
		String filePath = "";	//作业上传后存放路径
		String studentId = model.getStudentId(); // 学号
		String academicYear = model.getAcademicYear(); // 学年
		String className = model.getClassName(); // 班级名称
		
		StringBuilder sb = new StringBuilder(academicYear);
	
		//section 1： {学号班级姓名}
		sb.append(studentId);
		sb.append(className);
		sb.append(studentName);
		
		filePath = sb.toString();
		
		return filePath;
	}
	
	@Override
	public String buildFullPath(HomeworkBaseModel model, String studentName) throws NullPointerException{
		String absolutePath = "";
		String filePath = this.buildFilePath(model, studentName);
		
		absolutePath = mkdirs(filePath);
		
		return absolutePath;
	}

	@Value("${archive.root.path}")
	private String rootPath;
}
