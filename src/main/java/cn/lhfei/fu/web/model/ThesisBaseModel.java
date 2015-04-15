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
package cn.lhfei.fu.web.model;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public class ThesisBaseModel extends AbstractPaginationModel {

	@Override
	public List<Filter> getFilters() {
		return null;
	}

	@Override
	public ISearch buildSearch() {
		return null;
	}

	@Override
	public List<SimpleExpression> wrapperFilter() {
		return null;
	}
	
	
	private Integer baseId;
	private String origin; // '论文来源，枚举值包括： [ 科研（1）生产（2） 模拟（3）其它（0）]
	private String thesisTitle; // '论文标题thesis'
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date operationTime;
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
	private String thesisType; // '论文类别，枚举值包括： [ 设计（0） 论文（1） ]',
	private String teacherId; // 教师编号
	private String teacherName; // 教师姓名
	private String desc;

}
