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
 * 论文来源</p>
 * 
 * <blockquote>
 *	<table border=0 cellspacing=3 cellpadding=0 summary="论文类别字典" >
 *		<tr bgcolor="#ccccff" align="center">
 * 			<th align=center>枚举字面量</th>
 * 			<th align=center>枚举值</th>
 * 			<th align=center>数值类型</th>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>科研</code></td>
 * 			<td><code>1</code></td>
 * 			<td><code>String</code></td>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>生产</code></td>
 * 			<td><code>2</code></td>
 * 			<td><code>String</code></td>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>模拟</code></td>
 * 			<td><code>3</code></td>
 * 			<td><code>String</code></td>
 * 		</tr>
 * 		<tr bgcolor="#eeeeff" align="center">
 * 			<td><code>其它</code></td>
 * 			<td><code>0</code></td>
 * 			<td><code>String</code></td>
 * 		</tr>

 * 	</table>
 * </blockquote>
 * 
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Apr 16, 2015
 */

public enum ThesisOriginEnum implements AbstractDictEnum<String> {
	/**
	 * 科研（1）生产（2） 模拟（3）其它（0）
	 */
	KY("1", "\u79d1\u7814"),
	
	/**
	 * 科研（1）生产（2） 模拟（3）其它（0）
	 */
	SC("2", "\u751f\u4ea7"),
	
	/**
	 * 科研（1）生产（2） 模拟（3）其它（0）
	 */
	MN("3", "\u6a21\u62df"),
	
	/**
	 * 科研（1）生产（2） 模拟（3）其它（0）
	 */
	QT("0", "\u5176\u5b83");
	
	ThesisOriginEnum(String code, String label) {
		this.code = code;
		this.label = label;
	}

	/**
	 * 数据库<tt>KEY</tt>值
	 */
	public static final String ENUM_KEY = "LWLY";
	
	private String code;
	private String label;
	
	@Override
	public String getLabel(String code) {
		return this.label;
	}

	@Override
	public String getCode() {
		return this.code;
	}

}
