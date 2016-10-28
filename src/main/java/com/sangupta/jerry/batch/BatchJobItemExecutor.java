package com.sangupta.jerry.batch;

/**
 * The executor that actually performs something useful on the job item
 * that has been provided to the job queue.
 * 
 * @author sangupta
 *
 * @param <T>
 */
public interface BatchJobItemExecutor<T> {

	public T getJobItem();
	
	public long getWaitTimeOnJobReadErrorInMillis();

	public long getWaitTimeOnNullJobInMillis();
	
	public boolean pauseExecution();
	
	public long pauseCheckInterval();

	public boolean jobAlreadyProcessed(T job);

	public void executeJobItem(T job);
	
}
