/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sangupta.jerry.util.IOUtils;

/**
 * Utility class that provides convenience methods for working
 * with {@link UnsafeMemory}.
 *
 * @author sangupta
 *
 */
public class UnsafeMemoryUtils {

	public static void writeToFile(UnsafeMemory memory, File file) throws IOException {
		FileOutputStream stream = null;
		BufferedOutputStream boss = null;
		try {
			final byte[] bytes = memory.getBuffer();
			final int length = memory.getPosition();

			stream = new FileOutputStream(file);
			boss = new BufferedOutputStream(stream);

			int CHUNK_SIZE = 1 << 20;
			int chunks = length / CHUNK_SIZE;
			int extra = length % CHUNK_SIZE;
			if(extra > 0) {
				chunks++;
			}

			for(int ki = 0; ki < chunks; ki++) {
				int delta = CHUNK_SIZE;
				if((ki + 1) == chunks) {
					delta = extra;
				}
				boss.write(bytes, ki * CHUNK_SIZE, delta);
			}
		} catch(IOException e) {
			throw new IOException("Unable to write bytes to disk", e);
		} finally {
			IOUtils.closeQuietly(boss, stream);
		}
	}
}
