package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

public class TestReadableUtils {
	
	@Test
	public void testParseFileSize() {
		Assert.assertEquals(0l, ReadableUtils.parseFileSize(""));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0k"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0kb"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0m"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0mb"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0g"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0gb"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0t"));
		Assert.assertEquals(0l, ReadableUtils.parseFileSize("0tb"));
		
		// some values
		Assert.assertEquals(123l, ReadableUtils.parseFileSize("123"));
		Assert.assertEquals(123l, ReadableUtils.parseFileSize("123b"));
		Assert.assertEquals(123l, ReadableUtils.parseFileSize("123 b"));
		
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseFileSize("1k"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseFileSize("1kb"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseFileSize("1 k"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseFileSize("1 kb"));

		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseFileSize("1m"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseFileSize("1mb"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseFileSize("1 m"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseFileSize("1 mb"));

		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseFileSize("1g"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseFileSize("1gb"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseFileSize("1 g"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseFileSize("1 gb"));

		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseFileSize("1t"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseFileSize("1tb"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseFileSize("1 t"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseFileSize("1 tb"));
		
		// decimals
		Assert.assertEquals(13l * FileUtils.ONE_MB, ReadableUtils.parseFileSize("13m"));
		Assert.assertEquals(1200l * FileUtils.ONE_MB, ReadableUtils.parseFileSize("1200m"));
	}

}
