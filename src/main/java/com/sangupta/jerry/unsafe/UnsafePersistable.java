/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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
 
package com.sangupta.jerry.unsafe;

/**
 * Contract for an object that wishes to be serialized/deserialized using
 * {@link UnsafeMemory}.
 * 
 * @author sangupta
 *
 */
public interface UnsafePersistable {
	
	/**
	 * Serialize the object into an instance of {@link UnsafeMemory} and return
	 * that back.
	 * 
	 */
	public UnsafeMemory writeUnsafe();
	
	/**
	 * Serialize the object into the given instance of {@link UnsafeMemory}.
	 * 
	 * @param memory
	 */
	public void writeUnsafe(UnsafeMemory memory);
	
	/**
	 * De-serialize the object using the given instance of {@link UnsafeMemory}.
	 * 
	 * @param memory
	 *            the {@link UnsafeMemory} instance where the objects serialized
	 *            bytes are
	 */
	public void readUnsafe(UnsafeMemory memory);

}
