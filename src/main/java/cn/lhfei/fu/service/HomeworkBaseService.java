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

import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public interface HomeworkBaseService {

	boolean save(HomeworkBase role);

	List<HomeworkBase> findAll(HomeworkBaseModel homeworkModel);

	HomeworkBase findById(Integer id);

	void delete(Integer[] ids) throws Exception;

	SearchResult<HomeworkBase> search(HomeworkBaseModel homeworkModel);

	boolean update(HomeworkBaseModel model, String userType) throws NullPointerException;

	boolean approve(Integer[] ids);

	SearchAndCountModel<HomeworkBase> getPageAndCount(HomeworkBaseModel homeworkModel);
	
	boolean importHomework(String filePath) throws Exception;

	void updateTeacherId();
	
	/**
	 * 学生作业列表
	 * @param homeworkBaseModel
	 * @return
	 */
	List<HomeworkBaseModel> getHomeworkByStudent(HomeworkBaseModel homeworkBaseModel);
	
	/**
	 * 统计学上作业
	 * @param homeworkBaseModel
	 * @return
	 */
	int countHomeworkByStudent(HomeworkBaseModel homeworkBaseModel);
	
	/**
	 * 教师作业列表
	 * 
	 * @param homeworkBaseModel
	 * @return
	 */
	List<HomeworkBaseModel> getHomeworkByTeacher(HomeworkBaseModel homeworkBaseModel);
	
	/**
	 * 管理员作业列表
	 * @param homeworkBaseModel
	 * @return
	 */
	List<HomeworkBaseModel> getHomeworkByAdmin(HomeworkBaseModel homeworkBaseModel);
	
	/**
	 * 统计教师作业
	 * 
	 * @param homeworkBaseModel
	 * @return
	 */
	int countHomeworkByTeacher(HomeworkBaseModel homeworkBaseModel);

	/**
	 * 管理员统计作业
	 * @param homeworkBaseModel
	 * @return
	 */
	int countHomeworkByAdmin(HomeworkBaseModel homeworkBaseModel);
}
