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
import cn.lhfei.fu.common.constant.OperationTypeEnum;
import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.domain.HomeworkArchive;
import cn.lhfei.fu.orm.domain.HomeworkBase;
import cn.lhfei.fu.orm.mybatis.mapper.IStudentMapper;
import cn.lhfei.fu.orm.mybatis.mapper.ITeacherMapper;
import cn.lhfei.fu.orm.persistence.HomeworkArchiveDAO;
import cn.lhfei.fu.orm.persistence.HomeworkBaseDAO;
import cn.lhfei.fu.service.HomeworkBaseService;
import cn.lhfei.fu.service.IFilePathBuilder;
import cn.lhfei.fu.web.model.HomeworkBaseModel;
import cn.lhfei.fu.web.model.SearchAndCountModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */
@Service("homeworkBaseService")
@Transactional
public class HomeworkBaseServiceImpl implements HomeworkBaseService {

	private static final Logger log = LoggerFactory
			.getLogger(HomeworkBaseService.class);

	@Override
	public boolean save(HomeworkBase homework) {
		Date currentDate = new Date();

		homework.setActionType("" + OperationTypeEnum.XJ.getCode());
		homework.setCreateTime(currentDate);
		homework.setModifyTime(currentDate);
		homework.setOperationTime(currentDate);
//		homework.setStatus("" + ApproveStatusEnum.WTJ.getCode());

		return homeworkBaseDAO.save(homework);
	}

	@Override
	public List<HomeworkBase> findAll(HomeworkBaseModel homeworkModel) {

		return homeworkBaseDAO.search(homeworkModel.buildSearch());
	}

	@Override
	public void delete(Integer[] ids) throws Exception {
		homeworkBaseDAO.removeByIds(ids);
	}

	@Override
	public SearchResult<HomeworkBase> search(HomeworkBaseModel homeworkModel) {
		
		SearchResult<HomeworkBase> result = homeworkBaseDAO
				.searchAndCount(homeworkModel.buildSearch());

		/*List<HomeworkBase> list = result.getResult();
		for (HomeworkBase base : list) {
			
		}*/

		return result;
	}

	@Override
	public boolean approve(Integer[] ids) {

		HomeworkBase base = homeworkBaseDAO.find(ids[0]);
//		base.setStatus("" + ids[1]);

		homeworkBaseDAO.save(base);

		return true;
	}

	/**
	 * 上传 作业
	 * 
	 * @see cn.lhfei.fu.service.HomeworkBaseService#update(cn.lhfei.fu.web.model.HomeworkBaseModel)
	 */
	@Override
	public boolean update(HomeworkBaseModel model, String userType) throws NullPointerException {
		OutputStream out = null;
		Date currentTime = new Date();

		List<MultipartFile> files = model.getFiles();
		try {
			HomeworkBase base = homeworkBaseDAO.find(model.getBaseId());

			base.setModifyTime(currentTime);
			base.setActionType("" + OperationTypeEnum.SC.getCode());
			base.setOperationTime(currentTime);

			homeworkBaseDAO.save(base); // update homework_base info

			for (MultipartFile file : files) {// save archive file

				String filePath = filePathBuilder.buildFullPath(model,
						model.getStudentName());
				String fileName = filePathBuilder.buildFileName(model,
						model.getStudentName());

				String[] names = file.getOriginalFilename().split("[.]");

				String fileType = names[names.length - 1];

				String fullPath = filePath + File.separator + fileName + "."
						+ fileType;

				out = new FileOutputStream(new File(fullPath));

				BufferedOutputStream bf = new BufferedOutputStream(out);

				IOUtils.copyLarge(file.getInputStream(), bf);

				HomeworkArchive archive = new HomeworkArchive();
				archive.setArchiveName(fileName);
				archive.setArchivePath(fullPath);
				archive.setCreateTime(currentTime);
				archive.setModifyTime(currentTime);
				archive.setName(model.getName());
				archive.setStudentBaseId(model.getStudentBaseId());
				archive.setHomeworkBaseId(model.getBaseId());
				/*archive.setHomeworkBase(base);*/
				archive.setStudentName(model.getStudentName());
				archive.setStudentId(model.getStudentId());
				
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
				

				homeworkArchiveDAO.save(archive);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (NullPointerException e) {
			log.error("File name arguments missed.", e);

			throw new NullPointerException(e.getMessage());
		}

		return false;
	}
	
	@Override
	public SearchAndCountModel<HomeworkBase> getPageAndCount(HomeworkBaseModel homeworkModel) {
		// TODO Auto-generated method stub
		return homeworkBaseDAO.getPageAndCount(homeworkModel);
	}
	
	@Override
	public List<HomeworkBaseModel> getHomeworkByStudent(HomeworkBaseModel homeworkBaseModel) {
		return studentMapper.getHomeworkByStudent(homeworkBaseModel);
	}
	
	@Override
	public int countHomeworkByStudent(HomeworkBaseModel homeworkBaseModel) {
		return studentMapper.countHomeworkByStudent(homeworkBaseModel);
	}
	
	@Override
	public List<HomeworkBaseModel> getHomeworkByTeacher(
			HomeworkBaseModel homeworkBaseModel) {
		
		return teacherMapper.getHomeworkByTeacher(homeworkBaseModel);
	}

	@Override
	public int countHomeworkByTeachert(HomeworkBaseModel homeworkBaseModel) {
		return teacherMapper.countHomeworkByTeacher(homeworkBaseModel);
	}
	
	@Override
	@Transactional
	public HomeworkBase findById(Integer id) {
		return homeworkBaseDAO.find(id);
	}

	@Autowired
	private IFilePathBuilder filePathBuilder;

	@Autowired
	private HomeworkBaseDAO homeworkBaseDAO;

	@Autowired
	private HomeworkArchiveDAO homeworkArchiveDAO;
	
	@Autowired
	private IStudentMapper studentMapper;
	
	@Autowired
	private ITeacherMapper teacherMapper;


}
