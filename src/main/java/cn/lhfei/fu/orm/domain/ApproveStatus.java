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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 审核状态表
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 13, 2014
 */
@Entity
@Table(name = "APPROVE_STATUS")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ApproveStatus {
	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPROVE_STATUS_ID")
	private Integer id;		//审核状态表主键ID
	
	@Column(name = "DICT_TEXT")
	private String text;
	
	@Column(name = "DICT_CODE")
	private String code;
	
	@Column(name = "DICT_DESC")
	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
