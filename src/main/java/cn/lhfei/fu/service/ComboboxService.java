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

import cn.lhfei.fu.orm.domain.ApproveStatus;
import cn.lhfei.fu.orm.domain.ClassBase;
import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.CourseBase;
import cn.lhfei.fu.orm.domain.ThesisOrigin;

import com.googlecode.genericdao.search.ISearch;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 13, 2014
 */

public interface ComboboxService {

	/**
	 * 查询审批状态
	 * 
	 * @return
	 */
	List<ApproveStatus> getApproveStatus();
	
	/**
	 * 查询论文来源
	 * 
	 * @return
	 */
	List<ThesisOrigin> getThesisOrigin();
	
	List<Combobox> getCombobo(ISearch search);
	
	List<Combobox> getCombobo(String key);
	
	/**
	 * 查询全部课程
	 * 
	 * @return
	 */
	List<CourseBase> getCourse();
	
	/**
	 * 根据课程ID查询班级
	 * 
	 * @param courseId
	 * @return
	 */
	List<ClassBase> getClassByCourseId(int courseId);
	
	void createCombobox(Combobox combobox);
	
	
	boolean deleteApproveStatus(Integer[] ids);
	
	boolean deleteThesisOrigin(Integer[] ids);
	
	
}
