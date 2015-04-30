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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.common.constant.DegreeEnum;
import cn.lhfei.fu.service.IExcelService;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 30, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ExcelServiceImplTest {

	
	private String ROOT_PATH = "E:\\Webapp_workspace\\webapps_agent\\forestry-university-app\\src\\test\\resources\\excel\\0430\\";
	
	/**
	 * 导入本科生信息
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	@Test
	public void importNongraduate() {
		try {
			DegreeEnum degreeEnum = DegreeEnum.BK;
			String filePath = ROOT_PATH + "本科生-基本信息.xlsx";
			InputStream file = new FileInputStream(filePath);
			
			excelService.importStudent(file, degreeEnum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 导入研究生信息
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	@Test
	public void importGraduate() {
		try {
			DegreeEnum degreeEnum = DegreeEnum.SS;
			String filePath = ROOT_PATH + "研究生-基本信息.xlsx";
			InputStream file = new FileInputStream(filePath);
			
			excelService.importStudent(file, degreeEnum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 导入本科生论文
	 */
	@Test
	public void importNongraduateThesis() {
		try {
			DegreeEnum degreeEnum = DegreeEnum.BK;
			String filePath = ROOT_PATH + "本科生-论文.xls";
			InputStream file = new FileInputStream(filePath);
			
			excelService.importThesis(file, degreeEnum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 导入研究生论文
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	@Test
	public void importGraduateThesis() {
		try {
			DegreeEnum degreeEnum = DegreeEnum.SS;
			String filePath = ROOT_PATH + "研究生-论文.xlsx";
			InputStream file = new FileInputStream(filePath);
			
			excelService.importThesis(file, degreeEnum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Autowired
	private IExcelService excelService;
}
