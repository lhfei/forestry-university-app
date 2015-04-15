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

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.orm.domain.TeacherBase;
import cn.lhfei.fu.orm.persistence.TeacherBaseDAO;
import cn.lhfei.fu.service.TeacherService;

import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 7, 2015
 */
@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Override
	public List<TeacherBase> findAll() {
		Search search = new Search();
		search.setSearchClass(TeacherBase.class);
		
		search.addSortAsc("teacherName");
		
		return teacherBaseDAO.search(search);
	}

	@Autowired
	private TeacherBaseDAO teacherBaseDAO;
}
