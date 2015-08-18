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
 
package com.sangupta.jerry.ds.bitarray;

import java.io.File;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestMMapFileBackedBitArray {
	
	@Test
	public void testGetAndSet() throws Exception {
		File file = File.createTempFile("test-ba-", ".bin");
		file.deleteOnExit();
		int maxElements = 1000 * 1000; // 1 million
		BitArray ba = new MMapFileBackedBitArray(file, maxElements);
		
		Random random = new Random();
		for(int index = 0; index < maxElements; index++) {
			int nextBit = random.nextInt(maxElements);
			boolean didSet = ba.setBit(nextBit);
			if(didSet) {
				boolean isSet = ba.getBit(nextBit);
				Assert.assertTrue(isSet);
				ba.clearBit(nextBit);
				isSet = ba.getBit(nextBit);
				Assert.assertFalse(isSet);
			}
		}
		
		ba.close();
	}
	
	@Test
	public void testGetHighestBitSet() throws Exception {
		File file = File.createTempFile("test-ba-", ".bin");
		file.deleteOnExit();
		int maxElements = 1000 * 1000; // 1 million
		BitArray ba = new MMapFileBackedBitArray(file, maxElements);
		
		Random random = new Random();
		int currentMaxBit = -1;
		for(int index = 0; index < maxElements; index++) {
			int nextBit = random.nextInt(maxElements);
			boolean didSet = ba.setBit(nextBit);
			if(!didSet) {
				continue;
			}
			
			currentMaxBit = Math.max(currentMaxBit, nextBit);
			Assert.assertEquals(currentMaxBit, ba.getHighestBitSet());
		}
		
		ba.close();
	}
	
	@Test
	public void testGetLowestBitSet() throws Exception {
		File file = File.createTempFile("test-ba-", ".bin");
		file.deleteOnExit();
		int maxElements = 1000 * 1000; // 1 million
		BitArray ba = new MMapFileBackedBitArray(file, maxElements);
		
		Random random = new Random();
		int currentMinBit = Integer.MAX_VALUE;
		for(int index = 0; index < maxElements; index++) {
			int nextBit = random.nextInt(maxElements);
			boolean didSet = ba.setBit(nextBit);
			if(!didSet) {
				continue;
			}
			
			currentMinBit = Math.min(currentMinBit, nextBit);
			Assert.assertEquals(currentMinBit, ba.getLowestBitSet());
		}
		
		ba.close();
	}

}
