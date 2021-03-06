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

import cn.lhfei.fu.orm.domain.StudentBase;
import cn.lhfei.fu.orm.persistence.StudentBaseDAO;
import cn.lhfei.fu.service.StudentService;
import cn.lhfei.fu.web.model.StudentBaseModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 7, 2015
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Override
	public List<StudentBase> search(StudentBaseModel model) {
		return studentBaseDAO.search(model.buildSearch());
	}

	@Override
	public SearchResult<StudentBase> page(StudentBaseModel model) {
		SearchResult<StudentBase> result = studentBaseDAO
				.searchAndCount(model.buildSearch());
		
		return result;
	}

	@Override
	public StudentBase findById(Integer id) {
		return studentBaseDAO.find(id);
	}

	@Override
	public void delete(Integer[] ids) throws Exception {
		studentBaseDAO.removeByIds(ids);
	}

	@Autowired
	private StudentBaseDAO studentBaseDAO;
}
