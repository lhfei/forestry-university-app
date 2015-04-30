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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.common.constant.DegreeEnum;
import cn.lhfei.fu.common.constant.OperationTypeEnum;
import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.mybatis.mapper.IExcelFactoryMapper;
import cn.lhfei.fu.service.IExcelService;
import cn.lhfei.fu.web.model.StudentBaseModel;
import cn.lhfei.fu.web.model.ThesisBaseModel;
import cn.lhfei.identity.common.util.SecurityUtils;
import cn.lhfei.identity.web.model.UserModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 30, 2015
 */
@Service("excelService")
public class ExcelServiceImpl implements IExcelService {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);
	
	private static final Integer MAX_BATCH_SIZE = 1000;

	/**
	 * <tt>本科生： 学号	学生姓名	班级	性别</tt>
	 * <tt>研究生： 年级	学科	学号	学生姓名	性别</tt>
	 * @param file
	 * @param degreeEnum
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public void importStudent(InputStream file, DegreeEnum degreeEnum) throws InvalidFormatException, IOException  {
		
		Date currentDate = new Date();
		List<StudentBaseModel> studentList = new ArrayList<StudentBaseModel>();
		List<UserModel> userList = new ArrayList<UserModel>();
		
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheetAt(0);

		int totalRows = sheet.getPhysicalNumberOfRows();
		
		Cell studentIdCell = null;		// 学号
		Cell studentNameCell = null;	// 学生姓名
		Cell classCell = null;			// 班级
		Cell genderCell = null;			// 性别
		
		Integer degree = degreeEnum.getCode();
		
		int size = 0;

		for (int i = 1; i < totalRows; i++) {
			String studentId = "";
			String studentName = "";
			int gender = 0;			// 男(0)|女(1)
			String genderStr = "";
			String className = "";
			String grade = "";
			String major = "";
			String desc = "初始化导入";
			
			Row row = sheet.getRow(i);
			
			StudentBaseModel model = new StudentBaseModel();
			UserModel userModel = new UserModel();
			
			if(degreeEnum.equals(DegreeEnum.BK)){ // 本科生信息导入
				
				studentIdCell = row.getCell(0);
				studentNameCell = row.getCell(1);
				classCell = row.getCell(2);
				genderCell = row.getCell(3);
				
				className = classCell.getStringCellValue();
				
			}else if(degreeEnum.equals(DegreeEnum.SS)){
				Cell gradeCell = null;			// 年级
				Cell majorCell = null;			// 学科
				
				
				gradeCell = row.getCell(0);
				majorCell = row.getCell(1);
				studentIdCell = row.getCell(2);
				studentNameCell = row.getCell(3);
				genderCell = row.getCell(4);
				
				grade = gradeCell.getStringCellValue();
				major = majorCell.getStringCellValue();
				
			}
			
			studentIdCell.setCellType(Cell.CELL_TYPE_STRING);
			
			studentId = studentIdCell.getStringCellValue();
			studentName = studentNameCell.getStringCellValue();
			genderStr= genderCell.getStringCellValue();
			
			if("女".equals(genderStr)){
				gender = 1;
			}else {
				gender = 0;
			}
			
			model.setClassName(className);
			model.setGrade(grade);
			model.setMajorName(major);
			model.setMajorCode(major);
			
			model.setStudentId(studentId);
			model.setName(studentName);
			model.setDegree(degree);
			model.setGender(gender);
			
			model.setCreateTime(currentDate);
			model.setModifyTime(currentDate);
			model.setBirthday(currentDate);
			
			model.setUserType(UserTypeEnum.STUDENT.getCode());
			model.setAliasName(studentName);
			model.setEmail(studentId + "@fu.com");
			model.setExtend(desc);
			model.setExtend1(desc);
			
			log.info("Total Rows: {}", totalRows );

			
			userModel.setBirthday(currentDate);
			userModel.setCreateTime(currentDate);
			userModel.setGender(gender);
			userModel.setModifyTime(currentDate);
			userModel.setPassWord(SecurityUtils.toMd5("123456"));
			userModel.setRoleId(24);
			userModel.setSignTime(currentDate);
			userModel.setUserId(studentId);
			userModel.setUserName(studentName);
			userModel.setAliasName(studentName);
			userModel.setEmail(studentId + "@fu.com");
			userModel.setUserType(UserTypeEnum.STUDENT.getCode());
			
			size ++;
			
			userList.add(userModel);
			studentList.add(model);
			
			if(size % MAX_BATCH_SIZE == 0){
				excelMapper.importStudent(studentList);
				excelMapper.importSysUser(userList);
				
				studentList = new ArrayList<StudentBaseModel>();
				userList = new ArrayList<UserModel>();
			}
			
		}
		
		if(studentList.size() > 0){
			excelMapper.importStudent(studentList);
			excelMapper.importSysUser(userList);
		}
		
	};
	
	/**
	 * 
	 * 毕业学年-班级-学号-学生姓名-论文(设计)题目-论文(设计)英文题目-论文来源-论文类别-教师编号-教师姓名-指导老师职称
	 * 
	 * @param file
	 * @param degreeEnum
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public void importThesis(InputStream file, DegreeEnum degreeEnum) throws InvalidFormatException, IOException {
		
		Date currentDate = new Date();
		List<ThesisBaseModel> thesisList = new ArrayList<ThesisBaseModel>();
		
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheetAt(0);

		int totalRows = sheet.getPhysicalNumberOfRows();
		
		Cell academicYearCell = null;
		Cell classCell = null;
		Cell studentIdCell = null;
		Cell studentNameCell = null;
		Cell titleCell = null;
		Cell enTitleCell = null;
		Cell originCell = null;
		Cell thesisTypeCell = null;
		Cell teacherIdCell = null;
		Cell teacherNameCell = null;
		Cell teacherTitleCell = null;
		
		
		String desc = "初始化导入";
		Integer degree = degreeEnum.getCode();
		
		int size  = 0;

		for (int i = 1; i < totalRows; i++) {
			String academicYear = "";
			String className = "";
			String studentId = "";
			String studentName = "";
			String title = "";
			String enTitle = "";
			String origin = "";
			String thesisType = "";
			String teacherId = "";
			String teacherName = "";
			String teacherTitle = "";
			
			Row row = sheet.getRow(i);
			
			academicYearCell = row.getCell(0);
			classCell = row.getCell(1);
			studentIdCell = row.getCell(2);
			studentNameCell = row.getCell(3);
			titleCell = row.getCell(4);
			enTitleCell = row.getCell(5);
			originCell = row.getCell(6);
			thesisTypeCell = row.getCell(7);
			teacherIdCell = row.getCell(8);
			teacherNameCell = row.getCell(9);
			teacherTitleCell = row.getCell(10);		
			
			studentIdCell.setCellType(Cell.CELL_TYPE_STRING);
			teacherIdCell.setCellType(Cell.CELL_TYPE_STRING);
			academicYearCell.setCellType(Cell.CELL_TYPE_STRING);
			
			academicYear = academicYearCell.getStringCellValue();
			className = classCell.getStringCellValue();
			studentId = studentIdCell.getStringCellValue();
			studentName = studentNameCell.getStringCellValue();
			title = titleCell.getStringCellValue();
			enTitle = enTitleCell.getStringCellValue();
			origin = originCell.getStringCellValue();
			thesisType = thesisTypeCell.getStringCellValue();
			teacherId = teacherIdCell.getStringCellValue();
			teacherName = teacherNameCell.getStringCellValue();
			teacherTitle = teacherTitleCell.getStringCellValue();
			
			ThesisBaseModel model = new ThesisBaseModel();
			model.setAcademicYear(academicYear);
			model.setClassName(className);
			model.setStudentId(studentId);
			model.setStudentName(studentName);
			model.setThesisTitle(title);
			model.setThesisEnTitle(enTitle);
			model.setOrigin(origin);
			model.setThesisType(thesisType);
			model.setTeacherId(teacherId);
			model.setTeacherName(teacherName);
			model.setTeacherTitle(teacherTitle);
			
			model.setDescription(desc);
			model.setDegree(degree);
			model.setActionType("" +OperationTypeEnum.PLSC.getCode());
			
			model.setOperationTime(currentDate);
			model.setCreateTime(currentDate);
			model.setModifyTime(currentDate);
			
			thesisList.add(model);
			
			size ++;
			
			if(size % 50 == 0){
				excelMapper.importThesis(thesisList);
				thesisList = new ArrayList<ThesisBaseModel>();
			}
		}
		
		if(thesisList.size() > 0){
			excelMapper.importThesis(thesisList);
		}
		
	}
	
	/**
	 * @param list
	 * @return
	 */
	public boolean  importSysUser(List<UserModel> list) {
		
		
		
		return true;
	};
	
	
	
	@Autowired
	private IExcelFactoryMapper excelMapper;
}
