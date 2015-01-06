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
package cn.lhfei.identity.common.util;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 5, 2015
 */
public class ByteToHex {
	public static String bytesToHex(byte[] b) {

		char hexDigit[] = {'@','%', '#','0', '$','1', '2','3','5','8', '9',
				'l','n','m','E', 'f','G','H','x','V','W','y','Z',';','0','a','b','i','c','d','j','K','O' ,'R','s','t','u'};
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
}