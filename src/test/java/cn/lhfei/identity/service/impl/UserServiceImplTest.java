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
package cn.lhfei.identity.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.identity.common.util.SecurityUtils;
import cn.lhfei.identity.orm.domain.Role;
import cn.lhfei.identity.orm.domain.User;
import cn.lhfei.identity.service.RoleService;
import cn.lhfei.identity.service.UserService;
import cn.lhfei.identity.web.model.UserModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 5, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class UserServiceImplTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Test
	@Transactional
	public void findById() {
		User user = userService.findById(new Integer(1));
		
		Role role = user.getRole();
		
		log.info("Role_id is {}", role.getRoleId());
		
	}
	
	@Test
	public void search() {
		UserModel user = new UserModel();
		user.setUserId("lhfei");
		
		List<User> list = userService.search(user);
		
		log.info("Size: {}", list.size());
	}
	
	@Test
	public void saveOrUpdate() {
		User user = userService.findById(new Integer(1));
		
		String oldPass = user.getPassWord();
		
		String newPass = SecurityUtils.toMd5("Lhfei");
		
		user.setPassWord(newPass);
		
		boolean result = userService.saveOrUpdate(user);
		
		org.junit.Assert.assertTrue(!result);
		
		user = userService.findById(new Integer(1));
		org.junit.Assert.assertNotSame(oldPass, newPass);;
		

	}
	
	@Test
	public void createUser() {
		Date date = new Date();
		Role role = roleService.findById(new Integer(22));
		
		
		User user = new User();
		user.setAliasName("admin");
		
		user.setEmail("admin@163.com");
		user.setCreateTime(date);
		user.setGender(1);
		user.setModifyTime(date);
		user.setPassWord(SecurityUtils.toMd5("admin"));
		user.setRole(role);
		
		user.setSignTime(date);
		user.setUserId("10000000");
		user.setUserName("系统管理员");
		user.setUserType(UserTypeEnum.ADMIN.getCode());
		
		userService.saveOrUpdate(user);
	}
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
}
