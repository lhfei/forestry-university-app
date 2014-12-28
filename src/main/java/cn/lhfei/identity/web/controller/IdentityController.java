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
package cn.lhfei.identity.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.identity.orm.domain.Role;
import cn.lhfei.identity.service.RoleService;
import cn.lhfei.identity.util.JSONReturn;
import cn.lhfei.identity.web.model.IdsModel;
import cn.lhfei.identity.web.model.JsonReturnModel;
import cn.lhfei.identity.web.model.RoleModel;

import com.googlecode.genericdao.search.SearchResult;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2014
 */
@Controller
@RequestMapping("identity")
public class IdentityController {

	private Marker marker = MarkerFactory.getMarker("IDENTITY");
	private static final Logger log = LoggerFactory.getLogger(IdentityController.class);
	
	
	/////////////////////////////////////////////////////////////////////////////////
	//// 系统角色管理			
	//// Process step  	... 											
	/////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/roleRead", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonReturnModel<Role> roleRead(@RequestBody RoleModel role,HttpSession session) {
		
		JsonReturnModel<Role> json = new JsonReturnModel<Role>();
		
		json.setResult(true);
		
		//List<Role> list = roleServiceImpl.findAll(role);
		SearchResult<Role> result = roleServiceImpl.search(role);
		
		json.setRows(result.getResult());
		json.setTotal(result.getTotalCount());
		
		return json;
		
	}
	
	@RequestMapping(value="/roleList")
	public ModelAndView roleList(HttpSession session) {
		ModelAndView view = new ModelAndView("identity/role/list");
		
		return view;
		
	}
	
	@RequestMapping(value="/createRole", method=RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
    public @ResponseBody Map<String, Object> createRole(@RequestBody Role role, Model model) {
		
        model.addAttribute("role", role);
        
        boolean result = roleServiceImpl.save(role);
        
        if (result) {
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
		}else{
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
		}
    }

	@RequestMapping(value="/deleteRole", method=RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
    public @ResponseBody Map<String, Object> deleteRole(@RequestBody IdsModel<Integer> ids, Model model) {
		
		log.info(ids.toString());
		
		try {
			roleServiceImpl.delete(ids.getIds());
			return JSONReturn.mapOK("\u64cd\u4f5c\u6210\u529f!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JSONReturn.mapError("\u64cd\u4f5c\u5931\u8d25!");
		}
    }
	
	@Autowired
	private RoleService roleServiceImpl;
}
