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

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lhfei.fu.common.constant.ApproveStatusEnum;
import cn.lhfei.fu.common.constant.UserTypeEnum;
import cn.lhfei.fu.orm.domain.HomeworkArchive;
import cn.lhfei.fu.orm.mybatis.mapper.ITeacherMapper;
import cn.lhfei.fu.orm.persistence.HomeworkArchiveDAO;
import cn.lhfei.fu.service.HomeworkArchiveService;


/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Dec 2, 2014
 */
@Service("homeworkArchiveService")
@Transactional
public class HomeworkArchiveServiceImpl implements HomeworkArchiveService{
	
	@Override
	public HomeworkArchive read(Integer id) {
		
		return homeworkArchiveDAO.find(id);
	}
	
	@Override
	public String updateArachive(Integer id, String status, String desc, String userType) throws Exception {
		String message = "0";
		// 更新作业状态
		if(userType != null && userType.equals(UserTypeEnum.STUDENT.getCode())){
			message = "\u5f53\u524d\u64cd\u4f5c\u4e3a\u6388\u6743,"
					+ "\u8bf7\u4e0e\u7ba1\u7406\u5458\u8054\u7cfb!";
			
			return message;
		}
		/*else if(userType != null && userType.equals(UserTypeEnum.TEACHER.getCode())){
		}
		else if(userType != null && userType.equals(UserTypeEnum.ADMIN.getCode())){
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("desc", desc);
		
		if("1".equals(status)){// 1 审批通过 | 0 审批未通过
			status = ApproveStatusEnum.YSH.getCode().toString();
		}else{
			status = ApproveStatusEnum.WTG.getCode().toString();
		}
		
		map.put("status", status);
		
		teacherMapper.updateArachive(map);
		
		return message;
	}
	
	
	@Autowired
	private  HomeworkArchiveDAO  homeworkArchiveDAO;
	
	@Autowired
	private ITeacherMapper teacherMapper;
}
