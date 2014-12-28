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
package cn.lhfei.fu.orm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 课程基本信息</p>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@JsonAutoDetect
@Entity
@Table(name = "COURSE_BASE")
public class CourseBase {
	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_BASE_ID")
	private Integer id;

	@Column(name = "CLASS_NAME")
	private String className; // '班级名称',

	@Column(name = "COURSE_CODE")
	private String courseCode; // '课程代码',

	@Column(name = "COURSE_NAME")
	private String courseName; // '课程名称',

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
