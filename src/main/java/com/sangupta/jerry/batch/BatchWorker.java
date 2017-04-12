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

package com.sangupta.jerry.batch;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.sangupta.jerry.util.AssertUtils;

/**
 * The worker thread that works over a single batch job. Multiple worker threads
 * race to utilize and work over the amount of work backed by a queue.
 * 
 * @author sangupta
 * 
 * @since 3.0.0
 *
 * @param <T>
 *            the {@link Class} type of the job item that this worker will
 *            handle
 */
public class BatchWorker<T> extends AbstractExecutionThreadService  {
	
	/**
	 * My logger instance
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

	/**
	 * The thread name assigned to this worker
	 */
	private final String threadName;
	
	/**
	 * The name of the job that this worker is working on
	 */
	private final String jobName;
	
	/**
	 * If set to <code>true</code> indicates that the execution for this worker
	 * has been paused.
	 */
	private volatile boolean workerPaused = false;
	
	/**
	 * If set to <code>true</code> indicates that the execution for this worker
	 * has been requested to be stopped immediately.
	 */
	private volatile boolean stopNow = false;
	
	/**
	 * The item reader to be used to fetch one new job item from the batch
	 */
	private final Callable<T> itemReader;
	
	/**
	 * The executor to use to process the item read from the batch
	 */
	private final BatchJobItemExecutor<T> executor;
	
	/**
	 * Create a unique worker with the thread name.
	 * 
	 * @param threadName
	 *            the name of the thread to use
	 * 
	 * @param jobName
	 *            the name of the job that this worker is part of
	 * 
	 * @param itemReader
	 *            the item reader that reads an item to work on
	 * 
	 * @param executor
	 *            the executor to use for working on the job item
	 * 
	 * @throws IllegalArgumentException
	 *             if threadName or jobName are <code>null/empty</code>, or if
	 *             itemReader or executor are <code>null</code>
	 */
	public BatchWorker(String threadName, String jobName, Callable<T> itemReader, BatchJobItemExecutor<T> executor) {
		if(AssertUtils.isEmpty(threadName)) {
			throw new IllegalArgumentException("Thread name cannot be null/empty");
		}
		
		if(executor == null) {
			throw new IllegalArgumentException("Job piece executor cannot be null");
		}

		if(itemReader == null) {
			throw new IllegalArgumentException("Itemreader cannot be null");
		}
		
		this.threadName = threadName;
		this.jobName = jobName;
		this.itemReader = itemReader;
		this.executor = executor;
	}

	@Override
	protected void run() throws Exception {
		// setup the thread name to the right context
		Thread.currentThread().setName(this.threadName);
		
		// start running
		while(this.isRunning()) {
			// check if we need to stop right away
			if(this.stopNow) {
				return;
			}
			
			// check if we need to pause via redis config
			pauseIfNeeded();

			// read one message from queue
			LOGGER.debug("Fetching job item for batch-job: {}", this.jobName);
			
			// read the job from where-ever we are supposed to read from
			T job;
			try {
				job = this.itemReader.call();
			} catch(Exception e) {
				LOGGER.error("Unable to read job", e);
				
				if(this.executor.getWaitTimeOnJobReadErrorInMillis() > 0) {
					try {
						LOGGER.debug("Sleeping for stipulated time on job read error...");
						Thread.sleep(this.executor.getWaitTimeOnJobReadErrorInMillis());
					} catch (InterruptedException e1) {
						// make an exit immediately
						return;
					}
				}
				
				continue;
			}
			
			if(job == null) {
				LOGGER.debug("Job is read as null");
				
				if(this.executor.terminateExecutionOnNullJobItem()) {
					LOGGER.debug("Flagged to stop worker on null job... ending worker.");
					return;
				}
				
				if(this.executor.getWaitTimeOnNullJobInMillis() > 0) {
					try {
						LOGGER.debug("Sleeping for stipulated time on null job...");
						Thread.sleep(this.executor.getWaitTimeOnNullJobInMillis());
					} catch (InterruptedException e1) {
						// make an exit immediately
						return;
					}
				}
				
				continue;
			}
			
			// we found a job
			LOGGER.debug("Job to process read as: {}", job);
			
			// check if job has already been processed
			if(this.executor.jobAlreadyProcessed(job)) {
				LOGGER.debug("Job has already been processed, skipping now: {}", job);
				
				continue;
			}
			
			// run the job
			try {
				LOGGER.debug("Firing the job executor for job: {}", job);
				this.executor.executeJobItem(job);
			} catch(Exception e) {
				LOGGER.error("Unable to execute job: " + job, e);
			}
		}
	}
	
	/**
	 * Pause the worker if need be.
	 * 
	 */
	protected void pauseIfNeeded() {
		do {
			if(this.stopNow) {
				this.workerPaused = false;
				return;
			}
			
			if(!this.executor.pauseExecution()) {
				this.workerPaused = false;
				return;
			}
			
			// set pause
			this.workerPaused = true;
			
			// we need to pause
			LOGGER.info("Pausing this worker as dictated by JobPieceExecutor");

			// sleep for one minute
			try {
				Thread.sleep(this.executor.pauseCheckInterval());
			} catch (InterruptedException e) {
				// thread needs to shutdown
				return;
			}
		} while(true);
	}
	
	/**
	 * Invoked to request halt of this workers. Calling this method sets
	 * the {@link #stopNow} to <code>true</code> to signal the execution that execution
	 * should immediately halt. Once the job item, if any, is being processed - the
	 * execution will stop after the processing is complete.
	 * 
	 * @see com.google.common.util.concurrent.AbstractExecutionThreadService#triggerShutdown()
	 */
	@Override
	protected void triggerShutdown() {
		super.triggerShutdown();
		
		this.stopNow = true;
	}
	
	/**
	 * Return the thread name associated with this worker.
	 * 
	 * @return the name of the thread
	 */
	public String getThreadName() {
		return this.threadName;
	}

	/**
	 * Returns if the current worker is under the pause state or not.
	 * 
	 * @return <code>true</code> if worker is paused, <code>false</code> if
	 *         working
	 */
	public boolean isWorkerPaused() {
		return this.workerPaused;
	}
	
}
