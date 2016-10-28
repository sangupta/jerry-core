package com.sangupta.jerry.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.sangupta.jerry.util.AssertUtils;

/**
 * The worker thread that works over a single batch job. Multiple
 * worker threads race to utilize and work over the amount of work
 * backed by a queue.
 * 
 * @author sangupta
 *
 * @param <T>
 */
public class BatchWorker<T> extends AbstractExecutionThreadService  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

	private final String threadName;
	
	private final String crawlerName;
	
	private volatile boolean workerPaused = false;
	
	private volatile boolean stopNow = false;
	
	private final BatchJobItemExecutor<T> executor;
	
	/**
	 * Create a unique worker with the thread name.
	 * 
	 * @param threadName
	 * @param yoJobPieceExecutor 
	 */
	public BatchWorker(String threadName, String crawlerName, BatchJobItemExecutor<T> executor) {
		if(AssertUtils.isEmpty(threadName)) {
			throw new IllegalArgumentException("Thread name cannot be null/empty");
		}
		
		if(executor == null) {
			throw new IllegalArgumentException("Job piece executor cannot be null");
		}
		
		this.threadName = threadName;
		this.crawlerName = crawlerName;
		this.executor = executor;
	}

	@Override
	protected void run() throws Exception {
		while(this.isRunning()) {
			// check if we need to stop right away
			if(this.stopNow) {
				return;
			}
			
			// check if we need to pause via redis config
			pauseIfNeeded();

			// read one message from queue
			LOGGER.debug("Reading crawling url for crawler: {}", this.crawlerName);
			
			// read the job from where-ever we are supposed to read from
			T job;
			try {
				job = this.executor.getJobItem();
			} catch(Exception e) {
				LOGGER.error("Unable to read job", e);
				
				if(this.executor.getWaitTimeOnJobReadErrorInMillis() > 0) {
					try {
						LOGGER.debug("Sleeping for stipulated time on job read error...");
						Thread.sleep(this.executor.getWaitTimeOnJobReadErrorInMillis());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
				continue;
			}
			
			if(job == null) {
				LOGGER.error("Job is read as null");
				
				if(this.executor.getWaitTimeOnNullJobInMillis() > 0) {
					try {
						LOGGER.debug("Sleeping for stipulated time on null job...");
						Thread.sleep(this.executor.getWaitTimeOnNullJobInMillis());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
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
				// eat up
			}
		} while(true);
	}
	
	/**
	 * @see com.google.common.util.concurrent.AbstractExecutionThreadService#triggerShutdown()
	 */
	@Override
	protected void triggerShutdown() {
		super.triggerShutdown();
		
		this.stopNow = true;
	}
	
	public String getThreadName() {
		return this.threadName;
	}
	
	public boolean isWorkerPaused() {
		return this.workerPaused;
	}
	
}
