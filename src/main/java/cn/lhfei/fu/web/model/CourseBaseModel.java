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

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import cn.lhfei.fu.orm.domain.CourseBase;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 12, 2015
 */

public class CourseBaseModel extends AbstractPaginationModel {

	@Override
	public List<Filter> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SimpleExpression> wrapperFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ISearch buildSearch() {
		Search search = new Search();
		search.setSearchClass(CourseBase.class);
		search.setFirstResult(super.getPageNum());
		search.setMaxResults(getPageSize());
		
		if(null != className && className.trim().length() > 0 ){
			search.addFilterEqual("className", className.trim());
		}
		if(null != courseName && courseName.trim().length() > 0 ){
			search.addFilterLike("courseName", "%" + courseName.trim() + "%");
		}
		if(null != courseCode && courseCode.trim().length() > 0 ){
			search.addFilterEqual("courseCode", courseCode.trim());
		}
		if(null != academicYear && academicYear.trim().length() > 0 ){
			search.addFilterEqual("academicYear", academicYear.trim());
		}
		if(null != semester && semester.trim().length() > 0 ){
			search.addFilterEqual("semester", semester.trim());
		}
		
		return search;
	}
	
	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}


	private Integer baseId;
	private String className; // '班级名称',
	private String courseCode; // '课程代码',
	private String courseName; // '课程名称',
	private String academicYear; // '学年 academic year'
	private String semester; // '学期(semester)'

}
