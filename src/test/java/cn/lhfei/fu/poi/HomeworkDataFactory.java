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
package cn.lhfei.fu.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import cn.lhfei.fu.common.constant.OperationTypeEnum;
import cn.lhfei.fu.test.BaseTestSuite;
import cn.lhfei.fu.web.model.HomeworkBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 5, 2015
 */

public class HomeworkDataFactory extends BaseTestSuite{

	@Test
	public void read() throws IOException, InvalidFormatException {
		InputStream inp = new FileInputStream("E:\\Webapp_workspace\\webapps_agent\\forestry-university-app\\src\\test\\resource\\excel\\2014-2015.xlsx");
	    //InputStream inp = new FileInputStream("workbook.xlsx");

	    Workbook wb = WorkbookFactory.create(inp);
	    Sheet sheet = wb.getSheetAt(0);
	    
	    int totalRows = sheet.getPhysicalNumberOfRows();
    	Cell teacherCell = null;
    	Cell courseCell = null;	
    	Cell academicYearCell = null;
    	Cell semesterCell = null;
    	Cell classCell = null;
	    
		String teacherName = null;
		String courseName = null;
		String academicYear = null;
		String semester = null; 
		String classNames = null;
	    
		HomeworkBaseModel model = null;
		Date currentDate = new Date();
	    for(int i = 0; i < totalRows; i++){
	    	Row row = sheet.getRow(i);

	    	teacherCell = row.getCell(2);		// 授课教师
	    	courseCell = row.getCell(3);		// 课程
	    	academicYearCell = row.getCell(4);	// 学年
	    	semesterCell = row.getCell(5);		// 学期
	    	classCell = row.getCell(10);		// 班级
	    	
	    	
	    	if(teacherCell != null){
	    		teacherName = teacherCell.getStringCellValue().trim();
	    	}
	    	if(courseCell != null){
	    		courseName = courseCell.getStringCellValue().trim();
	    	}
	    	if(academicYearCell != null){
	    		academicYear = academicYearCell.getStringCellValue().trim();
	    	}
	    	if(semesterCell != null){
	    		semester = semesterCell.getStringCellValue().trim();
	    	}
	    	if(classCell != null){
	    		classNames = classCell.getStringCellValue().trim();
	    		
	    		String[] names = classNames.split(",");
	    		if(names != null && names.length > 0){
	    			for(String className : names){
	    				model = new HomeworkBaseModel();
	    				model.setAcademicYear(academicYear);
	    				model.setSemester(semester);
	    				model.setActionType(OperationTypeEnum.SC.getCode().toString());
	    				model.setClassName(className);
	    				model.setCourseName(courseName);
	    				model.setCreateTime(currentDate);
	    				model.setTeacherName(teacherName);
	    			}
	    		}
	    		
	    	}
	    	
	    	log.info("teacher={}, course={}, xn={}, xq={}, homework={}", teacherName, courseName, academicYear, semester, classNames);
	    }

	}
}
