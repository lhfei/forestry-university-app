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

import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.service.ThesisBaseService;
import cn.lhfei.fu.web.model.ThesisBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 16, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ThesisBaseServiceImplTest {
	private static final Logger log = LoggerFactory
			.getLogger(ThesisBaseServiceImplTest.class);
	
	@Test
	public void getThesis() throws Exception {
		ThesisBaseModel model = new ThesisBaseModel();
		
		List<ThesisBaseModel> list = thesisBaseService.getThesis(model);
		
		int total = thesisBaseService.countThesis(model);
		
		log.info("List Size: {}, Total: {}", list.size(), total);
		
		Assert.assertTrue(list.size() == total);
	}
	
	
	@Test
	public void getThesisStatue() throws Exception {
		ThesisBaseModel model = new ThesisBaseModel();
		model.setPageNum(0);
		model.setPageSize(10);
		
		List<ThesisBaseModel> list = thesisBaseService.getThesis(model);
		
		for(ThesisBaseModel bean : list){
			log.info("Thesis status: {}", bean.getStatus());
		}
		
	}
	
	@Test
	public void updateThesis() {
		ThesisBaseModel model = new ThesisBaseModel();
		model.setTeacherId("19850108");
		model.setStudentId("110314113");
		model.setBaseId(7313);
		
		thesisBaseService.updateThesis(model);
	}
	
	@Autowired
	private ThesisBaseService thesisBaseService;
}
