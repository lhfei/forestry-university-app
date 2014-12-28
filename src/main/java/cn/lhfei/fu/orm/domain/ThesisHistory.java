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

/**
 * 论文操作基本信息
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@Entity
@Table(name = "THESIS_HISTORY")
public class ThesisHistory extends AbstractEntity {
	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THESIS_HISTORY_ID")
	private Integer id;

	@Column(name = "OPERATOR")
	private String operator;

	@Column(name = "OPERATORID")
	private String operatorId;

	@Column(name = "REF_ID")
	private String refId;

	@Column(name = "OPERATION_TIME")
	private Date operationTime;

	@Column(name = "ACTION_TYPE")
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
