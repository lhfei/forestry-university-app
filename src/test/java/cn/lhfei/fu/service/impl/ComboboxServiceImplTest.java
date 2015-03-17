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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.common.constant.ApproveStatusEnum;
import cn.lhfei.fu.common.constant.ConstantCode;
import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.service.ComboboxService;
import cn.lhfei.fu.service.ISystemService;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 13, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ComboboxServiceImplTest {

	private static final Logger log = LoggerFactory
			.getLogger(ComboboxServiceImplTest.class);

	@Test
	public void createAcademicYear(){
		List<Combobox> list = new ArrayList<Combobox>();
		
		Combobox box0 = new Combobox();
		box0.setCode("2010-2011");
		box0.setLabel("2010-2011");
		box0.setKey(ConstantCode.XN_CODE);
		box0.setDesc("学年");
		
		Combobox box1 = new Combobox();
		box1.setCode("2011-2012");
		box1.setLabel("2011-2012");
		box1.setKey(ConstantCode.XN_CODE);
		box1.setDesc("学年");
		
		Combobox box2 = new Combobox();
		box2.setCode("2012-2013");
		box2.setLabel("2012-2013");
		box2.setKey(ConstantCode.XN_CODE);
		box2.setDesc("学年");
		
		Combobox box3 = new Combobox();
		box3.setCode("2013-2014");
		box3.setLabel("2013-2014");
		box3.setKey(ConstantCode.XN_CODE);
		box3.setDesc("学年");
		
		Combobox box4 = new Combobox();
		box4.setCode("2014-2015");
		box4.setLabel("2014-2015");
		box4.setKey(ConstantCode.XN_CODE);
		box4.setDesc("学年");
		
		Combobox box5 = new Combobox();
		box5.setCode("2015-2016");
		box5.setLabel("2015-2016");
		box5.setKey(ConstantCode.XN_CODE);
		box5.setDesc("学年");
		
		list.add(box0);
		list.add(box1);
		list.add(box2);
		list.add(box3);
		list.add(box4);
		list.add(box5);
		
		for(Combobox box : list){
			comboboxService.createCombobox(box);
		}
		
	}
	
	
	@Test
	public void createSemester(){
		List<Combobox> list = new ArrayList<Combobox>();
		
		Combobox box1 = new Combobox();
		box1.setCode("1");
		box1.setLabel("第一学期");
		box1.setKey(ConstantCode.XQ_CODE);
		box1.setDesc("学期");
		
		Combobox box2 = new Combobox();
		box2.setCode("2");
		box2.setLabel("第二学期");
		box2.setKey(ConstantCode.XQ_CODE);
		box2.setDesc("学期");
		
		list.add(box1);
		list.add(box2);
		
		for(Combobox box : list){
			comboboxService.createCombobox(box);
		}
		
	}
	
	@Test
	public void createApproveStatus() {
		/*作业状态，枚举值包括：  [  未提交（0）  待审核（1）  已审核（2）  未通过（3）  ]*/
		
		List<Combobox> list = new ArrayList<Combobox>();
		
		Combobox box1 = new Combobox();
		box1.setCode("" +ApproveStatusEnum.WTJ.getCode());
		box1.setLabel(ApproveStatusEnum.WTJ.getLabel(ApproveStatusEnum.WTJ.getCode()));
		box1.setKey(ApproveStatusEnum.KEY_ZYZT);
		box1.setDesc(ApproveStatusEnum.DESCRIPTION);
		
		Combobox box2 = new Combobox();
		box2.setCode("" +ApproveStatusEnum.DSH.getCode());
		box2.setLabel(ApproveStatusEnum.DSH.getLabel(ApproveStatusEnum.DSH.getCode()));
		box2.setKey(ApproveStatusEnum.KEY_ZYZT);
		box2.setDesc(ApproveStatusEnum.DESCRIPTION);
		
		Combobox box3 = new Combobox();
		box3.setCode("" +ApproveStatusEnum.YSH.getCode());
		box3.setLabel(ApproveStatusEnum.YSH.getLabel(ApproveStatusEnum.YSH.getCode()));
		box3.setKey(ApproveStatusEnum.KEY_ZYZT);
		box3.setDesc(ApproveStatusEnum.DESCRIPTION);
		
		Combobox box4 = new Combobox();
		box4.setCode("" +ApproveStatusEnum.WTG.getCode());
		box4.setLabel(ApproveStatusEnum.WTG.getLabel(ApproveStatusEnum.WTG.getCode()));
		box4.setKey(ApproveStatusEnum.KEY_ZYZT);
		box4.setDesc(ApproveStatusEnum.DESCRIPTION);
		
		list.add(box1);
		list.add(box2);
		list.add(box3);
		list.add(box4);
		
		for(Combobox box : list){
			comboboxService.createCombobox(box);
		}
	}
	
	@Test
	public void getClassByTeacher() throws Exception {

		String teacherId = "19630306";

		TeachingPeriods teachingPeriod = systemService.searchCurrentTeachingPeriods();

		List<Combobox> classList = comboboxService.getClassByTeacher(teacherId,
				teachingPeriod);
		
		for(Combobox box : classList){
			log.info("ClassName: {}, Code: {}", box.getLabel(), box.getCode());
		}
	}
	
	@Test
	public void batchUpdateTeacherIdByName() {
		comboboxService.batchUpdateTeacherIdByName();
	}

	@Autowired
	private ComboboxService comboboxService;
	@Autowired
	private ISystemService systemService;
}
