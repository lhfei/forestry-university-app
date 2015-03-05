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
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.orm.domain.HomeworkArchive;
import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 18, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class HomeworkBaseServiceImplTest {

	private static final Logger log = LoggerFactory
			.getLogger(HomeworkBaseServiceImplTest.class);
	
	@Test
	public void getHomeworkByStudent(){
		HomeworkBaseModel homeworkModel = new HomeworkBaseModel();
		homeworkModel.setClassName("园林建筑设计一班");
		homeworkModel.setStudentBaseId(new Integer(1));
		/*homeworkModel.setPageNum(2);
		homeworkModel.setPageSize(2);*/
		homeworkModel.setName("铅笔");
		
		List<HomeworkBaseModel> list = homeworkBaseService.getHomeworkByStudent(homeworkModel);
		
		for(HomeworkBaseModel base : list){
			log.info("Student ID: {}, Archive ID: {}",base.getStudentId(), base.getHomeworkArahiveId());
		}
		
		log.info("Result size is {}", list.size());
	}
	
	@Test
	public void countHomeworkByStudent(){
		HomeworkBaseModel homeworkModel = new HomeworkBaseModel();
		homeworkModel.setClassName("园林建筑设计一班");
		homeworkModel.setStudentBaseId(new Integer(1));
		/*homeworkModel.setPageNum(2);
		homeworkModel.setPageSize(2);*/
		//homeworkModel.setName("铅笔");
		
		int total = homeworkBaseService.countHomeworkByStudent(homeworkModel);
		

		
		log.info("Result size is {}", total);
	}
	
	@Test
	@Transactional
	public void findById() {
		HomeworkBase hw = homeworkBaseService.findById(5);
		
		log.info("length {}", hw.getArchives().size());
	}
	
	
	@Test
	public void search() {
		HomeworkBaseModel homeworkModel = new HomeworkBaseModel();
		homeworkModel.setClassName("城市规划设计一班");
		
		SearchResult<HomeworkBase> result = homeworkBaseService.search(homeworkModel);
		
		log.info("Size: {}", result.getTotalCount());
	}
	
	@Test
	public void getPageAndCount() {
		
		HomeworkBaseModel homeworkModel = new HomeworkBaseModel();
		
		homeworkModel.setClassName("城市规划设计一班");
		
		SearchAndCountModel<HomeworkBase> result = homeworkBaseService.getPageAndCount(homeworkModel);
		List<HomeworkBase> list = result.getRows();
		
		log.info("Size: {}", list.size());
		
		for(HomeworkBase base : list){
			Set<HomeworkArchive> set = base.getArchives();
			log.info("Size " +set.size());
		}
	}
	
	@Test
	public void importHomework() {
		String filePath = "E:\\Webapp_workspace\\webapps_agent\\forestry-university-app\\src\\test\\resource\\excel\\2014-2015.xlsx";
		
		try {
			homeworkBaseService.importHomework(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTeacherId() {
		homeworkBaseService.updateTeacherId();
	}
	
	
	@Autowired
	private HomeworkBaseService homeworkBaseService;
}
