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

public enum OperationTypeEnum implements AbstractDictEnum<Integer> {
	
	/*操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改|新建】*/
	
	/**
	 * 上传
	 */
	SC(0, "\u4e0a\u4f20"), 
	
	/**
	 * 下载
	 */
	XZ(1, "\u4e0b\u8f7d"),
	
	/**
	 * 批量上传
	 */
	PLSC(2, "\u6279\u91cf\u4e0a\u4f20"),
	
	/**
	 * 批量下载
	 */
	PLXZ(3, "\u6279\u91cf\u4e0b\u8f7d"),
	
	/**
	 * 审核
	 */
	SH(4, "\u5ba1\u6838"),
	
	/**
	 * 修改
	 */
	XG(5, "\u4fee\u6539"),
	
	/**
	 * 新建
	 */
	XJ(5, "\u65b0\u5efa");
	
	/**
	 * 数据库<tt>KEY</tt>值
	 */
	public static final String ENUM_KEY = "CZLX";
	
	@Override
	public String getLabel(Integer code) {
		return label;
	}

	@Override
	public Integer getCode() {
		return code;
	}
	
	
	OperationTypeEnum(int code, String label) {
		this.code = code;
		this.label = label;
	}

	private int code;
	private String label;

}
