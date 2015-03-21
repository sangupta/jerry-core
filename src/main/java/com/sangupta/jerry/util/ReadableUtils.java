package com.sangupta.jerry.util;

import java.util.Arrays;

public class ReadableUtils {
	
	public static long parseFileSize(String size) {
		if(AssertUtils.isEmpty(size)) {
			return 0;
		}
		
		char[] chars = size.toCharArray();
		boolean isNumber = true;
		int i = 0;
		for(i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(!('0' <= c && c <= '9')) {
				isNumber = false;
				break;
			}
		}
		
		if(isNumber) {
			return StringUtils.getLongValue(size, 0);
		}
		
		// not a number
		String num = new String(Arrays.copyOfRange(chars, 0, i));
		String id = new String(Arrays.copyOfRange(chars, i, chars.length));
		long value = StringUtils.getLongValue(num, 0);
		
		// check id
		id = id.trim();
		if("b".equalsIgnoreCase(id)) {
			return value;
		}

		if("k".equalsIgnoreCase(id) || "kb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_KB;
		}

		if("m".equalsIgnoreCase(id) || "mb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_MB;
		}
		
		if("g".equalsIgnoreCase(id) || "gb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_GB;
		}

		if("t".equalsIgnoreCase(id) || "tb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_TB;
		}
		
		throw new IllegalArgumentException("Unknown size identifier: " + id);
	}

}
