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

}