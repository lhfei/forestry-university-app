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

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 11, 2014
 */
public class MaskFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<MaskFormat> {

	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
		fieldTypes.add(String.class);
		return fieldTypes;
	}

	public Parser<?> getParser(MaskFormat annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}

	public Printer<?> getPrinter(MaskFormat annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}
	
	private static class MaskFormatter implements Formatter<String> {

		private javax.swing.text.MaskFormatter delegate;

		public MaskFormatter(String mask) {
			try {
				this.delegate = new javax.swing.text.MaskFormatter(mask);
				this.delegate.setValueContainsLiteralCharacters(false);
			} catch (ParseException e) {
				throw new IllegalStateException("Mask could not be parsed " + mask, e);
			}
		}

		public String print(String object, Locale locale) {
			try {
				return delegate.valueToString(object);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Unable to print using mask " + delegate.getMask(), e);
			}
		}

		public String parse(String text, Locale locale) throws ParseException {
			return (String) delegate.stringToValue(text);
		}

	}

}
