/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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


package com.sangupta.jerry.bitarray;

public class TestJavaBitSetArray extends AbstractTestBitArray {

	@Override
	protected BitArray getNewBitArray() throws Exception {
		return new JavaBitSetArray(MAX_ELEMENTS);
	}
	
	@Override
	protected int getMaxElements() {
		return 1024 * 10; // JavaBitSet is too slow
	}

}
