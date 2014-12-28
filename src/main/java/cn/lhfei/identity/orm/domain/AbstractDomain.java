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
package cn.lhfei.identity.orm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 26, 2014
 */
@SuppressWarnings("serial")
@MappedSuperclass
@JsonAutoDetect
public abstract class AbstractDomain implements Serializable {

	@Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@Column(name="MODIFY_TIME")
	private Date modifyTime;
	
	@Column(name="EXTEND")
	private String extend;
	
	@Column(name="EXTEND1")
	private String extend1;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
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
}
