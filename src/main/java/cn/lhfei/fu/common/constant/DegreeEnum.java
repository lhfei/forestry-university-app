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
 * @since Apr 18, 2015
 */

public enum DegreeEnum implements AbstractDictEnum<Integer> {
	
	/**
	 * \u672c\u79d1
	 */
	BK("\u672c\u79d1", 0),
	
	/**
	 * \u7855\u58eb
	 */
	SS("\u7855\u58eb", 1),
	
	/**
	 * \u535a\u58eb
	 */
	BS("\u535a\u58eb", 2);
	
	DegreeEnum(String label, Integer code){
		this.label = label;
		this.code  = code;
	}

	@Override
	public String getLabel(Integer code) {
		return label;
	}

	@Override
	public Integer getCode() {
		return code;
	}
	
	private String label;
	private Integer code;

}
