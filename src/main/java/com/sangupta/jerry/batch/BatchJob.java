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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import com.sangupta.jerry.util.AssertUtils;

/**
 * This is the main class that builds, instantiates and controls one particular
 * batch job. This way multiple batch jobs can be executed in parallel.
 * 
 * @author sangupta
 *
 * @since 3.0.0
 */
public abstract class BatchJob<T> {
	
	/**
	 * My private logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);
	
	/**
	 * The job name
	 */
	protected final String jobName;
	
	/**
	 * The internal {@link ServiceManager} that manages execution of all threads
	 * 
	 */
	private ServiceManager serviceManager;
	
	/**
	 * Return the job items over which we should work. Can return
	 * <code>null</code> to signal there are no current job items.
	 * 
	 * @return the job item
	 */
	protected abstract T getJobItem();
	
	/**
	 * Time in milliseconds to wait during shutdown, before each service worker
	 * thread will be killed to stop all services
	 * 
	 * @return the time in milliseconds
	 */
	protected abstract long getShutdownWaitTimeMillis();
	
	/**
	 * Get the {@link BatchJobItemExecutor} - the executor that process one
	 * given job item.
	 * 
	 * @return the associated {@link BatchJobItemExecutor}
	 */
	protected abstract BatchJobItemExecutor<T> getJobPieceExecutor();
	
	/**
	 * Extension point to do something before worker threads are initialized.
	 * Returning a <code>true</code> will continue further in the setup.
	 * Returning a <code>false</code> will immediately halt creation of workers.
	 * 
	 * @return <code>true</code> to proceed ahead, <code>false</code> to stop
	 *         initialization
	 */
	protected boolean beforeWorkersInitialize() {
		return true;
	}
	
	/**
	 * Extension point to do something after worker threads have been
	 * initialized. Returning a <code>true</code> will continue further in the
	 * setup. Returning a <code>false</code> will immediately halt working
	 * further.
	 * 
	 * @return <code>true</code> if workers should start processing,
	 *         <code>false</code> to stop immediately
	 */
	protected boolean afterWorkersInitialize() {
		return true;
	}
	
	/**
	 * Constructor
	 * 
	 * @param jobName
	 *            the name of the job to use
	 * 
	 * @throws IllegalArgumentException
	 *             if jobName is <code>null/empty</code>
	 */
	public BatchJob(String jobName) {
		if(AssertUtils.isEmpty(jobName)) {
			throw new IllegalArgumentException("Job name cannot be null/empty");
		}
		
		// set the crawler name
		this.jobName = jobName;
		
		// this should be the last line in the crawler
		BatchCentral.addJob(this);
	}
	
	/**
	 * Start this batch job asynchronously.
	 * 
	 * @param numThreads
	 *            the number of threads to use for processing the job
	 * 
	 * @return <code>true</code> if batch job was started, <code>false</code>
	 *         otherwise
	 * 
	 * @throws IllegalArgumentException
	 *             if the number of threads is less than or equal to zero
	 */
	public boolean startJobAsync(int numThreads) {
		if(numThreads <= 0) {
			throw new IllegalArgumentException("Number of threads cannot be less than or equal to zero");
		}
		
		// running pre-initialization for job
		boolean moveAhead = beforeWorkersInitialize();
		if(!moveAhead) {
			LOGGER.info("Pre-initialization of job does not want us to move forward.");
			return false;
		}
		
		// start job
		LOGGER.info("Creating {} number of job worker threads for job: {}", numThreads, this.jobName);
		
		// create a new guava service manager to manage threads
		List<Service> services = new ArrayList<>();
		
		final Callable<T> itemReader = new Callable<T>() {
			
			@Override
			public T call() throws Exception {
				return BatchJob.this.getJobItem();
			}
			
		};
		
		for(int index = 0; index < numThreads; index++) {
			// create a new worker
			BatchWorker<T> worker = new BatchWorker<T>("Job-Worker-" + this.jobName + "-" + index, this.jobName, itemReader, this.getJobPieceExecutor());
			
			// add to list of workers
			services.add(worker);
		}
		
		// create manager
		this.serviceManager = new ServiceManager(services);

		// and start the server
		this.serviceManager.startAsync();
		
		LOGGER.info("All worker threads have now started.Running after workers initialize...");
		
		// do post initialization
		afterWorkersInitialize();
		
		// return true
		return true;
	}
	
	/**
	 * Signal all worker threads to stop.
	 * 
	 */
	public void stopAsync() {
		this.serviceManager.stopAsync();
	}
	
	/**
	 * Shutdown this crawler instance, invoking shutdown on all
	 * worker threads.
	 * 
	 */
	public void shutdown() {
		// remove this instance from central
		BatchCentral.removeJob(this);
		
		// check for service manager
		if(this.serviceManager == null) {
			return;
		}
		
		LOGGER.debug("Stopping all services...");
		this.serviceManager.stopAsync();
		
		LOGGER.debug("Changing state of each worker manually...");
		ImmutableCollection<Service> services = this.serviceManager.servicesByState().values();
		for(Service service : services) {
			if(service == null) {
				continue;
			}
			
			@SuppressWarnings("unchecked")
			BatchWorker<T> worker = (BatchWorker<T>) service;
			
			// trigger shutdown on all workers
			worker.triggerShutdown();
		}
		
		// wait for stipulated time to shutdown
		try {
			LOGGER.debug("Waiting for all thread to die out...");
			this.serviceManager.awaitStopped(this.getShutdownWaitTimeMillis(), TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			LOGGER.error("Unable to wait for stopping all threads", e);
		}
	}
	
	/**
	 * Wait for all service threads to complete - either terminate naturally
	 * or fail.
	 * 
	 */
	public void waitForCompletion() {
		if(this.serviceManager == null) {
			return;
		}
		
		this.serviceManager.awaitStopped();
	}
	
	/**
	 * Return the name of this crawler.
	 * 
	 * @return the name of the job
	 */
	public String getJobName() {
		return this.jobName;
	}
	
}
