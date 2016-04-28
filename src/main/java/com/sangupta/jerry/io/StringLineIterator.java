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

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple iterator that reads a {@link String} line-by-line and
 * also maintains the line number, the starting and ending index positions
 * of the current read line.
 * 
 * This may be useful when parsing a source file where positions need
 * to be maintained.
 * 
 * @author sangupta
 *
 */
public class StringLineIterator implements Iterator<String> {
	
	/**
	 * The character array backing this iterator.
	 */
	private final char[] chars;
	
	/**
	 * The length of the string
	 */
	private final int length;
	
	/**
	 * Holds the current line number
	 */
	private int lineNumber = -1;
	
	/**
	 * Holds the beginning of the current line
	 */
	private int lineBegin = -1;
	
	/**
	 * Holds the ending of current line
	 */
	private int lineEnd = -1;
	
	/**
	 * Current read position in array
	 */
	private int current;
	
	/**
	 * Create a new instance of the {@link StringLineIterator}.
	 * 
	 * @param input
	 *            the {@link String} instance to read from
	 */
	public StringLineIterator(String input) {
		this.chars = input.toCharArray();
		this.length = input.length();
	}
	
	@Override
	public boolean hasNext() {
		if(this.current >= this.length) {
			return false;
		}
		
		return true;
	}

	@Override
	public String next() {
		if(!hasNext()) {
			throw new NoSuchElementException("No more lines");
		}
		
		if(this.lineNumber < 0) {
			this.lineNumber = 0;
		}
		
		for(int index = this.current; index < this.length; index++) {
			char c = chars[index];
			if(c == '\n') {
				this.lineNumber++;
				this.lineBegin = this.current;
				this.lineEnd = index;
				this.current = index + 1;
				
				return new String(Arrays.copyOfRange(this.chars, this.lineBegin, this.lineEnd));
			}
			
			if(c == '\r') {
				this.lineNumber++;
				this.lineBegin = this.current;
				this.lineEnd = index;
				this.current = index + 1;
				if(chars[index + 1] == '\n') {
					this.current++;
				}
				
				return new String(Arrays.copyOfRange(this.chars, this.lineBegin, this.lineEnd));
			}
		}
		
		// we are till the end of file
		this.lineNumber++;
		this.lineBegin = this.current;
		this.lineEnd = this.length;
		this.current = this.length;
		return new String(Arrays.copyOfRange(this.chars, this.lineBegin, this.length));
	}
	
	/**
	 * Return the current line number.
	 * 
	 * @return the current line number
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	/**
	 * Return the index position at which the current line starts.
	 * 
	 * @return the index for the beginning of thi sline
	 */
	public int getLineBegin() {
		return this.lineBegin;
	}
	
	/**
	 * Return the index position at which the current line ends.
	 * 
	 * @return the index position of line end
	 */
	public int getLineEnd() {
		return this.lineEnd;
	}
	
	/**
	 * Return the index position at which the next read for the line will occur.
	 * 
	 * @return the index at which read will occur
	 */
	public int getNextLineBegin() {
		if(this.current >= this.length) {
			return -1;
		}
		
		return this.current;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove unsupported on LineIterator");
	}
	
}