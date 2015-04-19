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
import cn.lhfei.fu.orm.domain.ThesisBase;
import cn.lhfei.fu.orm.mybatis.mapper.IExcelFactoryMapper;
import cn.lhfei.fu.service.DataBuildFactory;
import cn.lhfei.fu.service.ThesisBaseService;
import cn.lhfei.fu.web.model.ThesisBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 16, 2015
 */
@Service("thesisDataBuildFactory")
public class ThesisDataBuildFactory implements DataBuildFactory {
	private static final Logger log = LoggerFactory
			.getLogger(HomeworkDataBuildFactory.class);

	@Override
	public boolean importDataByExcel(String filePath, Map<String, Object> params) throws Exception {
		
		Date currentDate = new Date();
		List<ThesisBase> thesisList = new ArrayList<ThesisBase>();
		
		InputStream inp = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);

		int totalRows = sheet.getPhysicalNumberOfRows();
		
		Cell classCell = null;
		Cell studentNameCell = null;
		Cell studentIdCell = null;
		
		String className = "";
		String studentId = "";
		String studentName = "";
		
		String desc = "初始化导入";
		Integer degree = (Integer)params.get("degree");

		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			
			classCell = row.getCell(0);
			studentNameCell = row.getCell(1);
			studentIdCell = row.getCell(2);
			
			className = classCell.getStringCellValue();
			studentName = studentNameCell.getStringCellValue();
			studentId = studentIdCell.getStringCellValue();
			
			ThesisBase model = new ThesisBase();
			model.setClassName(className);
			model.setStudentName(studentName);
			model.setStudentId(studentId);
			model.setDegree(degree);
			model.setDesc(desc);
			model.setActionType("" +OperationTypeEnum.PLSC.getCode());
			
			model.setOperationTime(currentDate);
			model.setCreateTime(currentDate);
			model.setModifyTime(currentDate);
			
			thesisList.add(model);
		}

		log.info("Batch size: ", thesisList.size());
		
		for (ThesisBase thesis : thesisList) {
			
			thesisBaseService.save(thesis);
		}

		return false;
	}
	
	
	@Autowired
	private IExcelFactoryMapper excelFactoryMapper;
	
	@Autowired
	private ThesisBaseService thesisBaseService;

}
