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

package com.sangupta.jerry.security;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.entity.UserAwarePrincipal;

/**
 * Unit tests for {@link SecurityContext}.
 *
 * @author sangupta
 *
 */
public class TestSecurityContext {

	@Test
	public void testSecurityContext() {
		Assert.assertNull(SecurityContext.getPrincipal());
		Assert.assertTrue(SecurityContext.isAnonymousUser());

		Assert.assertTrue(SecurityContext.isAnonymousUser());

		final UserAwarePrincipal user = new UserAwarePrincipal() {

            @Override public String getName() { return "anonymous"; }

            @Override public String getUserID() { return "anonymous"; }

            @Override public void setUserID(String userID) { }

        };
        
		SecurityContext.setPrincipal(user);
		Assert.assertEquals(user, SecurityContext.getPrincipal());

		Assert.assertFalse(SecurityContext.isAnonymousUser());

		SecurityContext.clear();
		Assert.assertTrue(SecurityContext.isAnonymousUser());
	}

}
