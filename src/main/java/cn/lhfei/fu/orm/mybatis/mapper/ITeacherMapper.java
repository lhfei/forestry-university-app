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
import java.util.Map;

import cn.lhfei.fu.web.model.HomeworkBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 18, 2014
 */

public interface ITeacherMapper {

	List<HomeworkBaseModel> getHomeworkByTeacher(HomeworkBaseModel homeworkBaseModel);
	
	List<HomeworkBaseModel> getHomeworkByAdmin(HomeworkBaseModel homeworkBaseModel);
	
	int countHomeworkByTeacher(HomeworkBaseModel homeworkBaseModel);
	
	int countHomeworkByAdmin(HomeworkBaseModel homeworkBaseModel);
	
	List<HomeworkBaseModel> readHomework(HomeworkBaseModel model);
	
	void updateArachive(Map<String, Object> map) throws Exception;
}
