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
package cn.lhfei.identity.service;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;

import cn.lhfei.identity.orm.domain.User;
import cn.lhfei.identity.web.model.UserModel;


/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 5, 2015
 */

public interface UserService {

	User findById(Integer id);
	
	List<User> search(UserModel user);
	
	SearchResult<User> searchPage(UserModel user);
	
	/**
	 * <p>
	 * If an entity already exists in the datastore with the same id, call
	 * _update and return false (not new). If no such entity exists in the
	 * datastore, call _save() and return true (new)
	 * 
	 * @return <code>true</code> if _save(); <code>false</code> if _update().
	 */
	boolean saveOrUpdate(User user);
	
}
