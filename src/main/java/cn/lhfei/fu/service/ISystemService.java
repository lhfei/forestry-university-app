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
package cn.lhfei.fu.service;

import java.util.List;

import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.TeachingPeriods;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 6, 2015
 */

public interface ISystemService {

	/**
	 * @param academicYear
	 * @throws Exception
	 */
	void saveAcademicYear(Combobox academicYear) throws Exception;

	/**
	 * @param teachingPeriods
	 * @throws Exception
	 */
	void saveTeachingPeriods(TeachingPeriods teachingPeriods) throws Exception;

	/**
	 * @return
	 */
	List<TeachingPeriods> searchTeachingPeriods();

	/**
	 * @return
	 */
	TeachingPeriods searchCurrentTeachingPeriods() throws Exception;
}
