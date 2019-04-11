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

/**
 * A class that allows reading a {@link String} via simple tokens.
 *
 * @author sangupta
 *
 */
public class AdvancedStringReader {

	/**
	 * The original string we are working on. Can be <code>null</code>.
	 */
	private final String original;

	/**
	 * A character array that we are working on
	 */
	private final char[] chars;

	/**
	 * The length of the string we are working on. If the {@link #str} is
	 * <code>null</code>, the value will be <code>-1</code>.
	 */
	private final int length;

	/**
	 * The pointer in the string from where next read will happen
	 */
	private int current = 0;

	/**
	 * Create a new instance of {@link AdvancedStringReader}.
	 *
	 * @param str the string to operate upon
	 *
	 */
	public AdvancedStringReader(String str) {
		if (str == null) {
			this.original = null;
			this.chars = null;
			this.length = -1;
			return;
		}

		this.original = str;
		this.chars = str.toCharArray();
		this.length = this.chars.length;
	}

	/**
	 * Check if the reader has more tokens that you can read.
	 *
	 * @return <code>true</code> if there are more characters that can be read,
	 *         <code>false</code> otherwise
	 */
	public boolean hasNext() {
		return this.current < this.length;
	}

	/**
	 * Read the character at the current position but do not move ahead.
	 * 
	 * @return the next available character
	 */
	public char peekAhead() {
		return this.chars[this.current];
	}

	/**
	 * Look ahead in the string a given number of characters.
	 * 
	 * @param ahead the position from current to look ahead
	 * 
	 * @return the character at the position
	 */
	public char peekAhead(int ahead) {
		int pos = this.current + ahead;
		if (pos > this.length) {
			return ((char) -1);
		}

		return this.chars[pos];
	}

	/**
	 * Peek into the next given number of characters.
	 * 
	 * @param numCharacters number of characters to peek into
	 * 
	 * @return the read string, or <code>null</code> if no more characters are
	 *         available
	 */
	public String peekString(int numCharacters) {
		if (!this.hasNext()) {
			return null;
		}

		int end = this.current + numCharacters;
		if (end > this.length) {
			end = this.length;
		}

		return this.substring(this.current, end);
	}

	/**
	 * Peek the next available position of the given character from the current
	 * position.
	 * 
	 * @param c the character to look ahead
	 * 
	 * @return the index if found, or <code>-1</code>
	 */
	public int peekCharIndex(char c) {
		for (int index = this.current; index < this.length; index++) {
			if (this.chars[index] == c) {
				return index - this.current;
			}
		}

		return -1;
	}

	/**
	 * Peek the first non-white-space character available. The {@link #current}
	 * pointer is not moved ahead.
	 *
	 * @return the first non-white-space character available to read next.
	 */
	public char peekNextNonWhitespace() {
		for (int index = this.current; index < this.length; index++) {
			if (!Character.isWhitespace(this.chars[index])) {
				return this.chars[index];
			}
		}

		return ((char) -1);
	}

	/**
	 * Read the string from current position to the next occurrence of the given
	 * character. The given character is excluded in returned string.
	 *
	 * @param separator the character till which we are going to read
	 *
	 * @return the sub-string thus extracted
	 */
	public String readBefore(char separator) {
		return this.readBefore(String.valueOf(separator), 1);
	}

	/**
	 * Read the string from current position to the next nth occurrence of the given
	 * character. The given character is excluded in returned string.
	 *
	 * @param separator the character till which we are going to read
	 *
	 * @param occurence the times the character will be included before we stop
	 *                  further reading
	 *
	 * @return the sub-string thus extracted
	 */
	public String readBefore(char separator, int occurence) {
		return this.readBefore(String.valueOf(separator), occurence);
	}

	/**
	 * Read ahead till we encounter the given separator or there are no more tokens
	 * in the queue. The given character is excluded in returned string.
	 * 
	 * @param separator the separator being looked at
	 * 
	 * @return the {@link String} thus read
	 */
	public String readBefore(String separator) {
		return this.readBefore(separator, 1);
	}

	/**
	 * Read ahead till the next given occurrence of the given separator or if there
	 * are no more tokens in the queue. The given character is excluded in returned
	 * string.
	 * 
	 * @param separator the separator being looked at
	 * 
	 * @param occurence the occurrence to find
	 * 
	 * @return the {@link String} thus read
	 * 
	 */
	public String readBefore(String separator, int occurence) {
		if (!this.hasNext()) {
			return null;
		}

		int numFound = 0;
		int index = -1;
		int searchFrom = this.current;
		do {
			index = this.indexOf(separator, searchFrom);
			if (index < 0) {
				int start = this.current;
				this.current = this.length;
				return this.substring(start);
			}

			numFound++;
			if (numFound == occurence) {
				break;
			}

			searchFrom = index + 1;
		} while (true);

		String extracted = this.substring(this.current, index);
		this.current = index + separator.length();
		return extracted;
	}

	/**
	 * Read the entire remaining string in this reader.
	 *
	 * @return the remaining sub-string, <code>null</code> if there are no more
	 *         tokens to be read
	 */
	public String readRemaining() {
		if (!this.hasNext()) {
			return null;
		}

		return this.substring(this.current);
	}

	/**
	 * Read string from current position to number of characters ahead.
	 * 
	 * @param numCharacters the number of characters to read
	 * 
	 * @return the {@link String} thus read
	 */
	public String readNext(int numCharacters) {
		if (!this.hasNext()) {
			return null;
		}

		int end = this.current + numCharacters;
		if (end > this.length) {
			end = this.length;
		}

		String result = this.substring(this.current, end);
		this.current = end;
		return result;
	}

	/**
	 * Read after skipping to the next occurrence of the given character. The
	 * character is excluded from read string.
	 * 
	 * @param character the character to skip to
	 * 
	 * @return the read string, or <code>null</code> if no more characters are
	 *         available
	 */
	public String readAfter(char character) {
		if (!this.hasNext()) {
			return null;
		}

		int index;
		for (index = this.current; index < this.length; index++) {
			if (this.chars[index] == character) {
				break;
			}
		}

		index++;
		this.current = this.length;
		return this.substring(index);
	}

	/**
	 * Read starting with the next occurrence of the given character. The character
	 * is included in read string.
	 * 
	 * @param character the character to start reading from
	 * 
	 * @return the read string, or <code>null</code> if no more characters are
	 *         available
	 */
	public String readFrom(char character) {
		if (!this.hasNext()) {
			return null;
		}

		int index;
		for (index = this.current; index < this.length; index++) {
			if (this.chars[index] == character) {
				break;
			}
		}

		this.current = this.length;
		return this.substring(index);
	}

	/**
	 * Read the string between the first occurrence of starting character, and the
	 * next subsequent occurrence of the closing character. If the starting
	 * character cannot be found, will return <code>null</code>. If the starting
	 * character is found, but closing character cannot be found, will return the
	 * entire string after. The starting character is never included.
	 *
	 * @param starting the character to start reading from
	 *
	 * @param closing  the character to stop reading at
	 *
	 * @return the sub-string thus extracted, or <code>null</code> if there are no
	 *         more characters remaining.
	 */
	public String readBetween(char starting, char closing) {
//		if (!this.hasNext()) {
//			return null;
//		}
//		
//		int start = this.indexOf(starting, this.current);
//		if(start < 0) {
//			return null;
//		}
//		
//		start++;
//		int end = this.indexOf(closing, start);
//		if(end < 0) {
//			end = this.length;
//		}
//		
//		String returnValue = this.substring(start, end);
//		this.current = end;
//		return returnValue;
		
		return this.readBetween(String.valueOf(starting), String.valueOf(closing));
	}
	
	public String readBetween(String starting, String closing) {
		if (!this.hasNext()) {
			return null;
		}
		
		int start = this.indexOf(starting, this.current);
		if(start < 0) {
			return null;
		}
		
		start = start + starting.length();
		int end = this.indexOf(closing, start);
		if(end < 0) {
			end = this.length;
		}
		
		String returnValue = this.substring(start, end);
		this.current = end;
		return returnValue;
	}

	/**
	 * Skip the given number of characters
	 * 
	 * @param numCharacters
	 * @return
	 */
	public int skip(int numCharacters) {
		int end = this.current + numCharacters;
		if (end > this.length) {
			end = this.length;
		}

		int returnValue = end - this.current;
		this.current = end;
		return returnValue;
	}

	/**
	 * Skip all whitespace characters that follow the {@link #current} reading
	 * position.
	 *
	 * @return the number of characters skipped
	 */
	public int skipWhiteSpace() {
		for (int index = this.current; index < this.length; index++) {
			if (!Character.isWhitespace(this.chars[index])) {
				// we found one
				int returnValue = index - this.current;
				this.current = index;

				return returnValue;
			}
		}

		int returnValue = this.length - this.current;
		this.current = this.length;
		return returnValue;
	}

	/**
	 * Reset the reading position to the start of the actual string. This sets the
	 * {@link #current} pointer back to zero.
	 */
	public void reset() {
		this.current = 0;
	}

	private String substring(int begin) {
		return new String(this.chars, begin, this.length - begin);
	}

	private String substring(int begin, int end) {
		if (end < 0) {
			return this.substring(begin);
		}

		return new String(this.chars, begin, end - begin);
	}

	private int indexOf(String lookup, int location) {
		return this.original.indexOf(lookup, location);
	}

}
