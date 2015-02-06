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
package cn.lhfei.fu.constant;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.identity.orm.domain.Menu;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 26, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class HibernateTemplateTest {

	@Test
	public void create() {
		Menu menu = new Menu();
		
		menu.setmName("我的作业");
		menu.setmCode("001");
		menu.setmUrl("myse.do");
		
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(menu);
		
		session.getTransaction().commit();
		
		
	}
	
	@Autowired
	private HibernateTransactionManager hibernateTemplate;
}
