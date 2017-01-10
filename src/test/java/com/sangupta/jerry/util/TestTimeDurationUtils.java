/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link TimeDurationUtils}.
 *
 * @author sangupta
 *
 */
public class TestTimeDurationUtils {

	@Test
	public void testAgo() {
		long time = System.currentTimeMillis();
		
		Assert.assertEquals("less than a minute ago", TimeDurationUtils.ago(time));
		
		time -= DateUtils.ONE_MINUTE;
		Assert.assertEquals("about a minute ago", TimeDurationUtils.ago(time));
		
		time -= (3 * DateUtils.ONE_MINUTE);
		Assert.assertEquals("about 4 minutes ago", TimeDurationUtils.ago(time));
		
		time -= (56 * DateUtils.ONE_MINUTE);
		Assert.assertEquals("about an hour ago", TimeDurationUtils.ago(time));
		
		time -= DateUtils.ONE_HOUR;
		Assert.assertEquals("about 2 hours ago", TimeDurationUtils.ago(time));
		
		time -= (22 * DateUtils.ONE_HOUR);
		Assert.assertEquals("about a day ago", TimeDurationUtils.ago(time));
		
		time -= (2 * DateUtils.ONE_DAY);
		Assert.assertEquals("about 3 days ago", TimeDurationUtils.ago(time));
		
		time -= (27 * DateUtils.ONE_DAY);
		Assert.assertEquals("about a month ago", TimeDurationUtils.ago(time));
		
		time -= (2 * DateUtils.ONE_MONTH);
		Assert.assertEquals("about 3 months ago", TimeDurationUtils.ago(time));
		
		time -= (10 * DateUtils.ONE_MONTH);
		Assert.assertEquals("about an year ago", TimeDurationUtils.ago(time));
		
		time -= (2 * DateUtils.ONE_YEAR);
		Assert.assertEquals("about 3 years ago", TimeDurationUtils.ago(time));
	}
	
	@Test
	public void testAgoCondensed() {
		long time = System.currentTimeMillis();
		
		Assert.assertEquals("now", TimeDurationUtils.ago(time, true));
		
		time -= DateUtils.ONE_MINUTE;
		Assert.assertEquals("1min", TimeDurationUtils.ago(time, true));
		
		time -= (3 * DateUtils.ONE_MINUTE);
		Assert.assertEquals("4min", TimeDurationUtils.ago(time, true));
		
		time -= (56 * DateUtils.ONE_MINUTE);
		Assert.assertEquals("1h", TimeDurationUtils.ago(time, true));
		
		time -= DateUtils.ONE_HOUR;
		Assert.assertEquals("2h", TimeDurationUtils.ago(time, true));
		
		time -= (22 * DateUtils.ONE_HOUR);
		Assert.assertEquals("1d", TimeDurationUtils.ago(time, true));
		
		time -= (2 * DateUtils.ONE_DAY);
		Assert.assertEquals("3d", TimeDurationUtils.ago(time, true));
		
		time -= (27 * DateUtils.ONE_DAY);
		Assert.assertEquals("1mo", TimeDurationUtils.ago(time, true));
		
		time -= (2 * DateUtils.ONE_MONTH);
		Assert.assertEquals("3mo", TimeDurationUtils.ago(time, true));
		
		time -= (10 * DateUtils.ONE_MONTH);
		Assert.assertEquals("1y", TimeDurationUtils.ago(time, true));
		
		time -= (2 * DateUtils.ONE_YEAR);
		Assert.assertEquals("3y", TimeDurationUtils.ago(time, true));
	}

}
