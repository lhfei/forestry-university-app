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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.orm.persistence.ComboboxDAO;
import cn.lhfei.fu.orm.persistence.TeachingPeriodsDAO;
import cn.lhfei.fu.service.ISystemService;

import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 6, 2015
 */
@Service(value = "systemService")
@Transactional
public class SystemServiceImpl implements ISystemService {

	private static final Logger log = LoggerFactory
			.getLogger(SystemServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.lhfei.fu.service.ISystemService#saveAcademicYear(cn.lhfei.fu.orm.domain
	 * .Combobox)
	 */
	@Override
	public void saveAcademicYear(Combobox academicYear) throws Exception {
		comboboxDAO.save(academicYear);
	};

	@Override
	public void saveTeachingPeriods(TeachingPeriods teachingPeriods)
			throws Exception {
		teachingPeriodsDAO.save(teachingPeriods);
	}

	@Override
	public List<TeachingPeriods> searchTeachingPeriods() {
		Search search = new Search();
		search.addSortDesc("academicYear");

		List<TeachingPeriods> list = teachingPeriodsDAO.search(search);

		return list;
	}

	@Override
	public TeachingPeriods searchCurrentTeachingPeriods() throws Exception {

		List<TeachingPeriods> list = searchTeachingPeriods();

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}

	@Autowired
	private ComboboxDAO comboboxDAO;

	@Autowired
	private TeachingPeriodsDAO teachingPeriodsDAO;
}
