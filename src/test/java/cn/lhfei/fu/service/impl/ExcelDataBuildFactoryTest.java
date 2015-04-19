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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.service.DataBuildFactory;
import cn.lhfei.fu.test.BaseTestSuite;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 31, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ExcelDataBuildFactoryTest extends BaseTestSuite {

	@Test
	public void importHomework() throws Exception {
		String filePath = "E:\\Webapp_workspace\\webapps_agent\\forestry-university-app\\src\\test\\resource\\excel\\2014-2015_0331.xlsx";
		
		homeworkDataBuildFactory.importDataByExcel(filePath, null);
	}
	
	@Test
	public void importThesis() throws Exception {
		String filePath = "E:\\Webapp_workspace\\webapps_agent\\forestry-university-app\\src\\test\\resource\\excel\\论文模板-本科.xls";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("degree", 0);
		
		thesisDataBuildFactory.importDataByExcel(filePath, params);
	}
	
	
	@Autowired
	private DataBuildFactory homeworkDataBuildFactory;
	
	@Autowired
	private DataBuildFactory thesisDataBuildFactory;
}
