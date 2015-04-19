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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 学生基本信息</p>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@Entity
@Table(name = "STUDENT_BASE")
@JsonAutoDetect
public class StudentBase extends AbstractEntity {

	private static final long serialVersionUID = 3827515709038662446L;

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDENT_BASE_ID")
	private Integer id;

	@Column(name = "BIRTHDAY")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date birthday;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GENDER")
	private int gender; // '性别， 枚举值包括[男（0）女（1）]

	@Column(name = "STUDENT_ID")
	private String studentId; // '学生编号',
	
	@Column(name = "DEGREE")
	private int degree;			//学历

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MAJOR_CODE")
	private String majorCode; // '专业代码',

	@Column(name = "MAJOR_NAME")
	private String majorName; // '专业名称'
	
	@Column(name = "CLASS_NAME")
	private String className; // '班级名称'

	@Column(name = "ALIAS_NAME")
	private String aliasName;
	
	@Column(name = "USER_TYPE")
	private String userType = UserTypeEnum.STUDENT.getCode();
	
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

	/**
	 * 学生编号
	 * 
	 * @return
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * 学生编号
	 * 
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
}
