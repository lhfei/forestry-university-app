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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.domain.ApproveStatus;
import cn.lhfei.fu.orm.domain.ClassBase;
import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.CourseBase;
import cn.lhfei.fu.orm.domain.TeacherBase;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.orm.domain.ThesisArchive;
import cn.lhfei.fu.service.ComboboxService;
import cn.lhfei.fu.service.CourseService;
import cn.lhfei.fu.service.HomeworkArchiveService;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.service.ISystemService;
import cn.lhfei.fu.service.TeacherService;
import cn.lhfei.fu.service.ThesisArchiveService;
import cn.lhfei.fu.service.ThesisBaseService;
import cn.lhfei.fu.web.model.CourseBaseModel;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.HomeworkConfModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;
import cn.lhfei.fu.web.model.ThesisBaseModel;
import cn.lhfei.identity.util.JSONReturn;
import cn.lhfei.identity.web.model.JsonReturnModel;
import cn.lhfei.identity.web.model.UserSession;

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
			name = "%" +name.trim()+ "%";
		} 
		if(null != studentName && studentName.trim().length() > 0){
			studentName = "%" +studentName.trim()+ "%";
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
	
	/**
	 * 查询所有班级
	 * 
	 * @param session
	 * @param start
	 * @param limit
	 * @return
	 */
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
	
	/**
	 * 查询所有教师 </p>
	 * 
	 * @param session
	 * @param start
	 * @param limit
	 * @return
	 */
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
	/**
	 * 课程作业设置 </p>
	 * 
	 * @param session
	 * @param academicYear
	 * @param semester
	 * @param name
	 * @param desc
	 * @param courseId
	 * @param classId
	 * @param teacherId
	 * @param teacherName
	 * @return
	 */
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
	
	/**
	 * 获取当前最新增加的学生作业</p>
	 * @param session
	 * @param start
	 * @param limit
	 * @return
	 */
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
	
	@RequestMapping(value="thesisIndex", method=RequestMethod.GET)
	public ModelAndView thesisIndex(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("admin/thesis/list");
		List<ApproveStatus> list = comboboxService.getApproveStatus();
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		
		TeachingPeriods teachingPeriod = (TeachingPeriods)session.getAttribute(CURRENT_ACADEMICYEAR_SEMESTER);
		
		List<Combobox> classList = comboboxService.getClassByTeacher(null, teachingPeriod);
		List<TeacherBase> teacherList = teacherService.findAll();
		
		for(Combobox box : xnList){
			String value = box.getLabel().split("-")[1];
			box.setCode(value);
			box.setLabel(value);
		}
		
		view.addObject("ZTJS", teacherList);
		view.addObject(SPZT, list);
		view.addObject(XN, xnList);
		view.addObject(CLASS, classList);
		
		return view;
	}	
	
	/**
	 * 论文列表
	 * 
	 * @param academicYear
	 * @param semester
	 * @param courseName
	 * @param className
	 * @param name
	 * @param status
	 * @param studentId
	 * @param studentName
	 * @param start
	 * @param page
	 * @param limit
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/thesisRead", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody JsonReturnModel<ThesisBaseModel> thesisRead(
			@RequestParam(value = "academicYear", required = false)String academicYear,
			@RequestParam(value = "className", required = false)String className,
			@RequestParam(value = "thesisType", required = false)String thesisType,
			@RequestParam(value = "origin", required = false)String origin,
			@RequestParam(value = "degree", required = false)int degree,
			@RequestParam(value = "status", required = false)int status,
			@RequestParam(value = "studentId", required = false)String studentId,
			@RequestParam(value = "studentName", required = false)String studentName,
			@RequestParam("start")int start,
			@RequestParam("page")int page,
			@RequestParam("limit")int limit, HttpSession session) throws Exception {
		
		UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		JsonReturnModel<ThesisBaseModel> json = new JsonReturnModel<ThesisBaseModel>();
		
		ThesisBaseModel thesisModel = new ThesisBaseModel();
		
		if(userSession != null){
			/*if(null != thesisTitle && thesisTitle.trim().length() > 0){
				thesisTitle = "%" +thesisTitle.trim()+ "%";
			}*/ 
			if(null != studentName && studentName.trim().length() > 0){
				studentName = "%" +studentName.trim()+ "%";
			} 
			
			thesisModel.setAcademicYear(academicYear);
			thesisModel.setClassName(className);
			thesisModel.setThesisType(thesisType);
			thesisModel.setOrigin(origin);
			thesisModel.setDegree(degree);
			thesisModel.setStatus(status);
			thesisModel.setStudentName(studentName);
			thesisModel.setStudentId(studentId);
			thesisModel.setPageNum(start);
			thesisModel.setPageSize(limit);
			
			// very important!
			String userType = userSession.getUser().getUserType();
			
			if(UserTypeEnum.ADMIN.getCode().equals(userType)){
				thesisModel.setTeacherId(null);
			}else if(UserTypeEnum.TEACHER.getCode().equals(userType)){
				thesisModel.setTeacherId(userSession.getUser().getUserId());
			} else if(UserTypeEnum.STUDENT.getCode().equals(userType)){
				thesisModel.setStudentId(userSession.getUser().getUserId());
			}
			
			json.setResult(true);
			
			List<ThesisBaseModel> result = thesisBaseService.getThesis(thesisModel);
			int total = thesisBaseService.countThesis(thesisModel);
			
			json.setTotal(total);
			json.setRows(result);
			
		}else {// session expired.
			json.setResult(false);
			json.setMessage("Session expired.");
		}
		
		return json;	
		
	}
	
	
	/**
	 * @param uploadForm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "updateThesis", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> updateThesis(
			@ModelAttribute("uploadForm") ThesisBaseModel uploadForm,
			HttpSession session) {
		
		String userType = (String)session.getAttribute(USER_TYPE);
		boolean result = false;
		
		try {
			
			uploadForm.setTeacherId(uploadForm.getTeacherName());
			
			result = thesisBaseService.update(uploadForm, userType);
			
			if(result){
				return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
			}else 
				return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			return JSONReturn.mapError(e.getMessage());
		}

	}
	
	@RequestMapping(value = "/preload", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Object> preload(
			@RequestParam("thesisBaseId") String thesisBaseId,
			@RequestParam("studentId") String studentId) {
		
		try {
			
			List<ThesisArchive> archives = thesisArchiveService.findArchive(thesisBaseId, studentId);
			
			return JSONReturn.mapOK(archives);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			return JSONReturn.mapError(e.getMessage());
		}
	}
	
	@RequestMapping(value="downloadArchive", method = RequestMethod.GET)
	public void downloadArchive(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") int id) throws IOException {
		try {
			ThesisArchive thesisAtchive = thesisArchiveService.read(id);
			
			if(null != thesisAtchive){
				String archivePath = thesisAtchive.getArchivePath();
				String[] separatorPaths = archivePath.split("\\" +File.separator);
				String archiveName = separatorPaths[separatorPaths.length -1];
				
				File file = new File(archivePath);
				//String fileName = thesisAtchive.getArchiveName();
				
				// get your file as InputStream
				InputStream is = new FileInputStream(file);
				response.setContentType("application/image;charset=UTF-8");
				response.setContentLength(new Long(file.length()).intValue());
				response.setHeader("Content-Disposition", "attachment; filename="+archiveName+"; charset=UTF-8");
				
				// copy it to response's OutputStream
				org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
			throw new RuntimeException("IOError writing file to output stream", ex);
		} 
	}
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // 论文管理. -- End														++++++++++
	// ///////////////////////////////////////////////////////////////////////////////
	
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // 模板下载. -- Start													++++++++++
	// ///////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/nongraduateFor", method = RequestMethod.GET)
	public ModelAndView nongraduateFor() {
		ModelAndView view = new ModelAndView("admin/template/nongraduateFor");
		
		return view;
	}	
	
	@RequestMapping(value = "/graduateFor", method = RequestMethod.GET)
	public ModelAndView graduateFor() {
		ModelAndView view = new ModelAndView("admin/template/graduateFor");
		
		return view;
	}	
	
	// ///////////////////////////////////////////////////////////////////////////////
	// // 模板下载. -- End														++++++++++
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
	private ThesisBaseService thesisBaseService;
	
	@Autowired
	private HomeworkBaseService homeworkBaseService;
	
	@Autowired
	private HomeworkArchiveService homeworkArchiveService;
	
	@Autowired
	private ThesisArchiveService thesisArchiveService;
}
