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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2014
 */

public class TDateUtils {
	/**
	 * <p>
	 * 获取四位年份数
	 * </p>
	 * 
	 * @param objects
	 *            <em>默认值为系统当前年份</em>
	 * @return String
	 */
	public static String getFullYear(Date... objects) {
		if(null == objects){
			objects = new Date[]{new Date()};
		}
		Date date = null;
		date = (objects.length > 0) ? objects[0] : new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return "" + cal.get(Calendar.YEAR);
	}

	/**
	 * <p>
	 * 获取年份后两位数
	 * </p>
	 * 
	 * @param objects
	 *            <em>默认值为系统当前年份</em>
	 * @return String
	 */
	public static String getShortYear(Date... objects) {
		if(null == objects){
			objects = new Date[]{new Date()};
		}
		Date date = null;
		date = (objects.length > 0) ? objects[0] : new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		String year = "" + cal.get(Calendar.YEAR);
		return year.substring(2, 4);
	}

	/**
	 * <p>
	 * 获取月份数
	 * </p>
	 * 
	 * @param objects
	 *            <em>默认值为系统当前月份</em>
	 * @return String
	 */
	public static String getMonth(Date... objects) {
		if(null == objects){
			objects = new Date[]{new Date()};
		}
		Date date = null;
		date = (objects.length > 0) ? objects[0] : new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		String month = String.valueOf(cal.get(Calendar.MONDAY) + 1);
		month = (month.length() < 2) ? "0" + month : month;
		return month;
	}

	/**
	 * <em>获取系统当前时间</em>
	 * <p>
	 * 详细参数格式参考如下</> <blockquote>
	 * <table border=0 cellspacing=3 cellpadding=0 summary="Examples of date and time patterns interpreted in the U.S. locale">
	 * <tr bgcolor="#ccccff">
	 * <th align=center>Date and Time Pattern</th>
	 * <th align=center>Result</th>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyyy年MM月dd日 G 'at' HH:mm:ss z"</code></td>
	 * <td><code>2001年07月04日 下午 at 12:08:56 PDT</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"yyyy.MM.dd G 'at' HH:mm:ss z"</code></td>
	 * <td><code>2001.07.04 AD at 12:08:56 PDT</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"EEE, MMM d, ''yy"</code></td>
	 * <td><code>Wed, Jul 4, '01</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"h:mm a"</code></td>
	 * <td><code>12:08 PM</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"hh 'o''clock' a, zzzz"</code></td>
	 * <td><code>12 o'clock PM, Pacific Daylight Time</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"K:mm a, z"</code></td>
	 * <td><code>0:08 PM, PDT</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyyyy.MMMMM.dd GGG hh:mm aaa"</code></td>
	 * <td><code>02001.July.04 AD 12:08 PM</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code></td>
	 * <td><code>Wed, 4 Jul 2001 12:08:56 -0700</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyMMddHHmmssZ"</code></td>
	 * <td><code>010704120856-0700</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</code></td>
	 * <td><code>2001-07-04T12:08:56.235-0700</code></td>
	 * </tr>
	 * </table>
	 * </blockquote>
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(String dateFormat) {
		dateFormat = ("".equals(dateFormat) || dateFormat == null) ? DateFormat.YYYY_MM_DD_hh_mm_ss
				.getFormat() : dateFormat;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}

	/**
	 * <em>格式化时间</em>
	 * <p>
	 * 详细参数格式参考如下</> <blockquote>
	 * <table border=0 cellspacing=3 cellpadding=0 summary="Examples of date and time patterns interpreted in the U.S. locale">
	 * <tr bgcolor="#ccccff">
	 * <th align=center>Date and Time Pattern</th>
	 * <th align=center>Result</th>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyyy年MM月dd日 G 'at' HH:mm:ss z"</code></td>
	 * <td><code>2001年07月04日 下午 at 12:08:56 PDT</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"yyyy.MM.dd G 'at' HH:mm:ss z"</code></td>
	 * <td><code>2001.07.04 AD at 12:08:56 PDT</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"EEE, MMM d, ''yy"</code></td>
	 * <td><code>Wed, Jul 4, '01</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"h:mm a"</code></td>
	 * <td><code>12:08 PM</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"hh 'o''clock' a, zzzz"</code></td>
	 * <td><code>12 o'clock PM, Pacific Daylight Time</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"K:mm a, z"</code></td>
	 * <td><code>0:08 PM, PDT</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyyyy.MMMMM.dd GGG hh:mm aaa"</code></td>
	 * <td><code>02001.July.04 AD 12:08 PM</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code></td>
	 * <td><code>Wed, 4 Jul 2001 12:08:56 -0700</code></td>
	 * </tr>
	 * <tr bgcolor="#eeeeff">
	 * <td><code>"yyMMddHHmmssZ"</code></td>
	 * <td><code>010704120856-0700</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</code></td>
	 * <td><code>2001-07-04T12:08:56.235-0700</code></td>
	 * </tr>
	 * </table>
	 * </blockquote>
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String date2Str(Date date, String... dateFormat) {
		String format = DateFormat.YYYY_MM_DD.getFormat();
		if (dateFormat.length != 0) {
			format = dateFormat[0];
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * <em>返回当前日期、时间的字符串</em>
	 * <p>
	 * <code>eg. 2012-12-12 12:12:12</code>
	 * <p>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回:&nbsp<code>201112121212</br>
	 * 
	 * @return
	 */
	public static String dateTime() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = GregorianCalendar.getInstance();
		int monthNum = cal.get(Calendar.MONTH) + 1;
		String month = (monthNum > 9) ? "" + monthNum : "0" + monthNum;
		sb.append(cal.get(Calendar.YEAR));
		sb.append(month);
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(cal.get(Calendar.SECOND));

		return sb.toString();
	}

	/**
	 * <em>返回当前日期的年份、月份拼接字符串</em>
	 * <p>
	 * <code>eg. 2012-12-12</code>
	 * <p>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回:&nbsp<code>201112</br>
	 * 
	 * @return
	 */
	public static String yearMonth() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = GregorianCalendar.getInstance();
		int monthNum = cal.get(Calendar.MONTH) + 1;
		String month = (monthNum > 9) ? "" + monthNum : "0" + monthNum;
		sb.append(cal.get(Calendar.YEAR));
		sb.append(month);
		return sb.toString();
	}

	public enum DateFormat {

		YYYY_MM_DD("yyyy-MM-dd"), YYYY_MM_DD_hh_mm_ss("yyyy-MM-DD HH:mm:ss");

		private String format;

		private DateFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}
}
