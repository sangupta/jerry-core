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
	 */
	public static void removeJob(BatchJob<?> job) {
		if(job == null) {
			throw new IllegalArgumentException("Job cannot be null");
		}
		
		BatchCentral.ALL_BATCH_JOBS.remove(job);
	}
	
}
