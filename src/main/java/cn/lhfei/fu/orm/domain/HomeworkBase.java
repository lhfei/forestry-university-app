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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 30, 2014
 */
@Entity
@Table(name = "HOMEWORK_BASE")
@JsonAutoDetect
public class HomeworkBase extends AbstractEntity {

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOMEWORK_BASE_ID")
	private Integer id;

	@Column(name = "HW_NAME")
	private String name;

	@Column(name = "ACADEMIC_YEAR")
	private String academicYear; // '学年 academic year'

	@Column(name = "SEMESTER")
	private String semester; // '学期(semester)'

	@Column(name = "OPERATION_TIME")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date operationTime;

	@Column(name = "ACTION_TYPE")
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'

	@Column(name = "TEACHER_ID")
	private String teacherId; // 教师编号

	@Column(name = "TEACHER_NAME")
	private String teacherName; // 教师姓名

	@Column(name = "DESC_INFO")
	private String desc;

	@Column(name = "CLASS_NAME")
	private String className; // '班级名称'

	@Column(name = "COURSE_CODE")
	private String courseCode; // '课程代码'

	@Column(name = "COURSE_NAME")
	private String courseName; // '课程名称'

	@Column(name = "MAJOR_CODE")
	private String majorCode; // '专业代码'

	@Column(name = "MAJOR_NAME")
	private String majorName; // '专业名称'
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HomeworkArchive> archives;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		if(null == operationTime){
			operationTime = new Date();
		}
		this.operationTime = operationTime;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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

	public Set<HomeworkArchive> getArchives() {
		return archives;
	}

	public void setArchives(Set<HomeworkArchive> archives) {
		this.archives = archives;
	}

	//=================================================================================
	@Column(name = "STATUS")
	@Transient 
	private String status;		//'作业状态，枚举值包括： 未提交（0） 待审核（1） 已审核（2） 未通过（3） ]'

	@Transient 
	private String studentId;	
	
	@Transient 
	private String homeworkArahiveId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getHomeworkArahiveId() {
		return homeworkArahiveId;
	}

	public void setHomeworkArahiveId(String homeworkArahiveId) {
		this.homeworkArahiveId = homeworkArahiveId;
	}	
	
	//=================================================================================

	
	
}
