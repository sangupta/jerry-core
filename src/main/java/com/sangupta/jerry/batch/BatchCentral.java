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

package com.sangupta.jerry.batch;

import java.util.HashSet;
import java.util.Set;

/**
 * The main class where all jobs are registered and can be seen from.
 * This allows one central place for all job activities. This also allows
 * that the callee need not pass jobs between places and can use this 
 * to fetch its jobs.
 * 
 * @author sangupta
 *
 */
public class BatchCentral {
	
	/**
	 * All known jobs
	 */
	private final static Set<BatchJob<?>> ALL_BATCH_JOBS = new HashSet<>();

	/**
	 * Add the given batch job to the central.
	 * 
	 * @param crawler
	 */
	final static void addJob(BatchJob<?> job) {
		if(job == null) {
			throw new IllegalArgumentException("Job cannot be null");
		}
		
		BatchCentral.ALL_BATCH_JOBS.add(job);
	}

	/**
	 * Remove the given batch job from the central.
	 * 
	 * @param job
	 *            the job to be removed
	 * 
	 * @throws IllegalArgumentException
	 *             if the job to be removed is <code>null</code>
	 */
	public static void removeJob(BatchJob<?> job) {
		if(job == null) {
			throw new IllegalArgumentException("Job cannot be null");
		}
		
		BatchCentral.ALL_BATCH_JOBS.remove(job);
	}
	
}
