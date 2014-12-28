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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.identity.orm.domain.Role;
import cn.lhfei.identity.orm.persistence.RoleDAO;
import cn.lhfei.identity.service.RoleService;
import cn.lhfei.identity.web.model.RoleModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 27, 2014
 */
@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

	public boolean save(Role role) {
		Date date = new Date();
		role.setCreateTime(date);
		role.setModifyTime(date);
		
		return roleDAOimpl.save(role);
	}

	@Override
	public List<Role> findAll(RoleModel roleModel) {
		return roleDAOimpl.search(roleModel.buildSearch());
	}
	
	@Override
	public SearchResult<Role> search(RoleModel roleModel) {
		SearchResult<Role> result = roleDAOimpl.searchAndCount(roleModel.buildSearch());
		return result;
	}

	public void delete(Integer[] ids) throws Exception {
		roleDAOimpl.removeByIds(ids);
	}

	@Autowired
	private RoleDAO roleDAOimpl;
}
