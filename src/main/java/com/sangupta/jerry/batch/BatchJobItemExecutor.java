/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
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

/**
 * The executor that actually performs something useful on the job item that has
 * been provided to the job queue.
 * 
 * @author sangupta
 * 
 * @since 3.0.0
 *
 * @param <T>
 *            the {@link Class} type of the item that represents a job-order
 */
public interface BatchJobItemExecutor<T> {

	/**
	 * Process the provided job item. The item is guaranteed to be not
	 * <code>null</code>
	 * 
	 * @param job
	 *            the job item to process
	 */
	public void executeJobItem(T job);
	
	/**
	 * Specify the time in milliseconds to wait when an exception is thrown
	 * reading an item from the job queue.
	 * 
	 * @return time in milliseconds
	 */
	public long getWaitTimeOnJobReadErrorInMillis();

	/**
	 * Specify the time in milliseconds to wait when the job queue returns a
	 * <code>null</code> item. After the specified timeout the queue is again 
	 * polled to fetch an item for processing.
	 * 
	 * @return time in milliseconds
	 */
	public long getWaitTimeOnNullJobInMillis();
	
	/**
	 * Method to signal to pause execution. This method must return
	 * <code>true</code> to signal that the worker should pause execution.
	 * 
	 * @return <code>true</code> if execution should be paused,
	 *         <code>false</code> otherwise
	 */
	public boolean pauseExecution();
	
	/**
	 * Specify the time in milliseconds to wait when a pause signal has been
	 * raised before checking again if the pause mode is now revoked, and the
	 * worker can restart execution of newer job items from the queue.
	 * 
	 * @return time in milliseconds
	 */
	public long pauseCheckInterval();

	/**
	 * Method to check if the job-item has already been processed by another
	 * worker. This method exists solely when the queue returning the items may
	 * contain duplicates or where there are guarantees needed not to process a
	 * given item within certain range of time.
	 * 
	 * @param job
	 *            the job item to be tested
	 * 
	 * @return <code>true</code> if the job has already been processed,
	 *         <code>false</code> otherwise
	 */
	public boolean jobAlreadyProcessed(T job);
	
	/**
	 * This method defines if the execution of the worker should be terminated
	 * when they encounter a <code>null</code> job item. When this method
	 * returns <code>true</code> the time interval defined by method
	 * {@link #getWaitTimeOnNullJobInMillis()} has no effect. The execution of
	 * this worker stops whenever the very first <code>null</code> is
	 * encountered.
	 * 
	 * @return <code>true</code> to terminate worker on <code>null</code> item
	 *         in queue, <code>false</code> otherwise
	 */
	public boolean terminateExecutionOnNullJobItem();

}
