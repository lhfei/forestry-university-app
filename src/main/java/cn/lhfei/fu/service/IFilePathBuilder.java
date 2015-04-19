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

import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.ThesisBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 5, 2014
 */

public interface IFilePathBuilder {

	String getRootPath();

	String mkdirs(String path);

	/**
	 * Build home work name。
	 * 
	 * The file name like this <tt>{学号班级姓名}.{学年}.{学期}.{课程名称}.{作业名称}_{附件张数}</tt>
	 * 
	 * eg. 
	 * 
	 * @param model
	 * @param studentName
	 * @param 附件编号
	 * @return
	 */
	String buildFileName(HomeworkBaseModel model, String studentName, int num);

	/**
	 * Build homework file path.
	 * 
	 * 	{学号}{班级}{姓名}
	 * 
	 * @param model
	 * @param studentName
	 * @return
	 */
	String buildFilePath(HomeworkBaseModel model, String studentName);

	/**
	 * Make homework archives persistence full directory.
	 * @param model
	 * @param studentName
	 * @return
	 * @throws NullPointerException
	 */
	String buildFullPath(HomeworkBaseModel model, String studentName) throws NullPointerException;
	
	
	// ======================== Thesis =============================
	
	/**
	 * @param model
	 * @param studentName
	 * @param num
	 * @return
	 */
	String buildThesisFileName(ThesisBaseModel model, String studentName, int num);
	
	/**
	 * @param model
	 * @param studentName
	 * @return
	 */
	String buildThesisFilePath(ThesisBaseModel model, String studentName);
	
	/**
	 * @param model
	 * @param studentName
	 * @return
	 * @throws NullPointerException
	 */
	String buildThesisFullPath(ThesisBaseModel model, String studentName) throws NullPointerException;
}
