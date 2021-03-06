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
package cn.lhfei.fu.common.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.lhfei.fu.service.IFilePathBuilder;
import cn.lhfei.fu.web.model.HomeworkBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 5, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class FilePathBuilderTest {

	private static final Logger log = LoggerFactory.getLogger(FilePathBuilderTest.class);
	
	@Test
	public void getRootPath() {
		log.info(filePathBuilder.getRootPath());
	}
	
	@Test
	public void mkdirs() {
		filePathBuilder.mkdirs("123");
	}
	
	@Test
	public void buildFileName() {
		HomeworkBaseModel model = new HomeworkBaseModel();
		MultipartFile file  = null;
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		
		model.setAcademicYear("2014-2015");
		model.setSemester("1");
		model.setClassName("园艺 12-1");
		model.setName("园艺素描");
		model.setStudentId("130101");
		model.setCourseName("城市园林学");
		
		files.add(file);
		model.setFiles(files);
		
		String fileName = filePathBuilder.buildFileName(model, "习近平", 1);
		
		org.junit.Assert.assertEquals("130101园艺 12-1习近平.2014-2015.1.城市园林学.园艺素描_1", fileName);
		
		log.info(fileName);
	}
	
	@Test
	public void buildFullPath() {
		HomeworkBaseModel model = new HomeworkBaseModel();
		model.setStudentId("130101");
		model.setAcademicYear("2014-2015");
		model.setSemester("1");
		model.setClassName("园艺 12-1班");
		model.setName("园艺素描");
		
		String fileName = filePathBuilder.buildFullPath(model, "习近平");
		
		org.junit.Assert.assertTrue(fileName.endsWith("130101园艺 12-1班习近平"));
		
		log.info(fileName);
		
	}
	
	@Autowired
	private  IFilePathBuilder  filePathBuilder;
}
