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
 * 专业基本信息</p>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@JsonAutoDetect
@Entity
@Table(name = "MAJOR_BASE")
public class MajorBase {

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAJOR_BASE_ID")
	private int id;

	@Column(name = "MAJOR_CODE")
	private String majorCode; // '专业代码',

	@Column(name = "MAJOR_NAME")
	private String major_name; // '专业名称'

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	
}
