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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import cn.lhfei.fu.orm.domain.HomeworkArchive;
import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.service.ComboboxService;
import cn.lhfei.fu.service.HomeworkArchiveService;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.service.ISystemService;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.identity.util.JSONReturn;
import cn.lhfei.identity.web.model.IdsModel;
import cn.lhfei.identity.web.model.JsonReturnModel;
import cn.lhfei.identity.web.model.UserSession;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 22, 2014
 */
@Controller
@RequestMapping("teacher")
public class TeacherController extends AbstractController {

	@RequestMapping(value="homeworkIndex", method=RequestMethod.GET)
	public ModelAndView homeworkIndex(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("teacher/hwork/list");
		List<ApproveStatus> list = comboboxService.getApproveStatus();
		List<Combobox> xnList = comboboxService.getCombobo(XN);
		List<Combobox> xqList = comboboxService.getCombobo(XQ);
		List<CourseBase> courseList = comboboxService.getCourse();
		
		String teacherId = getUserId(session);
		
		TeachingPeriods teachingPeriod = systemService.searchCurrentTeachingPeriods();
		
		List<Combobox> classList = comboboxService.getClassByTeacher(teacherId, teachingPeriod);
		
		view.addObject(SPZT, list);
		view.addObject(XN, xnList);
		view.addObject(XQ, xqList);
		view.addObject(COURSE, courseList);
		view.addObject(CLASS, classList);
		
		return view;
	}
	
	@RequestMapping(value="thesisIndex", method=RequestMethod.GET)
	public ModelAndView thesisIndex(HttpSession session) {
		ModelAndView view = new ModelAndView("teacher/thesis/list");
		return view;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//// 作业管理管理
	//// Process step  	... 											
	/////////////////////////////////////////////////////////////////////////////////
	
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
			@RequestParam("status")Integer status,
			@RequestParam("studentId")String studentId,
			@RequestParam("studentName")String studentName,
			@RequestParam("name")String name,
			@RequestParam("start")int start,
			@RequestParam("page")int page,
			@RequestParam("limit")int limit, HttpSession session) throws Exception {
		
		UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		
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
		
		homework.setTeacherId(userSession.getUser().getUserId());
		
		JsonReturnModel<HomeworkBaseModel> json = new JsonReturnModel<HomeworkBaseModel>();
		
		json.setResult(true);
		
		/*SearchResult<HomeworkBase> result = homeworkBaseService.search(homework);
		json.setRows(result.getResult());
		json.setTotal(result.getTotalCount());*/
		
		List<HomeworkBaseModel> result = homeworkBaseService.getHomeworkByTeacher(homework);
		//result.get(0).setId(789);
		int total = homeworkBaseService.countHomeworkByTeacher(homework);
		
		json.setTotal(total);
		json.setRows(result);
		
		return json;	
		
	}

	/**
	 * upload homework archives.
	 * 
	 * @param uploadForm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "updateHomework", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> updateHomework(
			@ModelAttribute("uploadForm") HomeworkBaseModel uploadForm,
			HttpSession session) {
		
		String userType = (String)session.getAttribute(USER_TYPE);
		
		UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		
		//uploadForm.setStudentId(userSession.getUser().getUserId());
		//uploadForm.setStudentName(userSession.getUser().getUserName());
		
		homeworkBaseService.update(uploadForm, userType);

		return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
	}
	
	/**
	 * approve homework.
	 * 
	 * @param id
	 * @param desc
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/approveHomework", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody Map<String, Object> approveHomework(
			@RequestParam("id")Integer id, @RequestParam("desc")String desc, 
			@RequestParam("status")String status, HttpSession session) {
		
		try {
			
			String userType = (String)session.getAttribute(USER_TYPE);
			
			String message = homeworkArchiveService.updateArachive(id, status, desc, userType);
			
			if("0".equals(message)){
				return JSONReturn.mapOK("\u5ba1\u6279\u6210\u529f!");
			}else
				return JSONReturn.mapOK("\u5ba1\u6279\u5931\u8d25!" +message);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return JSONReturn.mapError("\u5ba1\u6279\u5931\u8d25!");
		}
		
	}
	
	/**
	 * Method for handling file download request from client
	 */
	@RequestMapping(value="downloadImg", method = RequestMethod.GET)
	public void downloadImg(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") int id) throws IOException {
		try {
			HomeworkArchive homeworkArchive = homeworkArchiveService.read(id);
			if(null != homeworkArchive){
				File file = new File(homeworkArchive.getArchivePath());
				String fileName = homeworkArchive.getArchiveName();
				//fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
				// get your file as InputStream
				InputStream is = new FileInputStream(file);
				response.setContentType("application/image;charset=UTF-8");
				response.setContentLength(new Long(file.length()).intValue());
				response.setHeader("Content-Disposition", "attachment; filename="+fileName+".jpg; charset=UTF-8");
				
				// copy it to response's OutputStream
				org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
	
	@RequestMapping(value="/approveHomework", method=RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
    public @ResponseBody Map<String, Object> approveHomework(@RequestBody IdsModel<Integer> ids, Model model) {
		log.info(ids.toString());
		try {
			homeworkBaseService.approve(ids.getIds());
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
		}
    }



	/**
	 * create homework.
	 * 
	 * @param homework
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/createHomework", method=RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> createHomework(
			@RequestBody HomeworkBase homework, Model model, HttpSession session) {
		UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
		// 
		homework.setTeacherId(userSession.getUser().getUserId());
		homework.setTeacherName(userSession.getUser().getUserName());
		
        model.addAttribute("role", homework);
        
        boolean result = homeworkBaseService.save(homework);
        
        if (result) {
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
		}else{
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
		}
    }
	
	@RequestMapping(value="/deleteHomework", method=RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
    public @ResponseBody Map<String, Object> deleteHomework(@RequestBody IdsModel<Integer> ids, Model model) {
		log.info(ids.toString());
		try {
			homeworkBaseService.delete(ids.getIds());
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
		}
    }	
	
	@RequestMapping(value = "/getClassByCourse", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ClassBase> getClassByCourse(@RequestParam("courseId") int courseId) {
		List<ClassBase> list = comboboxService.getClassByCourseId(courseId);
		return list;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//// Reset Password		
	/////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/preResetPw", method = RequestMethod.GET)
	public ModelAndView preResetPw(HttpSession session) {
		UserSession userSession = (UserSession) session
				.getAttribute(USER_SESSION);
		String userId = userSession.getUser().getUserId();
		
		ModelAndView view = new ModelAndView("teacher/config/resetPw");
		
		view.addObject("id", userId);
		
		return view;
	}
	
	@Autowired
	private HomeworkBaseService homeworkBaseService;
	@Autowired
	private HomeworkArchiveService homeworkArchiveService;
	@Autowired
	private ComboboxService comboboxService;
	@Autowired
	private ISystemService systemService;

}
