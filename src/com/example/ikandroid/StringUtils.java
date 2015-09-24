package com.example.ikandroid;

public class StringUtils {

	public StringUtils() {
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * @deprecated Method clean is deprecated
	 */

	public static String clean(String str) {
		return str != null ? str.trim() : "";
	}

	public static String trim(String str) {
		return str != null ? str.trim() : null;
	}

	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	public static String trimToEmpty(String str) {
		return str != null ? str.trim() : "";
	}

	public static String strip(String str) {
		return strip(str, null);
	}

	public static String stripToNull(String str) {
		if (str == null) {
			return null;
		} else {
			str = strip(str, null);
			return str.length() != 0 ? str : null;
		}
	}

	public static String stripToEmpty(String str) {
		return str != null ? strip(str, null) : "";
	}

	public static String strip(String str, String stripChars) {
		if (isEmpty(str)) {
			return str;
		} else {
			str = stripStart(str, stripChars);
			return stripEnd(str, stripChars);
		}
	}

	public static String stripStart(String str, String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return str;
		int start = 0;
		if (stripChars == null) {
			for (; start != strLen && Character.isWhitespace(str.charAt(start)); start++)
				;
		} else {
			if (stripChars.length() == 0)
				return str;
			for (; start != strLen && stripChars.indexOf(str.charAt(start)) != -1; start++)
				;
		}
		return str.substring(start);
	}

	public static String stripEnd(String str, String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0)
			return str;
		if (stripChars == null) {
			for (; end != 0 && Character.isWhitespace(str.charAt(end - 1)); end--)
				;
		} else {
			if (stripChars.length() == 0)
				return str;
			for (; end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1; end--)
				;
		}
		return str.substring(0, end);
	}

	public static String[] stripAll(String strs[]) {
		return stripAll(strs, null);
	}

	public static String[] stripAll(String strs[], String stripChars) {
		int strsLen;
		if (strs == null || (strsLen = strs.length) == 0)
			return strs;
		String newArr[] = new String[strsLen];
		for (int i = 0; i < strsLen; i++)
			newArr[i] = strip(strs[i], stripChars);

		return newArr;
	}

	public static boolean equals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 == null;
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
	}

	public static int indexOf(String str, char searchChar) {
		if (isEmpty(str))
			return -1;
		else
			return str.indexOf(searchChar);
	}

	public static int indexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str))
			return -1;
		else
			return str.indexOf(searchChar, startPos);
	}

	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null)
			return -1;
		else
			return str.indexOf(searchStr);
	}

	public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
		if (str == null || searchStr == null || ordinal <= 0)
			return -1;
		if (searchStr.length() == 0)
			return 0;
		int found = 0;
		int index = -1;
		do {
			index = str.indexOf(searchStr, index + 1);
			if (index < 0)
				return index;
		} while (++found < ordinal);
		return index;
	}

	public static int indexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null)
			return -1;
		if (searchStr.length() == 0 && startPos >= str.length())
			return str.length();
		else
			return str.indexOf(searchStr, startPos);
	}

	public static int lastIndexOf(String str, char searchChar) {
		if (isEmpty(str))
			return -1;
		else
			return str.lastIndexOf(searchChar);
	}

	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str))
			return -1;
		else
			return str.lastIndexOf(searchChar, startPos);
	}

	public static int lastIndexOf(String str, String searchStr) {
		if (str == null || searchStr == null)
			return -1;
		else
			return str.lastIndexOf(searchStr);
	}

	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null)
			return -1;
		else
			return str.lastIndexOf(searchStr, startPos);
	}

	public static boolean contains(String str, char searchChar) {
		if (isEmpty(str))
			return false;
		else
			return str.indexOf(searchChar) >= 0;
	}

	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;
		else
			return str.indexOf(searchStr) >= 0;
	}

	public static boolean containsIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;
		else
			return contains(str.toUpperCase(), searchStr.toUpperCase());
	}

	public static boolean containsAny(String str, char searchChars[]) {
		if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0)
			return false;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length; j++)
				if (searchChars[j] == ch)
					return true;

		}

		return false;
	}

	public static boolean containsAny(String str, String searchChars) {
		if (searchChars == null)
			return false;
		else
			return containsAny(str, searchChars.toCharArray());
	}

	public static int indexOfAnyBut(String str, String searchChars) {
		if (isEmpty(str) || isEmpty(searchChars))
			return -1;
		for (int i = 0; i < str.length(); i++)
			if (searchChars.indexOf(str.charAt(i)) < 0)
				return i;

		return -1;
	}

	public static boolean containsNone(String str, char invalidChars[]) {
		if (str == null || invalidChars == null)
			return true;
		int strSize = str.length();
		int validSize = invalidChars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++)
				if (invalidChars[j] == ch)
					return false;

		}

		return true;
	}

	public static boolean containsNone(String str, String invalidChars) {
		if (str == null || invalidChars == null)
			return true;
		else
			return containsNone(str, invalidChars.toCharArray());
	}

	public static int indexOfAny(String str, String searchStrs[]) {
		if (str == null || searchStrs == null)
			return -1;
		int sz = searchStrs.length;
		int ret = 2147483647;
		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search == null)
				continue;
			tmp = str.indexOf(search);
			if (tmp != -1 && tmp < ret)
				ret = tmp;
		}

		return ret != 2147483647 ? ret : -1;
	}

	public static int lastIndexOfAny(String str, String searchStrs[]) {
		if (str == null || searchStrs == null)
			return -1;
		int sz = searchStrs.length;
		int ret = -1;
		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search == null)
				continue;
			tmp = str.lastIndexOf(search);
			if (tmp > ret)
				ret = tmp;
		}

		return ret;
	}

	public static String substring(String str, int start) {
		if (str == null)
			return null;
		if (start < 0)
			start = str.length() + start;
		if (start < 0)
			start = 0;
		if (start > str.length())
			return "";
		else
			return str.substring(start);
	}

	public static String substring(String str, int start, int end) {
		if (str == null)
			return null;
		if (end < 0)
			end = str.length() + end;
		if (start < 0)
			start = str.length() + start;
		if (end > str.length())
			end = str.length();
		if (start > end)
			return "";
		if (start < 0)
			start = 0;
		if (end < 0)
			end = 0;
		return str.substring(start, end);
	}

	public static String left(String str, int len) {
		if (str == null)
			return null;
		if (len < 0)
			return "";
		if (str.length() <= len)
			return str;
		else
			return str.substring(0, len);
	}

	public static String right(String str, int len) {
		if (str == null)
			return null;
		if (len < 0)
			return "";
		if (str.length() <= len)
			return str;
		else
			return str.substring(str.length() - len);
	}

	public static String mid(String str, int pos, int len) {
		if (str == null)
			return null;
		if (len < 0 || pos > str.length())
			return "";
		if (pos < 0)
			pos = 0;
		if (str.length() <= pos + len)
			return str.substring(pos);
		else
			return str.substring(pos, pos + len);
	}

	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null)
			return str;
		if (separator.length() == 0)
			return "";
		int pos = str.indexOf(separator);
		if (pos == -1)
			return str;
		else
			return str.substring(0, pos);
	}

	public static String substringAfter(String str, String separator) {
		if (isEmpty(str))
			return str;
		if (separator == null)
			return "";
		int pos = str.indexOf(separator);
		if (pos == -1)
			return "";
		else
			return str.substring(pos + separator.length());
	}

	public static String substringBeforeLast(String str, String separator) {
		if (isEmpty(str) || isEmpty(separator))
			return str;
		int pos = str.lastIndexOf(separator);
		if (pos == -1)
			return str;
		else
			return str.substring(0, pos);
	}

	public static String substringAfterLast(String str, String separator) {
		if (isEmpty(str))
			return str;
		if (isEmpty(separator))
			return "";
		int pos = str.lastIndexOf(separator);
		if (pos == -1 || pos == str.length() - separator.length())
			return "";
		else
			return str.substring(pos + separator.length());
	}

	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null)
			return null;
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1)
				return str.substring(start + open.length(), end);
		}
		return null;
	}

	/**
	 * @deprecated Method getNestedString is deprecated
	 */

	public static String getNestedString(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	/**
	 * @deprecated Method getNestedString is deprecated
	 */

	public static String getNestedString(String str, String open, String close) {
		return substringBetween(str, open, close);
	}

	/**
	 * @deprecated Method concatenate is deprecated
	 */

	public static String concatenate(Object array[]) {
		return join(array, ((String) (null)));
	}

	public static String join(Object array[]) {
		return join(array, ((String) (null)));
	}

	public static String join(Object array[], char separator) {
		if (array == null)
			return null;
		else
			return join(array, separator, 0, array.length);
	}

	public static String join(Object array[], char separator, int startIndex, int endIndex) {
		if (array == null)
			return null;
		int bufSize = endIndex - startIndex;
		if (bufSize <= 0)
			return "";
		bufSize *= (array[startIndex] != null ? array[startIndex].toString().length() : 16) + 1;
		StringBuffer buf = new StringBuffer(bufSize);
		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex)
				buf.append(separator);
			if (array[i] != null)
				buf.append(array[i]);
		}

		return buf.toString();
	}

	public static String join(Object array[], String separator) {
		if (array == null)
			return null;
		else
			return join(array, separator, 0, array.length);
	}

	public static String join(Object array[], String separator, int startIndex, int endIndex) {
		if (array == null)
			return null;
		if (separator == null)
			separator = "";
		int bufSize = endIndex - startIndex;
		if (bufSize <= 0)
			return "";
		bufSize *= (array[startIndex] != null ? array[startIndex].toString().length() : 16) + separator.length();
		StringBuffer buf = new StringBuffer(bufSize);
		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex)
				buf.append(separator);
			if (array[i] != null)
				buf.append(array[i]);
		}

		return buf.toString();
	}

}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: E:\workspace\IKUnderstanding\libs\commons-lang-2.4.jar Total
 * time: 76 ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */