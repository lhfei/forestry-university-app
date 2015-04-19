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

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.service.TeacherService;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 17, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class TeacherServiceImplTest {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherServiceImplTest.class);
	
	
	@Test
	public void updateTitle() throws Exception {
		String filePath = "src\\test\\resource\\excel\\园林学院教职工名册 - 2014-10-31提供数据库.xlsx";
		String teacherId = "";
		String teacherTitle = "";
		
		InputStream inp = new FileInputStream(filePath);

		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);

		int totalRows = sheet.getPhysicalNumberOfRows();
		
		Cell teacherIdCell = null;
		Cell teacherTitleCell = null;
		
		for(int i=1; i<totalRows; i++){
			Row row = sheet.getRow(i);
			teacherIdCell =  row.getCell(1);
			teacherTitleCell = row.getCell(8);
			
			teacherId = teacherIdCell.getStringCellValue();
			
			if(teacherId != null && teacherId.startsWith("S")){
				teacherId = teacherId.replaceAll("S", "");
			}
			
			teacherTitle = teacherTitleCell.getStringCellValue();
			
			log.info("ID: {}, Title: {}", teacherId, teacherTitle);
			
			teacherService.updateTeacherTitle(teacherId, teacherTitle);
		}
		
	}
	
	
	@Autowired
	TeacherService teacherService;
}
