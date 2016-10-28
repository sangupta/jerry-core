package com.sangupta.jerry.batch;

import java.util.ArrayList;
import java.util.List;
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
 */
public abstract class BatchJob<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);
	
	protected final String jobName;
	
	private ServiceManager serviceManager;
	
	protected abstract int getShutdownWaitTime();
	
	protected abstract TimeUnit getShutdownWaitTimeUnit();
	
	protected abstract BatchJobItemExecutor<T> getJobPieceExecutor();
	
	protected boolean beforeWorkersInitialize() {
		return true;
	}
	
	protected boolean afterWorkersInitialize() {
		return true;
	}
	
	/**
	 * Constructor
	 * 
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
	 * Start this crawler asynchronously.
	 * 
	 * @return
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
		for(int index = 0; index < numThreads; index++) {
			// create a new worker
			BatchWorker<T> worker = new BatchWorker<T>(this.jobName, "Job-Worker-" + this.jobName + "-" + index, this.getJobPieceExecutor());
			
			// add to list of workers
			services.add(worker);
		}
		
		// create manager
		this.serviceManager = new ServiceManager(services);

		// and start the server
		this.serviceManager.startAsync();
		
		LOGGER.info("All worker threads have now started.\nRunning after workers initialize...");
		
		// do post initialization
		afterWorkersInitialize();
		
		// return true
		return true;
	}
	
	/**
	 * Shutdown this crawler instance
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
			this.serviceManager.awaitStopped(this.getShutdownWaitTime(), this.getShutdownWaitTimeUnit());
		} catch (TimeoutException e) {
			LOGGER.error("Unable to wait for stopping all threads", e);
		}
	}
	
	/**
	 * Return the name of this crawler.
	 * 
	 * @return
	 */
	public String getJobName() {
		return this.jobName;
	}
	
}
