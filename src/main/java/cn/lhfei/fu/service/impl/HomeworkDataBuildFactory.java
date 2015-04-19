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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.common.constant.OperationTypeEnum;
import cn.lhfei.fu.orm.mybatis.mapper.IExcelFactoryMapper;
import cn.lhfei.fu.service.DataBuildFactory;
import cn.lhfei.fu.web.model.HomeworkBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 31, 2015
 */
@Service("homeworkDataBuildFactory")
public class HomeworkDataBuildFactory implements DataBuildFactory {
	private static final Logger log = LoggerFactory
			.getLogger(HomeworkDataBuildFactory.class);

	/* (non-Javadoc)
	 * @see cn.lhfei.fu.service.DataBuildFactory#importDataByExcel(java.lang.String)
	 */
	@Override
	public boolean importDataByExcel(String filePath , Map<String, Object> params) throws Exception {
		InputStream inp = new FileInputStream(filePath);

		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);

		int totalRows = sheet.getPhysicalNumberOfRows();
		Cell homeworkNameCell = null;		// 作业名称
		Cell academicYearCell = null;		// 学年
		Cell semesterCell = null;			// 学期
		Cell teacherIdCell = null;			// 教师编号		
		Cell teacherNameCell = null;		// 教师姓名		
		Cell classCell = null;				// 班级名称
		Cell courseCodeCell = null;			// 课程编号		
		Cell courseNameCell = null;			// 课程名称			

		String homeworkName = null;
		String teacherId = null;
		String teacherName = null;
		String courseName = null;
		String courseCode = null;
		String academicYear = null;
		String semester = null;
		String className = null;

		HomeworkBaseModel model = null;
		Date currentDate = new Date();
		List<HomeworkBaseModel> homeworkList = new ArrayList<HomeworkBaseModel>();

		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			
			homeworkNameCell = row.getCell(0);	// 作业名称
			academicYearCell = row.getCell(1); // 学年
			semesterCell = row.getCell(2); // 学期
			teacherIdCell = row.getCell(3); // 教师编号
			teacherNameCell = row.getCell(4); // 教师姓名
			classCell = row.getCell(5); // 班级名称
			courseCodeCell = row.getCell(6); // 课程编号
			courseNameCell = row.getCell(7); // 课程名称

			if (homeworkNameCell != null) {
				homeworkName = homeworkNameCell.getStringCellValue().trim();
			}
			if (academicYearCell != null) {
				academicYear = academicYearCell.getStringCellValue().trim();
			}
			if (semesterCell != null) {
				semester = semesterCell.getStringCellValue().trim();
			}
			if (teacherIdCell != null) {
				teacherId = teacherIdCell.getStringCellValue().trim();
			}
			if (teacherNameCell != null) {
				teacherName = teacherNameCell.getStringCellValue().trim();
			}
			if (courseCodeCell != null) {
				courseCode = courseCodeCell.getStringCellValue().trim();
			}
			if (courseNameCell != null) {
				courseName = courseNameCell.getStringCellValue().trim();
			}
			if (classCell != null) {
				className = classCell.getStringCellValue().trim();

				model = new HomeworkBaseModel();
				
				model.setName(homeworkName);
				model.setAcademicYear(academicYear);
				model.setSemester(semester);
				model.setTeacherId(teacherId);
				model.setTeacherName(teacherName);
				model.setClassName(className);
				model.setCourseCode(courseCode);
				model.setCourseName(courseName);
				
				model.setActionType(OperationTypeEnum.SC.getCode().toString());
				model.setOperationTime(currentDate);
				model.setCreateTime(currentDate);
				model.setModifyTime(currentDate);

				homeworkList.add(model);

			}

			log.info("teacher={}, course={}, xn={}, xq={}, homework={}",
					teacherName, courseName, academicYear, semester, className);
		}

		for (HomeworkBaseModel baseModel : homeworkList) {
			excelFactoryMapper.importHomework(baseModel);
		}

		return false;
	}

	@Autowired
	private IExcelFactoryMapper excelFactoryMapper;
}
