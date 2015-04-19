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

/**
 * 论文附件基本信息</p>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@Entity
@Table(name = "THESIS_ARCHIVE")
public class ThesisArchive extends AbstractEntity {

	private static final long serialVersionUID = 6003134215086924444L;

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THESIS_ARCHIVE_ID")
	private Integer id;
	
	@Column(name="STUDENT_BASE_ID")
	private Integer studentBaseId;	//学生主键ID
	
	@Column(name = "STUDENT_ID")
	private String studentId; // '学生编号',
	
	@Column(name = "THESIS_BASE_ID")
	private Integer thesisBaseId;

	@Column(name = "THESIS_TITLE")
	private String thesisTitle;

	@Column(name = "ARCHIVE_NAME")
	private String archiveName;

	@Column(name = "ARCHIVE_PATH")
	private String archivePath;
	
	@Column(name = "STATUS")
	private String status; 		//'作业状态，枚举值包括： 未提交（0） 待审核（1） 已审核（2） 未通过（3） ]'

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 学生主键 <code>ID</code>
	 * 
	 * @return
	 */
	public Integer getStudentBaseId() {
		return studentBaseId;
	}

	/**
	 * 学生主键 <code>ID</code>
	 * 
	 * @param studentBaseId
	 */
	public void setStudentBaseId(Integer studentBaseId) {
		this.studentBaseId = studentBaseId;
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

	public Integer getThesisBaseId() {
		return thesisBaseId;
	}

	public void setThesisBaseId(Integer thesisBaseId) {
		this.thesisBaseId = thesisBaseId;
	}

	public String getThesisTitle() {
		return thesisTitle;
	}

	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
