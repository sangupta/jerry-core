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


package com.sangupta.jerry.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

import com.sangupta.jerry.consume.GenericConsumer;
import com.sangupta.jerry.util.CheckUtils;

/**
 * A simple file reader that read a given file chunk by chunk and
 * pass it to a {@link GenericConsumer} of bytes so that the file
 * can be consumed.
 *
 * @author sangupta
 *
 */
public class FileByteChunkConsumer {

	private static int defaultBufferSize = 8192;

	private final File file;

	private final GenericConsumer<byte[]> consumer;

	public FileByteChunkConsumer(File file, GenericConsumer<byte[]> consumer) {
		CheckUtils.checkReadableFile(file);

		if(consumer == null) {
			throw new IllegalArgumentException("Consumer cannot be null");
		}

		this.file = file;
		this.consumer = consumer;
	}

	/**
	 * Start consuming the file to the end.
	 *
	 */
	public void consume() {
		boolean moveAhead = this.consumer.before();
		if(!moveAhead) {
			return;
		}

		// start reading file
		FileInputStream fis = null;
		BufferedInputStream stream = null;
		try {
			fis = new FileInputStream(this.file);
			stream = new BufferedInputStream(fis);

			// start reading
			byte[] chunk = new byte[defaultBufferSize];
		    int chunkLen = 0;
		    while ((chunkLen = stream.read(chunk)) != -1) {
		    	if(chunkLen != defaultBufferSize) {
		    		// copy the relevant bytes into a new buffer space
		    		chunk = Arrays.copyOfRange(chunk, 0, chunkLen);
		    	}

		        moveAhead = this.consumer.consume(chunk);
		        if(!moveAhead) {
		        	return;
		        }
		    }

			// if the file has been read completely
			this.consumer.after();
		} catch(IOException e) {
			this.consumer.onException(e);
		} finally {
			IOUtils.closeQuietly(stream);
			IOUtils.closeQuietly(fis);
		}
	}

}
