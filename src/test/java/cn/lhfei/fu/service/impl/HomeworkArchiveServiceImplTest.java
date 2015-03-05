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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.common.constant.ApproveStatusEnum;
import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.service.HomeworkArchiveService;
import cn.lhfei.fu.test.BaseTestSuite;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 5, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class HomeworkArchiveServiceImplTest extends BaseTestSuite {

	@Test
	public void updateArachive() throws Exception {
		Integer id = new Integer(18);
		String desc = "特殊处理";
		String status = ""+ApproveStatusEnum.YSH.getCode();
		
		homeworkArchiveService.updateArachive(id, status, desc,
				UserTypeEnum.TEACHER.getCode());
	}
	
	
	@Autowired
	private HomeworkArchiveService homeworkArchiveService;
}
