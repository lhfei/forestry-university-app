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
 * 论文类别</p>
 * 
 * <blockquote>
 *	<table border=0 cellspacing=3 cellpadding=0 summary="论文类别字典" >
 *		<tr bgcolor="#ccccff" align="center">
 * 			<th align=center>枚举字面量</th>
 * 			<th align=center>枚举值</th>
 * 			<th align=center>数值类型</th>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>设计</code></td>
 * 			<td><code>0</code></td>
 * 			<td><code>Intenger</code></td>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>论文</code></td>
 * 			<td><code>1</code></td>
 * 			<td><code>Intenger</code></td>
 * 		</tr>

 * 	</table>
 * </blockquote>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 24, 2014
 */

public enum ThesisCategoryEnum implements AbstractDictEnum<Integer> {

	/**
	 * 设计
	 */
	SJ(0, "\u8bbe\u8ba1"), 
	
	/**
	 * 论文
	 */
	LW(1, "\u8bba\u6587");

	ThesisCategoryEnum(int code, String label) {
		this.code = code;
		this.label = label;
	}

	private int code;
	private String label;

	@Override
	public String getLabel(int code) {
		return label;
	}

	@Override
	public Integer getCode() {
		return this.code;
	}
}
