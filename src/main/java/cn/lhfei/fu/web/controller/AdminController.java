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
package cn.lhfei.fu.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.fu.orm.domain.ApproveStatus;
import cn.lhfei.fu.orm.domain.ClassBase;
import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.CourseBase;
import cn.lhfei.fu.orm.domain.TeacherBase;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.service.ComboboxService;
import cn.lhfei.fu.service.CourseService;
import cn.lhfei.fu.service.HomeworkArchiveService;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.service.ISystemService;
import cn.lhfei.fu.service.TeacherService;
import cn.lhfei.fu.web.model.CourseBaseModel;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.HomeworkConfModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;
import cn.lhfei.identity.util.JSONReturn;
import cn.lhfei.identity.web.model.JsonReturnModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Mar 11, 2015
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController extends AbstractController {

	// ///////////////////////////////////////////////////////////////////////////////
	// // Homework List View
	// ///////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="homeworkIndex", method=RequestMethod.GET)
	public ModelAndView homeworkIndex(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("admin/hwork/list");
		List<ApproveStatus> list = comboboxService.getApproveStatus();
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		List<Combobox> xqList = comboboxService.getCombobo(XQ);
		List<CourseBase> courseList = comboboxService.getCourse();
		
		TeachingPeriods teachingPeriod = (TeachingPeriods)session.getAttribute(CURRENT_ACADEMICYEAR_SEMESTER);
		
		List<Combobox> classList = comboboxService.getClassByTeacher(null, teachingPeriod);
		
		view.addObject(SPZT, list);
		view.addObject(XN, xnList);
		view.addObject(XQ, xqList);
		view.addObject(COURSE, courseList);
		view.addObject(CLASS, classList);
		
		return view;
	}	
	
	/**
	 * Homework read
	 * 
	 * @param academicYear
	 * @param semester
	 * @param courseName
	 * @param name
	 * @param start
	 * @param page
	 * @param limit
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/homeworkRead", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody JsonReturnModel<HomeworkBaseModel> homeworkRead(
			@RequestParam("academicYear")String academicYear,
			@RequestParam("semester")String semester,
			@RequestParam("courseName")String courseName,
			@RequestParam("className")String className,
			@RequestParam("name")String name,
			@RequestParam("status")Integer status,
			@RequestParam("studentId")String studentId,
			@RequestParam("studentName")String studentName,
			@RequestParam("start")int start,
			@RequestParam("page")int page,
			@RequestParam("limit")int limit, HttpSession session) throws Exception {
		
		//UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		
		TeachingPeriods period = (TeachingPeriods)session.getAttribute(CURRENT_ACADEMICYEAR_SEMESTER);
		
		
		if(null != name && name.trim().length() > 0){
			name = "%" +name+ "%";
		} 
		if(null != studentName && studentName.trim().length() > 0){
			studentName = "%" +studentName+ "%";
		} 
		
		HomeworkBaseModel homework = new HomeworkBaseModel();
		homework.setClassName(className);
		homework.setCourseName(courseName);
		homework.setName(name);
		homework.setStatus(status);
		homework.setStudentId(studentId);
		homework.setStudentName(studentName);
		homework.setPageNum(start);
		homework.setPageSize(limit);
		
		if(COMBOBOX_DEFAULT_VALUE.equals(academicYear)){
			if(period == null) {
				period = systemService.searchCurrentTeachingPeriods();
				homework.setAcademicYear(period.getAcademicYear());
			}
		}else {		
			homework.setAcademicYear(academicYear);
		}
		
		if(COMBOBOX_DEFAULT_VALUE.equals(semester)){
			if(period == null) {
				period = systemService.searchCurrentTeachingPeriods();
				homework.setSemester(period.getSemester());;
			}
		}else{			
			homework.setSemester(semester);
		}
		
		// very impotent!
		homework.setTeacherId(null);
		
		JsonReturnModel<HomeworkBaseModel> json = new JsonReturnModel<HomeworkBaseModel>();
		
		json.setResult(true);
		
		List<HomeworkBaseModel> result = homeworkBaseService.getHomeworkByAdmin(homework);
		int total = homeworkBaseService.countHomeworkByAdmin(homework);
		
		json.setTotal(total);
		json.setRows(result);
		
		return json;	
		
	}	
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // Course Configuration
	// ///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @param start
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView course(HttpSession session) {
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		List<Combobox> xqList = comboboxService.getCombobo(XQ);
		
		ModelAndView view = new ModelAndView("admin/course/course");
		String userId = getUserId(session);
		
		view.addObject(USER_ID, userId);
		view.addObject(XN, xnList);
		view.addObject(XQ, xqList);
		
		return view;
	}

	@RequestMapping(value = "/courseCreate", produces = "application/json",
			method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> courseCreate(@RequestBody CourseBase course) {
		try {
			
			courseService.create(course);
			
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25! \u8bf7\u7a0d\u540e\u91cd\u8bd5.");
		}
	}
	
	@RequestMapping(value = "/courseRead")
	public @ResponseBody JsonReturnModel<CourseBase> courseRead(
			@RequestParam int start, @RequestParam("page") int page,
			@RequestParam int limit) throws Exception {

		JsonReturnModel<CourseBase> json = new JsonReturnModel<CourseBase>();
		CourseBaseModel model = new CourseBaseModel();
		model.setPageNum(start);
		model.setPageSize(limit);
		
		try {
			SearchResult<CourseBase> result = courseService.read(model);
			
			json.setResult(true);
			json.setRows(result.getResult());
			json.setTotal(result.getTotalCount());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			json.setResult(false);
			json.setMessage(e.getMessage());
			json.setTotal(0);
		}

		log.info("paging: start {} to {}", start, limit);

		return json;
	}

	public void courseUpdate() {

	}

	@RequestMapping(value = "/courseDelete", produces = "application/json",
			method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> courseDelete(@RequestBody CourseBase course) {
		try {

			courseService.delete(course);

			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return JSONReturn
					.mapError("\u64cd\u4f5c\u5931\u8d25! \u8bf7\u7a0d\u540e\u91cd\u8bd5.");
		}
	}
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // Course bing Homework
	// ///////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="hwBind", method=RequestMethod.GET)
	public ModelAndView hwBind(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("admin/course/hwBind");
		List<ApproveStatus> list = comboboxService.getApproveStatus();
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		List<Combobox> xqList = comboboxService.getCombobo(XQ);
		List<CourseBase> courseList = comboboxService.getCourse();
		
		String teacherId = getUserId(session);
		
		TeachingPeriods teachingPeriod = (TeachingPeriods)session.getAttribute(CURRENT_ACADEMICYEAR_SEMESTER);
		
		List<Combobox> classList = comboboxService.getClassByTeacher(teacherId, teachingPeriod);
		
		view.addObject(SPZT, list);
		view.addObject(XN, xnList);
		view.addObject(XQ, xqList);
		view.addObject(COURSE, courseList);
		view.addObject(CLASS, classList);
		
		return view;
	}
	
	/**
	 * 课程设置 </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/courseCof", method = RequestMethod.GET)
	public ModelAndView courseCof() {
		ModelAndView view = new ModelAndView("admin/course/courseCof");
		
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		List<Combobox> xqList = comboboxService.getCombobo(XQ);
		view.addObject(XN, xnList);
		view.addObject(XQ, xqList);
		
		return view;
	}
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // Combobox remote data handler. 
	// ///////////////////////////////////////////////////////////////////////////////
	/**
	 * 读取课程列表</p>
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/readCourse")
	public @ResponseBody SearchAndCountModel<CourseBase> readCourse(
			@RequestParam(required = false) int start, @RequestParam(required = false) int limit) {
		SearchAndCountModel<CourseBase> json = new SearchAndCountModel<CourseBase>();
		CourseBaseModel model = new CourseBaseModel();
		model.setPageNum(start);
		model.setPageSize(limit);
		
		try {
			SearchResult<CourseBase> result = courseService.read(model);
			
			json.setRows(result.getResult());
			json.setTotal(result.getTotalCount());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			json.setTotal(0);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/readClass")
	public @ResponseBody SearchAndCountModel<ClassBase> readClass(HttpSession session,
			@RequestParam(required = false) int start, @RequestParam(required = false) int limit) {
		
		SearchAndCountModel<ClassBase> json = new SearchAndCountModel<ClassBase>();
		
		try {
			
			List<ClassBase> classList = comboboxService.getAllClass();
			
			json.setRows(classList);
			json.setTotal(classList.size());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			json.setTotal(0);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/readTeacher")
	public @ResponseBody SearchAndCountModel<TeacherBase> readTeacher(HttpSession session,
			@RequestParam(required = false) int start, @RequestParam(required = false) int limit) {
		
		SearchAndCountModel<TeacherBase> json = new SearchAndCountModel<TeacherBase>();
		
		try {
			
			List<TeacherBase> classList = teacherService.findAll();
			
			json.setRows(classList);
			json.setTotal(classList.size());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			json.setTotal(0);
		}
		
		return json;
	}
	
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // Combobox remote data handler. -- End
	// ///////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/createHomework", method = RequestMethod.GET,produces = "application/json")
	public Map<String, Object> createHomework(HttpSession session,	
			@RequestParam("academicYear") String academicYear,		// 
			@RequestParam("semester") String semester,				// 
			@RequestParam("name") String name,						// 
			@RequestParam("desc") String desc,						//  
			@RequestParam("courseId") String courseId,				// 
			@RequestParam("classId") String classId,				// 
			@RequestParam("teacherId") String teacherId,
			@RequestParam("teacherName") String teacherName) {
		
		try {
			HomeworkConfModel confModel = new HomeworkConfModel();
			confModel.setAcademicYear(academicYear);
			confModel.setSemester(semester);
			confModel.setName(name);
			confModel.setDesc(desc);
			confModel.setCourseId(Arrays.asList(courseId.split(",")));
			confModel.setClassId(Arrays.asList(classId.split(",")));
			confModel.setTeacherId(Arrays.asList(teacherId.split(",")));
			confModel.setTeacherName(Arrays.asList(teacherName.split(",")));
			
			homeworkBaseService.create(confModel);
			
			return JSONReturn.mapOK("\u5ba1\u6279\u6210\u529f!");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			return JSONReturn.mapError("\u5ba1\u6279\u5931\u8d25!"); 
		}
	}
	
	@RequestMapping(value = "/getLatestHomework")
	public @ResponseBody SearchAndCountModel<HomeworkBaseModel> getLatestHomework(HttpSession session,
			@RequestParam(required = false) int start, @RequestParam(required = false) int limit) {
		
		SearchAndCountModel<HomeworkBaseModel> json = new SearchAndCountModel<HomeworkBaseModel>();
		
		try {
			
			List<HomeworkBaseModel> classList = homeworkBaseService.getLatestHomework();
			
			json.setRows(classList);
			json.setTotal(classList.size());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			json.setTotal(0);
		}
		
		return json;
	}
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // 论文管理. -- Start													++++++++++
	// ///////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/thesiskRead", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody JsonReturnModel<HomeworkBaseModel> thesiskRead(
			@RequestParam("academicYear")String academicYear,
			@RequestParam("semester")String semester,
			@RequestParam("courseName")String courseName,
			@RequestParam("className")String className,
			@RequestParam("name")String name,
			@RequestParam("status")Integer status,
			@RequestParam("studentId")String studentId,
			@RequestParam("studentName")String studentName,
			@RequestParam("start")int start,
			@RequestParam("page")int page,
			@RequestParam("limit")int limit, HttpSession session) throws Exception {
		
		//UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		
		TeachingPeriods period = (TeachingPeriods)session.getAttribute(CURRENT_ACADEMICYEAR_SEMESTER);
		
		
		if(null != name && name.trim().length() > 0){
			name = "%" +name+ "%";
		} 
		if(null != studentName && studentName.trim().length() > 0){
			studentName = "%" +studentName+ "%";
		} 
		
		HomeworkBaseModel homework = new HomeworkBaseModel();
		homework.setClassName(className);
		homework.setCourseName(courseName);
		homework.setName(name);
		homework.setStatus(status);
		homework.setStudentId(studentId);
		homework.setStudentName(studentName);
		homework.setPageNum(start);
		homework.setPageSize(limit);
		
		if(COMBOBOX_DEFAULT_VALUE.equals(academicYear)){
			if(period == null) {
				period = systemService.searchCurrentTeachingPeriods();
				homework.setAcademicYear(period.getAcademicYear());
			}
		}else {		
			homework.setAcademicYear(academicYear);
		}
		
		if(COMBOBOX_DEFAULT_VALUE.equals(semester)){
			if(period == null) {
				period = systemService.searchCurrentTeachingPeriods();
				homework.setSemester(period.getSemester());;
			}
		}else{			
			homework.setSemester(semester);
		}
		
		// very impotent!
		homework.setTeacherId(null);
		
		JsonReturnModel<HomeworkBaseModel> json = new JsonReturnModel<HomeworkBaseModel>();
		
		json.setResult(true);
		
		List<HomeworkBaseModel> result = homeworkBaseService.getHomeworkByAdmin(homework);
		int total = homeworkBaseService.countHomeworkByAdmin(homework);
		
		json.setTotal(total);
		json.setRows(result);
		
		return json;	
		
	}		
	// ///////////////////////////////////////////////////////////////////////////////
	// // 论文管理. -- End														++++++++++
	// ///////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ComboboxService comboboxService;
	
	@Autowired
	private ISystemService systemService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private HomeworkBaseService homeworkBaseService;
	
	@Autowired
	private HomeworkArchiveService homeworkArchiveService;
}
