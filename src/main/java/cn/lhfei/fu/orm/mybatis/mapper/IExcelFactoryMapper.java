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
import cn.lhfei.fu.web.model.StudentBaseModel;
import cn.lhfei.fu.web.model.TeacherBaseModel;
import cn.lhfei.fu.web.model.ThesisBaseModel;
import cn.lhfei.identity.web.model.UserModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 5, 2015
 */
public interface IExcelFactoryMapper {

	/*void importHomework(List<HomeworkBaseModel> homeworkList);*/
	
	void importHomework(HomeworkBaseModel model);
	
	List<TeacherBaseModel> getTeacher();
	
	void updateTeacherId(TeacherBaseModel model);
	
	/**
	 * 导入学生
	 * @param list
	 */
	void importStudent(List<StudentBaseModel> list);
	
	
	/**
	 * 导入系统用户
	 * 
	 * @param list
	 */
	void importSysUser(List<UserModel> list);
	
	
	/**
	 * 导入学生论文
	 * 
	 * @param list
	 */
	void importThesis(List<ThesisBaseModel> list);
	
}
