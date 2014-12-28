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

package cn.lhfei.identity.web.model;

import java.util.List;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 27, 2013
 */

public abstract class PaginationModel {
	
	public abstract List<Filter> getFilters();
	
	public abstract ISearch buildSearch();
	
	/**
	 * 当前页起始条目索引
	 * @return
	 */
	public int getFirst(){
		if(pageNum > 1){
			return (pageNum - 1)  * pageSize;
		}else {
			return 0;
		}
	}
	
	/**
	 * 当前页最后一条记录条目索引
	 * @return
	 */
	public int getMax(){
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


	private int pageNum;	//当前页码
	private int pageSize;	//每页显示条目数
	
}
