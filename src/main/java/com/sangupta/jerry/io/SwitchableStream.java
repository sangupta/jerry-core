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
 
package com.sangupta.jerry.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An {@link OutputStream} that can allow to switch between two standard {@link OutputStream}s
 * with a single method call.
 * 
 * @author sangupta
 *
 */
public class SwitchableStream extends OutputStream {
	
	protected final OutputStream stream1;
	
	protected final OutputStream stream2;
	
	protected final AtomicReference<OutputStream> currentStream = new AtomicReference<OutputStream>();
	
	public SwitchableStream(OutputStream stream1, OutputStream stream2) {
		this.stream1 = stream1;
		this.stream2 = stream2;
	}

	@Override
	public void write(int b) throws IOException {
		this.currentStream.get().write(b);
	}
	
	public void switchStreams() {
		do {
			boolean success = this.currentStream.compareAndSet(stream1, stream2);
			if(success) {
				return;
			}
			
			success = this.currentStream.compareAndSet(stream2, stream1);
			if(success) {
				return;
			}
		} while(true);
	}

}
