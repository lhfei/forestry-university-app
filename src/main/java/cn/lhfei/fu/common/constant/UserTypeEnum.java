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
package cn.lhfei.fu.common.constant;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 7, 2015
 */

public enum UserTypeEnum implements AbstractDictEnum<String> {
	
	ADMIN("0", "\u7ba1\u7406\u5458"),
	
	TEACHER("1", "\u6559\u5e08"),
	
	STUDENT("2", "\u5b66\u751f");
	
	/**
	 * 数据库<tt>KEY</tt>值
	 */
	public static final String ENUM_KEY = "YHLX";
	
	UserTypeEnum(String userType, String label) {
		this.userType = userType;
	}
	
	@Override
	public String getLabel(String code) {
		
		return label;
	}
	
	@Override
	public String getCode() {
		return userType;
	}
	
	private String userType;
	private String label;
}
