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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.lhfei.fu.common.constant.ApproveStatusEnum;
import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.domain.ThesisArchive;
import cn.lhfei.fu.orm.domain.ThesisBase;
import cn.lhfei.fu.orm.mybatis.mapper.IExcelFactoryMapper;
import cn.lhfei.fu.orm.mybatis.mapper.IStudentMapper;
import cn.lhfei.fu.orm.mybatis.mapper.ITeacherMapper;
import cn.lhfei.fu.orm.persistence.TeacherBaseDAO;
import cn.lhfei.fu.orm.persistence.ThesisArchiveDAO;
import cn.lhfei.fu.orm.persistence.ThesisBaseDAO;
import cn.lhfei.fu.service.IFilePathBuilder;
import cn.lhfei.fu.service.ThesisBaseService;
import cn.lhfei.fu.web.model.TeacherBaseModel;
import cn.lhfei.fu.web.model.ThesisBaseModel;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */
@Service("thesisBaseService")
@Transactional
public class ThesisBaseServiceImpl implements ThesisBaseService {
	
	private static final Logger log = LoggerFactory.getLogger(ThesisBaseServiceImpl.class);
	
	@Override
	public boolean importThesisBasek(String filePath) throws Exception {
		return false;
	}

	@Override
	public List<ThesisBaseModel> getThesis(ThesisBaseModel model) throws Exception {
		
		return teacherMapper.getThesis(model);
	}
	
	@Override
	public Integer countThesis(ThesisBaseModel model) throws Exception {
		return teacherMapper.countThesis(model);
	}
	
	@Override
	public boolean save(ThesisBase thesis) throws Exception {
		return thesisBaseDAO.save(thesis);
	}
	
	@Override
	public boolean update(ThesisBaseModel model, String userType) throws Exception {
		OutputStream out = null;
		BufferedOutputStream bf = null;
		Date currentTime = new Date();
		
		boolean result = false;
		List<MultipartFile> files = model.getFiles();
		
		try {

			boolean modelIsValid = this.updateThesis(model);
			
			if(!modelIsValid){
				return result;
			}
			
			int num = 1;
			for (MultipartFile file : files) {// save archive file
				
				if(file.getSize() > 0){
					String filePath = filePathBuilder.buildThesisFullPath(model,
							model.getStudentName());
					String fileName = filePathBuilder.buildThesisFileName(model,
							model.getStudentName(), num);
					
					String[] names = file.getOriginalFilename().split("[.]");
					
					String fileType = names[names.length - 1];
					
					String fullPath = filePath + File.separator + fileName + "."
							+ fileType;
					
					out = new FileOutputStream(new File(fullPath));
					
					bf = new BufferedOutputStream(out);
					
					IOUtils.copyLarge(file.getInputStream(), bf);
					
					ThesisArchive archive = new ThesisArchive();
					archive.setThesisBaseId(model.getBaseId());
					archive.setStudentId(model.getStudentId());
					archive.setArchiveName(fileName);
					archive.setArchivePath(fullPath);
					archive.setCreateTime(currentTime);
					archive.setModifyTime(currentTime);
					archive.setStudentBaseId(model.getStudentBaseId());
					archive.setThesisTitle(model.getThesisTitle());
					archive.setExtend(model.getThesisEnTitle());	// 英文标题
					archive.setExtend1(model.getThesisType());		// 论文类型
					
					// 更新作业状态
					if(userType != null && userType.equals(UserTypeEnum.STUDENT.getCode())){
						archive.setStatus("" +ApproveStatusEnum.DSH.getCode());	
					}
					else if(userType != null && userType.equals(UserTypeEnum.TEACHER.getCode())){
						archive.setStatus("" +ApproveStatusEnum.DSH.getCode());	
					}
					else if(userType != null && userType.equals(UserTypeEnum.ADMIN.getCode())){
						archive.setStatus("" +ApproveStatusEnum.YSH.getCode());	
					}
					
					thesisArchiveDAO.save(archive);
					
					// auto increment archives number.
					num ++;
				}
			}
			
			result = true;
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new IOException(e.getMessage(), e);
			
		} catch (NullPointerException e) {
			log.error("File name arguments missed.", e);

			throw new NullPointerException(e.getMessage());
		} finally {
			if(out != null){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if(bf != null){
				try {
					bf.flush();
					bf.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}		

		return result;
	}
	
	@Override
	public boolean updateThesis(ThesisBaseModel model) throws IllegalArgumentException {
		boolean result = false;
		String teacherId = model.getTeacherId();
		
		if(model.getBaseId() == null || teacherId == null || teacherId.trim().length() < 0){
			
			throw  new IllegalArgumentException("Arguments is invalid. ThesisBase ID or Theacher ID is null.");
			
		}else {
			TeacherBaseModel teacherModel = teacherMapper.findTeacherByTeacherId(teacherId);
			
			model.setTeacherName(teacherModel.getTeacherName());
			model.setTeacherTitle(teacherModel.getTeacherTitle());
			
			try {
				teacherMapper.updateThesis(model);
				
				result = true;
			} catch (Exception e) {
				result = false;
				throw new IllegalArgumentException(e.getMessage(), e);
			}
			
			return result;
		}
		
	}
	
	@Autowired
	private IFilePathBuilder filePathBuilder;
	
	@Autowired
	private IStudentMapper studentMapper;
	
	@Autowired
	private ITeacherMapper teacherMapper;
	
	@Autowired
	private IExcelFactoryMapper excelFactoryMapper;
	
	@Autowired
	private ThesisBaseDAO thesisBaseDAO;
	
	@Autowired
	private ThesisArchiveDAO thesisArchiveDAO;
	
	@Autowired
	private TeacherBaseDAO teacherBaseDAO;

}
