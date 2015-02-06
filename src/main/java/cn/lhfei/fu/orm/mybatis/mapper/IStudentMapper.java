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
package cn.lhfei.fu.orm.mybatis.mapper;

import java.util.List;

import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.rest.Student;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 18, 2014
 */
public interface IStudentMapper {

	List<HomeworkBaseModel> getHomeworkByStudent(
			HomeworkBaseModel homeworkBaseModel);

	int countHomeworkByStudent(HomeworkBaseModel homeworkBaseModel);

	List<HomeworkBaseModel> readHomework(HomeworkBaseModel model);

	Student findStudentById(String studentId);
	
}
