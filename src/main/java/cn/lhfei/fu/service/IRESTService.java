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

import cn.lhfei.fu.orm.domain.StudentBase;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.rest.HomeworkArchiveModel;
import cn.lhfei.fu.web.model.rest.Student;

/**
 * 
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 5, 2015
 */

/**
 * @author lihf
 *
 */
public interface IRESTService {

	/**
	 * query student base info by student id.
	 * 
	 * @param studentId
	 * @return
	 */
	StudentBase findStudentInfo(String studentId) throws Exception;

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	Student findStudentByStudentId(String studentId) throws Exception;

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	List<HomeworkBaseModel> findHomeworkBaseByStudent(String studentId)
			throws Exception;
	

	/**
	 * @param archive
	 * @throws Exception
	 */
	void saveHomeWorkArchive(HomeworkArchiveModel archive) throws Exception;
}
