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

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.identity.common.util.SecurityUtils;
import cn.lhfei.identity.orm.domain.AbstractDomain;
import cn.lhfei.identity.orm.domain.User;
import cn.lhfei.identity.orm.persistence.IdentityDAO;
import cn.lhfei.identity.service.IdentityService;
import cn.lhfei.identity.service.MenuService;
import cn.lhfei.identity.service.RoleService;
import cn.lhfei.identity.service.UserService;
import cn.lhfei.identity.web.model.UserModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 27, 2014
 */
@Service("identityService")
@Transactional
public class IdentityServiceImpl implements IdentityService {

	@Override
	public boolean save(AbstractDomain entity) {
		return identityDAO.save(entity);
	}
	
	
	@Override
	public User login(String userId, String passWord) {
		User user = null;
		
		UserModel userModel = new UserModel();
		userModel.setUserId(userId);
		userModel.setPassWord(SecurityUtils.toMd5(passWord));
		
		List<User> list = userService.search(userModel);
		
		if(list != null && list.size() == 1){
			user = list.get(0);
		}
		
		return user;
	}
	
	@Override
	public boolean restPassword(String userId, String password) {
		
		password = SecurityUtils.toMd5(password);
		
		int result = identityDAO.restPassword(userId, password);
		
		return result > 0 ;
	}
	

	@Autowired
	private IdentityDAO identityDAO;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
}
