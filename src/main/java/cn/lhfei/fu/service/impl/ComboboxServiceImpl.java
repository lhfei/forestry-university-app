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
package cn.lhfei.fu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.orm.domain.ApproveStatus;
import cn.lhfei.fu.orm.domain.ClassBase;
import cn.lhfei.fu.orm.domain.Combobox;
import cn.lhfei.fu.orm.domain.CourseBase;
import cn.lhfei.fu.orm.domain.TeachingPeriods;
import cn.lhfei.fu.orm.domain.ThesisOrigin;
import cn.lhfei.fu.orm.mybatis.mapper.IComboboxMapper;
import cn.lhfei.fu.orm.persistence.ApproveStatusDAO;
import cn.lhfei.fu.orm.persistence.ClassBaseDAO;
import cn.lhfei.fu.orm.persistence.ComboboxDAO;
import cn.lhfei.fu.orm.persistence.CourseBaseDAO;
import cn.lhfei.fu.orm.persistence.ThesisOriginDAO;
import cn.lhfei.fu.service.ComboboxService;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 13, 2014
 */
@Service("comboboxService")
@Transactional
public class ComboboxServiceImpl implements ComboboxService {

	private static final Logger log = LoggerFactory
			.getLogger(ComboboxServiceImpl.class);

	@Override
	@Transactional
    @Cacheable
	public List<ApproveStatus> getApproveStatus() {
		Search search = new Search();
		
		search.addSortAsc("code");

		List<ApproveStatus> list = approveStatusDAO.search(search);

		return list;
	}

	@Override
	public List<ThesisOrigin> getThesisOrigin() {
		List<ThesisOrigin> list = thesisOriginDAO.findAll();

		return list;
	}

	@Override
	public boolean deleteApproveStatus(Integer[] ids) {

		try {
			approveStatusDAO.removeByIds(ids);
			
			return true;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

	}

	@Override
	public boolean deleteThesisOrigin(Integer[] ids) {

		try {
			thesisOriginDAO.removeByIds(ids);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

	}
	
	@Override
	public List<Combobox> getCombobo(ISearch search) {
		
		return comboboxDAO.search(search);
	}
	
	@Override
	public List<Combobox> getCombobo(String key) {
		Search search = new Search();
		search.addFilterEqual("key", key);
		search.addSortDesc("code");
		
		return getCombobo(search);
	}
	

	@Override
	public void createCombobox(Combobox combobox) {
		comboboxDAO.save(combobox);
	}
	
	@Override
	public List<CourseBase> getCourse() {
		Search search = new Search();
		search.addSortAsc("courseName");
		List<CourseBase> list = courseBaseDAO.search(search);
		
		return list;
	}

	@Override
	public List<ClassBase> getClassByCourseId(int courseId) {
		Search search = new Search();
		search.addSortAsc("className");
		search.addFilterEqual("courseId", courseId);
		
		List<ClassBase> list = classBaseDAO.search(search);
		
		return list;
	}	
	
	@Override
	public List<Combobox> getClassByTeacher(String teacherId, TeachingPeriods teachingPeriod) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("teacherId", teacherId);
		model.put("academicYear", teachingPeriod.getAcademicYear());
		model.put("semester", teachingPeriod.getSemester());
		
		return comboboxMapper.getClassByTeacher(model);
	}

	@Override
	public List<Combobox> getAllTeacher() {
		return comboboxMapper.getAllTeacher();
	}

	@Override
	public void updateTeacherIdByName(Map<String, Object> map) {
		comboboxMapper.updateTeacherIdByName(map);
	}
	
	@Override
	public void batchUpdateTeacherIdByName() {
		List<Combobox> teachers = this.getAllTeacher();
		for(Combobox teacher : teachers){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teacherId", teacher.getCode());
			map.put("teacherName", teacher.getLabel());
			
			this.updateTeacherIdByName(map);
		}
	}
	
	@Autowired
	private ApproveStatusDAO approveStatusDAO;

	@Autowired
	private ThesisOriginDAO thesisOriginDAO;
	
	@Autowired
	private ComboboxDAO comboboxDAO;
	
	@Autowired
	private CourseBaseDAO courseBaseDAO;
	
	@Autowired
	private ClassBaseDAO classBaseDAO;
	
	@Autowired
	private IComboboxMapper comboboxMapper;

}
