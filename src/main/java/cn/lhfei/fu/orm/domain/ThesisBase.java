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
 * 论文基本信息</p>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@Entity
@Table(name = "THESIS_BASE")
public class ThesisBase extends AbstractEntity {
	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THESIS_BASE_ID")
	private Integer id;

	@Column(name = "ORIGIN")
	private String origin; // '论文来源，枚举值包括： [ 科研（1）生产（2） 模拟（3）其它（0）]

	@Column(name = "THESIS_TITLE")
	private String thesisTitle; // '论文标题thesis'

	@Column(name = "OPERATION_TIME")
	private Date operationTime;

	@Column(name = "ACTION_TYPE")
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'

	@Column(name = "THESIS_TYPE")
	private String thesisType; // '论文类别，枚举值包括： [ 设计（0） 论文（1） ]',

	@Column(name = "TEACHER_ID")
	private String teacherId; // 教师编号

	@Column(name = "TEACHER_NAME")
	private String teacherName; // 教师姓名

	@Column(name = "DESC_INFO")
	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getThesisTitle() {
		return thesisTitle;
	}

	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
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

	public String getThesisType() {
		return thesisType;
	}

	public void setThesisType(String thesisType) {
		this.thesisType = thesisType;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
