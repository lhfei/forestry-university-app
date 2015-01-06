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

import cn.lhfei.identity.orm.domain.User;
import cn.lhfei.identity.orm.persistence.UserDAO;
import cn.lhfei.identity.service.UserService;
import cn.lhfei.identity.web.model.UserModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 5, 2015
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public User findById(Integer id) {
		User user = userDAO.find(id);

		return user;
	}

	@Override
	public List<User> search(UserModel user) {
		List<User> result = userDAO.search(user.buildSearch());

		return result;
	}

	@Override
	public SearchResult<User> searchPage(UserModel user) {
		SearchResult<User> result = userDAO.searchAndCount(user.buildSearch());

		return result;
	}

	/**
	 * <p>
	 * If an entity already exists in the datastore with the same id, call
	 * _update and return false (not new). If no such entity exists in the
	 * datastore, call _save() and return true (new)
	 * 
	 * @return <code>true</code> if _save(); <code>false</code> if _update().
	 */
	@Override
	public boolean saveOrUpdate(User user) {
		return userDAO.save(user);
	}

	@Autowired
	private UserDAO userDAO;

}
