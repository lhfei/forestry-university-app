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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.web.multipart.MultipartFile;

import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.identity.web.convert.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */

public class HomeworkBaseModel extends AbstractPaginationModel implements Serializable {
	private static final long serialVersionUID = 1991267055753135353L;

	@Override
	public List<SimpleExpression> wrapperFilter() {
		List<SimpleExpression> expList = new ArrayList<SimpleExpression>();
		
		if(academicYear != null){// user routeId to filter 
			SimpleExpression exp = Restrictions.eq("academicYear", academicYear);
			expList.add(exp);
		}
		if (null != semester && semester.trim().length() > 0) {
			SimpleExpression exp = Restrictions.eq("semester", semester.trim());
			expList.add(exp);
		}
		
		if (null != className && className.trim().length() > 0) {
			SimpleExpression exp = Restrictions.eq("className", className.trim());
			expList.add(exp);
		}
		if (null != courseName && courseName.trim().length() > 0) {
			SimpleExpression exp = Restrictions.eq("courseName", courseName.trim());
			expList.add(exp);
		}
		if (null != teacherName && teacherName.trim().length() > 0) {
			SimpleExpression exp = Restrictions.eq("teacherName", teacherName.trim());
			expList.add(exp);
		}
		if (null != majorName && majorName.trim().length() > 0) {
			SimpleExpression exp = Restrictions.eq("majorName", majorName.trim());
			expList.add(exp);
		}
		if (null != name && name.trim().length() > 0) {
			SimpleExpression exp = Restrictions.like("name", name.trim(), MatchMode.ANYWHERE);
			expList.add(exp);
		}
		if (null != teacherId && teacherId.trim().length() > 0) {// just not be all will be filter
			SimpleExpression exp = Restrictions.eq("teacherId", teacherId);
			expList.add(exp);
		}
		if (status > 0) {// just not be all will be filter
			SimpleExpression exp = Restrictions.eq("status", status);
			expList.add(exp);
		}			
		
		return expList;
	}
	
	@Override
	public List<Filter> getFilters() {

		return null;
	}

	@Override
	public ISearch buildSearch() {
		Search search = new Search();
		search.setSearchClass(HomeworkBase.class);
		search.setFirstResult(getFirst());
		search.setMaxResults(getPageSize());

		if (null != academicYear && academicYear.trim().length() > 0) {
			search.addFilterEqual("academicYear", academicYear.trim());
		}
		if (null != semester && semester.trim().length() > 0) {
			search.addFilterEqual("semester", semester.trim());
		}
		if (null != className && className.trim().length() > 0) {
			search.addFilterEqual("className", className.trim());
		}
		if (null != courseName && courseName.trim().length() > 0) {
			search.addFilterEqual("courseName", courseName.trim());
		}
		if (null != teacherName && teacherName.trim().length() > 0) {
			search.addFilterEqual("teacherName", teacherName.trim());
		}
		if (null != majorName && majorName.trim().length() > 0) {
			search.addFilterEqual("majorName", majorName.trim());
		}
		if (null != name && name.trim().length() > 0) {
			search.addFilterLike("name", "%" + name.trim() + "%");
		}

		if (null != teacherId && teacherId.trim().length() > 0) {// just not be all will be filter
			search.addFilterEqual("teacherId", teacherId);
		}

		if (status > 0) {// just not be all will be filter
			search.addFilterEqual("status", status);
		}
		
		// sort by: academicYear-desc|semester-desc

		search.addSortDesc("academicYear");
		search.addSortDesc("semester");

		return search;
	}

	private Integer baseId; // 作业ID
	private Integer studentBaseId; // 学生主键ID
	private String studentId; // 学生编号		
	private Integer homeworkArahiveId;// 作业附件主键ID

	private String studentName; //学生姓名
	private String name; 		// 学生姓名
	private String academicYear; // '学年 academic year'
	private String semester; // '学期(semester)'
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date operationTime;
	private String actionType; // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
	private String teacherId; // 教师编号
	private String teacherName; // 教师姓名

	private String className; // '班级名称'
	private String courseCode; // '课程代码'
	private String courseName; // '课程名称'
	private String majorCode; // '专业代码'
	private String majorName; // '专业名称'
	private String description;
	
	private String archiveName;
	private String archivePath;
	private int status; // '作业状态，枚举值包括： 未提交（0） 待审核（1） 已审核（2） 未通过（3） ]'

	private List<MultipartFile> files; // 作业附件

	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
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

	public Integer getHomeworkArahiveId() {
		return homeworkArahiveId;
	}

	public void setHomeworkArahiveId(Integer homeworkArahiveId) {
		this.homeworkArahiveId = homeworkArahiveId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
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
}
