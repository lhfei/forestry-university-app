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

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.service.ISystemService;
import cn.lhfei.fu.test.BaseTestSuite;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 6, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class SystemServiceImplTest extends BaseTestSuite {

	@Test
	public void saveAcademicYear() {
		Combobox academicYear = new Combobox();
		academicYear.setCode("2015-2016");
		academicYear.setLabel("2015-2016");
		academicYear.setKey("XN");
		academicYear.setDesc("学年");
		
		try {
			
			systemService.saveAcademicYear(academicYear);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveTeachingPeriods() {
		Date current = new Date();
		
		TeachingPeriods periods = new TeachingPeriods();
		periods.setAcademicYear("2014-2015");
		periods.setSemester("第一学期");
		periods.setOperator("admin");
		periods.setOperationTime(current);
		periods.setModifyTime(current);
		
		try {
			systemService.saveTeachingPeriods(periods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchCurrentTeachingPeriods() {
		
		List<TeachingPeriods> list = systemService.searchTeachingPeriods();
		
		Assert.assertTrue(list !=null && list.size() > 0);
		
		log.debug("", list.size());
		
		TeachingPeriods period = list.get(0);
		
		log.debug("AcademicYear: [{}], Semester: [{}]", period.getAcademicYear(), period.getSemester());
	}
	
	@Autowired
	private ISystemService systemService;
}
