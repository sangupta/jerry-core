package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

public class TestReadableUtils {
	
	@Test
	public void testParseFileSize() {
		Assert.assertEquals(0l, ReadableUtils.parseByteCount(""));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0k"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0kb"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0m"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0mb"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0g"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0gb"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0t"));
		Assert.assertEquals(0l, ReadableUtils.parseByteCount("0tb"));
		
		// some values
		Assert.assertEquals(123l, ReadableUtils.parseByteCount("123"));
		Assert.assertEquals(123l, ReadableUtils.parseByteCount("123b"));
		Assert.assertEquals(123l, ReadableUtils.parseByteCount("123 b"));
		
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseByteCount("1k"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseByteCount("1kb"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseByteCount("1 k"));
		Assert.assertEquals(FileUtils.ONE_KB, ReadableUtils.parseByteCount("1 kb"));

		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseByteCount("1m"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseByteCount("1mb"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseByteCount("1 m"));
		Assert.assertEquals(FileUtils.ONE_MB, ReadableUtils.parseByteCount("1 mb"));

		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseByteCount("1g"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseByteCount("1gb"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseByteCount("1 g"));
		Assert.assertEquals(FileUtils.ONE_GB, ReadableUtils.parseByteCount("1 gb"));

		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseByteCount("1t"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseByteCount("1tb"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseByteCount("1 t"));
		Assert.assertEquals(FileUtils.ONE_TB, ReadableUtils.parseByteCount("1 tb"));
		
		// decimals
		Assert.assertEquals(13l * FileUtils.ONE_MB, ReadableUtils.parseByteCount("13m"));
		Assert.assertEquals(1200l * FileUtils.ONE_MB, ReadableUtils.parseByteCount("1200m"));
	}

	@Test
	public void testGetReadableByteCount() {
		Assert.assertEquals("123 B", ReadableUtils.getReadableByteCount(123l));
		Assert.assertEquals("1 KB", ReadableUtils.getReadableByteCount(FileUtils.ONE_KB));
		
		Assert.assertEquals("1.5 KB", ReadableUtils.getReadableByteCount((long) (1.5 * FileUtils.ONE_KB)));
		Assert.assertEquals("2 KB", ReadableUtils.getReadableByteCount(2 * FileUtils.ONE_KB));
		
		Assert.assertEquals("1 MB", ReadableUtils.getReadableByteCount(FileUtils.ONE_MB));
		Assert.assertEquals("2.5 MB", ReadableUtils.getReadableByteCount((long) (2.5 * FileUtils.ONE_MB)));
		
		Assert.assertEquals("1 GB", ReadableUtils.getReadableByteCount(FileUtils.ONE_GB));
		Assert.assertEquals("3.3 GB", ReadableUtils.getReadableByteCount((long) (3.3 * FileUtils.ONE_GB)));

		Assert.assertEquals("1 TB", ReadableUtils.getReadableByteCount(FileUtils.ONE_TB));
		Assert.assertEquals("4.2 TB", ReadableUtils.getReadableByteCount((long) (4.2 * FileUtils.ONE_TB)));

		Assert.assertEquals("1 PB", ReadableUtils.getReadableByteCount(FileUtils.ONE_TB * FileUtils.ONE_KB));
		Assert.assertEquals("3.3 PB", ReadableUtils.getReadableByteCount((long) (3.3 * FileUtils.ONE_TB * FileUtils.ONE_KB)));

		Assert.assertEquals("1 EB", ReadableUtils.getReadableByteCount(FileUtils.ONE_TB * FileUtils.ONE_MB));
		Assert.assertEquals("3.3 EB", ReadableUtils.getReadableByteCount((long) (3.3 * FileUtils.ONE_TB * FileUtils.ONE_MB)));
	}
	
	@Test
	public void testGetReadableTimeDuration() {
		Assert.assertEquals("1 milli", ReadableUtils.getReadableTimeDuration(1l));
		Assert.assertEquals("123 millis", ReadableUtils.getReadableTimeDuration(123l));
		Assert.assertEquals("1 second", ReadableUtils.getReadableTimeDuration(1000l));
		Assert.assertEquals("2 seconds", ReadableUtils.getReadableTimeDuration(2000l));
		Assert.assertEquals("2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(2100l));
		Assert.assertEquals("1 minute", ReadableUtils.getReadableTimeDuration(60000l));
		Assert.assertEquals("1 minute 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(62100l));
		Assert.assertEquals("2 minutes 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(122100l));
		Assert.assertEquals("1 hour", ReadableUtils.getReadableTimeDuration(3600000l));
		Assert.assertEquals("1 hour 2 minutes 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(3722100l));
		Assert.assertEquals("2 hours 2 minutes 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(7322100l));
		Assert.assertEquals("1 day", ReadableUtils.getReadableTimeDuration(86400000l));
		Assert.assertEquals("1 day 2 hours 2 minutes 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(93722100l));
		Assert.assertEquals("2 days 2 hours 2 minutes 2 seconds 100 millis", ReadableUtils.getReadableTimeDuration(180122100l));
	}
}
