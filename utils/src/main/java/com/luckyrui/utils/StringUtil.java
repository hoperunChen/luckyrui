package com.luckyrui.utils;

public class StringUtil {

	/**
	 * 判断str是否为空,如果为空使用nullStr
	 * 
	 * @param str
	 *            验证的str
	 * @param nullStr
	 *            替换的str
	 * @return
	 * @author chenrui
	 * @date 2016年9月3日 下午4:59:37
	 * @version 2016_Anniversary
	 */
	public static String ifnull(String str, String nullStr) {
		if (null == str || str.trim().isEmpty()) {
			return nullStr;
		}
		return str;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtil.isEmpty(cs);
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtil.isBlank(cs);
	}
}
