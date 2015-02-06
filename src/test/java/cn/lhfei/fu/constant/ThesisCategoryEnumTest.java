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

package cn.lhfei.fu.constant;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.fu.common.constant.ThesisCategoryEnum;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 25, 2014
 */

public class ThesisCategoryEnumTest {

	private static final Logger log = LoggerFactory.getLogger(ThesisCategoryEnumTest.class);
	
	@Test
	public void getLabel() {
		ThesisCategoryEnum tc = ThesisCategoryEnum.LW;
		
		log.info(tc.getLabel(ThesisCategoryEnum.LW.getCode()));;
	}
	
}
