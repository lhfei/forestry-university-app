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

package cn.lhfei.identity.web.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

import cn.lhfei.identity.util.TDateUtils;


/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 12, 2014
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		return LocalDate.parse(source, DateTimeFormatter
				.ofPattern(TDateUtils.DateFormat.YYYY_MM_DD.getFormat()));
	}


}
