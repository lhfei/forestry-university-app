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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.domain.StudentBase;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 7, 2015
 */
public class StudentBaseModel extends AbstractPaginationModel implements
		Serializable {
	private static final long serialVersionUID = -2954819011036534693L;

	@Override
	public List<Filter> getFilters() {
		return null;
	}

	@Override
	public ISearch buildSearch() {
		Search search = new Search();
		search.setSearchClass(StudentBase.class);
		search.setFirstResult(getFirst());
		search.setMaxResults(getPageSize());
		
		if(null != id && id > 0){
			search.addFilterEqual("id", id);
		}
		if (null != name && name.trim().length() > 0) {
			search.addFilterEqual("name", name.trim());
		}
		if (null != studentId && studentId.trim().length() > 0) {
			search.addFilterEqual("studentId", studentId.trim());
		}
		if (null != email && email.trim().length() > 0) {
			search.addFilterEqual("email", email.trim());
		}
		if (null != majorCode && majorCode.trim().length() > 0) {
			search.addFilterEqual("majorCode", majorCode.trim());
		}
		if (null != majorName && majorName.trim().length() > 0) {
			search.addFilterEqual("majorName", majorName.trim());
		}
		if (null != className && className.trim().length() > 0) {
			search.addFilterEqual("className", className.trim());
		}
		if (null != aliasName && aliasName.trim().length() > 0) {
			search.addFilterEqual("aliasName", aliasName.trim());
		}

		search.addFilterEqual("userType", UserTypeEnum.STUDENT.getCode());

		return search;
	}

	@Override
	public List<SimpleExpression> wrapperFilter() {
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	private Integer id;
	private Date birthday;
	private String name; // 学生姓名
	private int gender; // '性别， 枚举值包括[男（0）女（1）]
	private String studentId; // '学生编号',
	private String email;
	private String majorCode; // '专业代码',
	private String majorName; // '专业名称'
	private String className; // '班级名称'
	private String aliasName; //

}
