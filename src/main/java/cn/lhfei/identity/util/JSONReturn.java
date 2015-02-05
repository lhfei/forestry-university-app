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
package cn.lhfei.identity.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2014
 */

public class JSONReturn {
	private static final String DEFAULT_MESSAGE_NODE = "message";
	private static final String DEFAULT_SUCCESS_NODE = "success";
	private static final String DEFAULT_TOTAL_NODE = "total";
	private static final String DEFAULT_DATA_NODE = "data";

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param beans
	 * @return
	 */
	public static Map<String, Object> mapOK(List<? extends Serializable> beans) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put(DEFAULT_TOTAL_NODE, beans.size());
		modelMap.put(DEFAULT_DATA_NODE, beans);
		modelMap.put(DEFAULT_SUCCESS_NODE, true);

		return modelMap;
	}

	public static Map<String, Object> mapOK(List<? extends Serializable> beans,
			String nodeName) {
		nodeName = (nodeName != null && nodeName.trim().length() > 0) ? nodeName
				: DEFAULT_DATA_NODE;

		Map<String, Object> modelMap = new HashMap<String, Object>(1);
		modelMap.put(nodeName, beans);
		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param benas
	 * @return
	 */
	public static Map<String, Object> mapOK(List<? extends Serializable> benas,
			int total) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put(DEFAULT_TOTAL_NODE, total);
		modelMap.put(DEFAULT_DATA_NODE, benas);
		modelMap.put(DEFAULT_SUCCESS_NODE, true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param beans
	 * @param total
	 * @param message
	 * @return
	 */
	public static Map<String, Object> mapOK(List<? extends Serializable> beans,
			int total, String message) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put(DEFAULT_TOTAL_NODE, total);
		modelMap.put(DEFAULT_DATA_NODE, beans);
		modelMap.put(DEFAULT_MESSAGE_NODE, message);
		modelMap.put(DEFAULT_SUCCESS_NODE, true);

		return modelMap;
	}
	
	public static Map<String, Object> map(boolean result, Object bean, String message) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put(DEFAULT_SUCCESS_NODE, result);
		modelMap.put(DEFAULT_DATA_NODE, bean);
		modelMap.put(DEFAULT_MESSAGE_NODE, message);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView in case of success
	 * 
	 * @param msg
	 *            operation successful message
	 * @return
	 */
	public static Map<String, Object> mapOK(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put(DEFAULT_MESSAGE_NODE, msg);
		modelMap.put(DEFAULT_SUCCESS_NODE, true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView in case of exception
	 * 
	 * @param msg
	 *            exception message
	 * @return
	 */
	public static Map<String, Object> mapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put(DEFAULT_MESSAGE_NODE, msg);
		modelMap.put(DEFAULT_SUCCESS_NODE, false);

		return modelMap;
	}

	/**
	 * warper the customer map message.
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> mapWarp(Map<String, Object> map) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		Set<String> keys = map.keySet();

		Iterator<String> iter = keys.iterator();

		while (iter.hasNext()) {
			String key = iter.next();
			modelMap.put(key, map.get(key));
		}

		return modelMap;
	}
	

	/**
	 * 判断传入的条件是否为空
	 * 
	 * @param sta
	 * @return
	 */
	public static String checkNotEmpty(String sta) {
		if (null == sta || sta.equals("")) {
			return null;
		}
		return sta;

	}
}
