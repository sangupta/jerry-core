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


package com.sangupta.jerry.consume;

import java.io.IOException;

/**
 * A generic processing class that can be used to consume an entity.
 *
 * @author sangupta
 *
 * @param <T>
 *            the type of instance that this consumer can consume
 */
public abstract class GenericConsumer<T> {

	/**
	 * Called before the consumption of entity starts.
	 *
	 * @return <code>true</code> if consumption should continue, or
	 *         <code>false</code> if processing should stop immediately.
	 *
	 */
	public boolean before() {
		return true;
	}

	/**
	 * Called for processing of the entity.
	 *
	 * @param data
	 *            the entity data to consume
	 *
	 * @return <code>true</code> if consumption should continue, or
	 *         <code>false</code> if processing should stop immediately.
	 */
	public abstract boolean consume(T data);

	/**
	 * Called after completing of processing when the entity was consumed
	 * successfully, and not stopped in between either by returning a
	 * <code>false</code> in the {@link #consume(Object)} method, or if an
	 * exception was raised.
	 *
	 */
	public void after() {
		// do nothing
	}

	/**
	 * Called when an exception is raised during the processing of file.
	 *
	 * @param e
	 *            the {@link IOException} that was raised.
	 */
	public void onException(Exception e) {
		// do nothing
	}

}
