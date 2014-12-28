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
 * @since Dec 6, 2014
 */

public enum ApproveStatusEnum implements AbstractDictEnum<Integer> {

	/*作业状态，枚举值包括：  [  未提交（0）  待审核（1）  已审核（2）  未通过（3）  ]*/
	
	/**
	 * 未提交
	 */
	WTJ(0, "\u672a\u63d0\u4ea4"), 
	
	/**
	 * 待审核
	 */
	DSH(1, "\u5f85\u5ba1\u6838"),
	
	/**
	 * 已审核
	 */
	YSH(2, "\u5df2\u5ba1\u6838"),
	
	/**
	 * 未通过
	 */
	WTG(3, "\u672a\u901a\u8fc7");
	
	@Override
	public String getLabel(int code) {
		return label;
	}

	@Override
	public Integer getCode() {
		return code;
	}
	
	
	ApproveStatusEnum(int code, String label) {
		this.code = code;
		this.label = label;
	}

	private int code;
	private String label;

}
