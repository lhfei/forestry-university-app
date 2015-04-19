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

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.web.multipart.MultipartFile;

import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public class ThesisBaseModel extends AbstractPaginationModel {


	@Override
	public ISearch buildSearch() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.lhfei.fu.web.model.AbstractPaginationModel#getFilters()
	 */
	@Override
	@Deprecated
	public List<Filter> getFilters() {
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.lhfei.fu.web.model.AbstractPaginationModel#wrapperFilter()
	 */
	@Override
	@Deprecated
	public List<SimpleExpression> wrapperFilter() {
		return null;
	}
	

	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	public Integer getStudentBaseId() {
		return studentBaseId;
	}

	public void setStudentBaseId(Integer studentBaseId) {
		this.studentBaseId = studentBaseId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getThesisEnTitle() {
		return thesisEnTitle;
	}

	public void setThesisEnTitle(String thesisEnTitle) {
		this.thesisEnTitle = thesisEnTitle;
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

	public String getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public Integer getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Integer archiveId) {
		this.archiveId = archiveId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	// PRIVATE FIELDS
	private Integer baseId; // 论文ID
	private Integer studentBaseId; // 学生主键ID
	private String studentName; //学生姓名
	private String studentId; // 学生编号
	private int degree = -1;			//学历
	private String score;		// 论文成绩
	private String academicYear; // '学年 academic year'
	private String semester; // '学期(semester)'
	private String className; // '班级名称'
	private String origin; // '论文来源，枚举值包括： [ 科研（1）生产（2） 模拟（3）其它（0）]
	private String thesisTitle; // '论文标题thesis'
	private String thesisEnTitle; // '论文英文标题thesis'
	private String thesisType; // '论文类别，枚举值包括： [ 设计（0） 论文（1） ]',
	private String teacherId; // 教师编号
	private String teacherName; // 教师姓名
	private String teacherTitle;	//指导教师职称
	private Integer archiveId;// 论文附件主键ID
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date operationTime;
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
	private int status; // '作业状态，枚举值包括： 未提交（0） 待审核（1） 已审核（2） 未通过（3） ]'
	private String description;
	
	private List<MultipartFile> files; // 论文附件

}
