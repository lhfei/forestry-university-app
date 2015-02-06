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

import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Feb 6, 2015
 */
@Entity
@Table(name = "TEACHING_PERIODS")
public class TeachingPeriods {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERIOD_ID")
	private Integer id;

	@Column(name = "ACADEMIC_YEAR")
	private String academicYear; // '学年 academic year'

	@Column(name = "SEMESTER")
	private String semester; // '学期(semester)'

	@Column(name = "OPERATOR")
	private String operator;

	@Column(name = "OPERATION_TIME")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date operationTime;

	@Column(name = "MODIFY_TIME")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date modifyTime;
}
