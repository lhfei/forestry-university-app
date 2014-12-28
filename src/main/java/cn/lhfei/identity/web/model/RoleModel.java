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

import java.util.ArrayList;
import java.util.List;

import cn.lhfei.identity.orm.domain.Role;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;


/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2014
 */

public class RoleModel extends PaginationModel {

	private String name;
	private String code;
	private int status;		//(0|all, ..)
	
	@Override
	public ISearch buildSearch() {
		Search search = new Search();
		search.setSearchClass(Role.class);
		
		search.setFirstResult(getFirst());
		search.setMaxResults(getPageSize());
		
		if(null != name && name.trim().length() > 0){
			search.addFilterLike("name", "%" + name.trim() + "%");
		}
		
		if(null != code && code.trim().length() > 0){
			search.addFilterLike("code", "%" + code.trim() + "%");
		}
		
		if(status > 0){//just not be all will be filter
			search.addFilterEqual("status", status);
		}
		
		return search;
	}
	
	@Override
	public List<Filter> getFilters() {
		List<Filter> filters = new ArrayList<Filter>();
		
		if(null != name && name.trim().length() > 0){
			Filter nameFilter = new Filter();
			nameFilter.like("name", name.trim());
			filters.add(nameFilter);
		}
		
		if(null != code && code.trim().length() > 0){
			Filter codeFilter = new Filter();
			codeFilter.like("code", code.trim());
			filters.add(codeFilter);
		}
		
		if(status > 0){//just not be all will be filter
			Filter statusFilter = new Filter();
			statusFilter.equal("status", status);
			filters.add(statusFilter);
		}
		
		
		return filters;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
