/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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


package com.sangupta.jerry.ds.refresh;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.util.DateUtils;

/**
 * Unit tests for {@link AutoRefreshableFloat} class
 *
 * @author sangupta
 *
 */
public class TestAutoRefreshableFloat {

	@Test
	public void test() {
		AutoRefreshableFloat i = new AutoRefreshableFloat(DateUtils.ONE_SECOND) {

			private float value = 20f;

			@Override
			public float refresh() {
				this.value = this.value * 2f;
				return this.value;
			}

		};

		// start testing
		float value = i.get();
		Assert.assertTrue(value > 0);
		Assert.assertEquals(value, i.get(), 0f);

		try {
			Thread.sleep(DateUtils.ONE_SECOND * 2l);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}

		Assert.assertTrue(i.get() > 0);
		Assert.assertFalse(value == i.get());

		// test exception
		try {
			i = new AutoRefreshableFloat(0l) {

				@Override
				public float refresh() {
					return 0;
				}
			};

			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			i = new AutoRefreshableFloat(-15l) {

				@Override
				public float refresh() {
					return 0;
				}
			};

			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}
