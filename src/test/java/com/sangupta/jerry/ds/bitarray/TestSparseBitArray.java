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

package com.sangupta.jerry.ds.bitarray;

/**
 * Unit tests for {@link SparseBitArray}.
 * 
 * @author sangupta
 *
 */
public class TestSparseBitArray extends TestAbstractBitArray {

	private final int BUCKET_SIZE = 1024;
	
	private final int NUM_BUCKETS = MAX_ELEMENTS / BUCKET_SIZE;
	
	@Override
	protected BitArray getNewBitArray() throws Exception {
		return new SparseBitArray(NUM_BUCKETS, BUCKET_SIZE);
	}
	
}
