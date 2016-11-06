/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2016, Sandeep Gupta
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

package com.sangupta.jerry;

import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * Enumeration that defines the application environment that we are
 * running in.
 * 
 * @author sangupta
 *
 */
public enum ApplicationEnvironment {
	
	DEVELOPMENT(new String[] { "dev", "development" }),
	
	TEST(new String[] { "qa", "qe", "test" }),
	
	PRE_STAGE(new String[] { "prestage", "pre-stage" }),
	
	STAGE(new String[] { "stg", "stage" }),
	
	PRE_PRODUCTION(new String[] { "preprod", "pre-prod" }),
	
	PRODUCTION(new String[] { "prod", "production" });
	
	private ApplicationEnvironment(String[] alias) {
		this.alias = alias;
	}
	
	private String[] alias;
	
	/**
	 * @param environmentName
	 * @return
	 */
	public static ApplicationEnvironment fromString(String environmentName) {
		if(AssertUtils.isEmpty(environmentName)) {
			return null;
		}
		
		environmentName = environmentName.toLowerCase();
		
		if(StringUtils.contains(DEVELOPMENT.alias, environmentName)) {
			return ApplicationEnvironment.DEVELOPMENT;
		}
		
		if(StringUtils.contains(TEST.alias, environmentName)) {
			return ApplicationEnvironment.TEST;
		}
		
		if(StringUtils.contains(PRE_STAGE.alias, environmentName)) {
			return ApplicationEnvironment.PRE_STAGE;
		}
		
		if(StringUtils.contains(STAGE.alias, environmentName)) {
			return ApplicationEnvironment.STAGE;
		}
		
		if(StringUtils.contains(PRE_PRODUCTION.alias, environmentName)) {
			return ApplicationEnvironment.PRE_PRODUCTION;
		}
		
		if(StringUtils.contains(PRODUCTION.alias, environmentName)) {
			return ApplicationEnvironment.PRODUCTION;
		}
		
		throw new IllegalArgumentException("Invalid environment detected as: " + environmentName);
	}
	
}
