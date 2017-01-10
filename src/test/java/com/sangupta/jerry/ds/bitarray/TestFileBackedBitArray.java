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

import java.io.File;
import java.io.IOException;

public class TestFileBackedBitArray extends TestAbstractBitArray {
	
	@Override
	protected BitArray getNewBitArray() throws IOException {
		File file = File.createTempFile("test-ba-", ".bin");
		file.deleteOnExit();
		return new FileBackedBitArray(file, MAX_ELEMENTS);
	}

	@Override
	protected int getMaxElements() {
		return 1024;
	}
	
}
