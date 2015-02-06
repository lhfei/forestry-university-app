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

public abstract class AbstractPaginationModel {
	
	/**
	 * @deprecated
	 * @return
	 */
	public abstract List<Filter> getFilters();
	
	public abstract ISearch buildSearch();
	
	
	/**
	 * @deprecated
	 * @return
	 */
	public abstract List<SimpleExpression> wrapperFilter();

	/**
	 * 当前页起始条目索引
	 * 
	 * @return
	 */
	public int getFirst() {
		if (pageNum > 1) {
			return (pageNum - 1) * pageSize;
		} else {
			return 0;
		}
	}

	/**
	 * 当前页最后一条记录条目索引
	 * 
	 * @return
	 */
	public int getMax() {
		return pageNum * pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	public int getEnd() {
		end = pageNum + pageSize;
		
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	private int pageNum = 0; // 当前页码
	private int pageSize = Integer.MAX_VALUE; // 每页显示条目数
	
	private int end;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date createTime;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date modifyTime;
	private String extend;
	private String extend1;
}
