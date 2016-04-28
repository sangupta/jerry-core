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
 

package com.sangupta.jerry.io;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.consume.GenericConsumer;
import com.sangupta.jerry.util.StringUtils;

/**
 * Unit tests for {@link FileByteChunkConsumer} class
 * 
 * @author sangupta
 *
 */
public class TestFileByteChunkConsumer {

	@Test
	public void test() throws IOException {
		File file = File.createTempFile("jerry-core-", ".bin");
		file.deleteOnExit();
		
		String contents = StringUtils.getRandomString(5000);
		byte[] bytes = contents.getBytes();
		
		FileUtils.writeByteArrayToFile(file, bytes);
		
		final AtomicLong al = new AtomicLong(100); 
		GenericConsumer<byte[]> consumer = new GenericConsumer<byte[]>() {
			
			@Override
			public boolean before() {
				al.set(0);
				return super.before();
			}
			
			@Override
			public boolean consume(byte[] data) {
				al.addAndGet(data.length);
				return true;
			}
			
			@Override
			public void after() {
				al.addAndGet(50);
				super.after();
			}
		};
		
		FileByteChunkConsumer fileConsumer = new FileByteChunkConsumer(file, consumer);
		fileConsumer.consume();
		
		// check now
		Assert.assertEquals(bytes.length + 50, al.get()); // 50 because we added 50 in after
	}
	
}