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

import java.util.UUID;

import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.EnvironmentUtils;

/**
 * Application context that holds some base values for every application.
 *
 * @author sangupta
 *
 */
public class ApplicationContext {

	/**
	 * The time at which this particular node started up
	 */
	public static final long STARTUP_TIME = System.currentTimeMillis();

	/**
	 * The project version as read from the manifest file of this project - it may be empty
	 */
	public static final String PROJECT_VERSION = ApplicationContext.class.getPackage().getImplementationVersion();

	/**
	 * Node ID that is unique at the time of application startup
	 */
	public static final String NODE_ID = UUID.randomUUID().toString();
	
	/**
	 * The default application environment - can be changed from outside
	 */
	private static ApplicationEnvironment defaultApplicationEnvironment = null;
	
	public static void setDefaultApplicationEnvironment(ApplicationEnvironment environment) {
		if(environment == null) {
			throw new IllegalArgumentException("Environment cannot be null");
		}
		
		defaultApplicationEnvironment = environment;
	}
	
	private static String applicationEnvironmentProperty = null;
	
	public static void setApplicationEnvironmentProperty(String environment) {
		if(AssertUtils.isEmpty(environment)) {
			throw new IllegalArgumentException("Environment name cannot be empty/null");
		}
		
		if(ApplicationContext.applicationEnvironmentProperty != null) {
			throw new IllegalStateException("Application environment cannot be changed once it is set");
		}
		
		ApplicationContext.applicationEnvironmentProperty = environment;
	}
	
	private static ApplicationEnvironment applicationEnvironment = null;
	
	public static void setApplicationEnvironment(ApplicationEnvironment environment) {
		if(environment == null) {
			throw new IllegalArgumentException("Environment cannot be null");
		}
		
		if(ApplicationContext.applicationEnvironment != null) {
			throw new IllegalStateException("Application environment cannot be changed once it is set");
		}
		
		ApplicationContext.applicationEnvironment = environment;
	}

	/**
	 * Get the valid environment for the application using the command-line or
	 * system-wide property.
	 * 
	 * @return the {@link ApplicationEnvironment} deciphered from
	 *         System-property, java-property or set via code
	 */
	public static ApplicationEnvironment getValidEnvironment() {
		if(ApplicationContext.applicationEnvironment != null) {
			return ApplicationContext.applicationEnvironment;
		}
		
		String environmentName = EnvironmentUtils.readProperty(ApplicationContext.applicationEnvironmentProperty, null);
		if(environmentName == null) {
			return defaultApplicationEnvironment;
		}
		
		return ApplicationEnvironment.fromString(environmentName);
	}
	
}
