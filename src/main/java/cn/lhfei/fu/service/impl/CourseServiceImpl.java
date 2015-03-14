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

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.orm.domain.CourseBase;
import cn.lhfei.fu.orm.persistence.CourseBaseDAO;
import cn.lhfei.fu.service.CourseService;
import cn.lhfei.fu.web.model.CourseBaseModel;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 11, 2015
 */
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Override
	public void create(CourseBase base) throws Exception {
		courseBaseDAO.save(base);
	}

	@Override
	public SearchResult<CourseBase> read(CourseBaseModel model) throws Exception {

		SearchResult<CourseBase> result = courseBaseDAO.searchAndCount(model.buildSearch());

		return result;
	}

	@Override
	public void update(CourseBase base) throws Exception {
		courseBaseDAO.save(base);
	}

	@Override
	public void delete(CourseBase base) throws Exception {
		courseBaseDAO.remove(base);
	}

	@Autowired
	private CourseBaseDAO courseBaseDAO;
}
