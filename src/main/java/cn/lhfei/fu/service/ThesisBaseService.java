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

import cn.lhfei.fu.orm.domain.ThesisBase;
import cn.lhfei.fu.web.model.ThesisBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public interface ThesisBaseService {

	/**
	 * 导入论文
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	boolean importThesisBasek(String filePath) throws Exception;
	
	
	/**
	 * @param model
	 * @return
	 */
	List<ThesisBaseModel> getThesis(ThesisBaseModel model) throws Exception;
	
	Integer countThesis(ThesisBaseModel model) throws Exception;
	
	/**
	 * @param thesis
	 * @return
	 * @throws Exception
	 */
	boolean save(ThesisBase thesis) throws Exception;
	
	/**
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	boolean update(ThesisBaseModel model, String userType) throws Exception;
	
	/**
	 * @param model
	 * @return
	 */
	boolean updateThesis(ThesisBaseModel model)  throws IllegalArgumentException ;
}
