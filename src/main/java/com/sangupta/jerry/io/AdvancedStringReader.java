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

/**
 * A class that allows reading a {@link String} via simple tokens.
 *
 * @author sangupta
 *
 */
public class AdvancedStringReader {

    /**
     * The string that we are working on. Can be <code>null</code>.
     */
	private final String str;

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
	    if(str == null) {
	        this.str = null;
	        this.length = -1;
	        return;
	    }

		this.str = str;
		this.length = str.length();
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
     * Read the string from current position to the next occurrence of the given
     * character.
     *
     * @param separator
     *            the character till which we are going to read
     *
     * @return the sub-string thus extracted
     */
	public String readTillNext(char separator) {
		return this.readTillNext(String.valueOf(separator), 1);
	}
	
	public char peekAhead(int position) {
	    int pos = this.current + position;
	    if(pos > this.length) {
	        return 0;
	    }
	    
	    return this.str.charAt(pos);
	}
	
	public String readTillPosition(int position) {
        int pos = this.current + position;
        if(pos > this.length) {
            pos = this.length;
        }
	    
        String result = this.str.substring(this.current, pos);
        this.current = pos;
        return result;
	}

	/**
     * Read the string from current position to the next nth occurrence of the
     * given character.
     *
     * @param separator
     *            the character till which we are going to read
     *
     * @param occurence
     *            the times the character will be included before we stop
     *            further reading
     *
     * @return the sub-string thus extracted
     */
	public String readTillNext(char separator, int occurence) {
	    return this.readTillNext(String.valueOf(separator), occurence);
	}

	public int peekIndex(char c) {
	    for(int index = this.current; index < this.length; index++) {
	        if(this.str.charAt(index) == c) {
	            return index - this.current;
	        }
	    }
	    
	    return -1;
	}

    /**
	 *
	 * @param separator
	 * @return
	 */
	public String readTillNext(String separator) {
	    return this.readTillNext(separator, 1);
	}

	/**
	 *
	 * @param separator
	 * @param occurence
	 * @return
	 */
	public String readTillNext(String separator, int occurence) {
		if(!this.hasNext()) {
			return null;
		}

		int numFound = 0;
        int index = -1;
        int searchFrom = this.current;
		do {
            index = this.str.indexOf(separator, searchFrom);
    		if(index < 0) {
    			int start = this.current;
    			this.current = str.length();
    			return this.str.substring(start);
    		}

    		numFound++;
    		if(numFound == occurence) {
    		    break;
    		}

    		searchFrom = index + 1;
		} while(true);

		String extracted = this.str.substring(this.current, index);
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
		if(!this.hasNext()) {
			return null;
		}

		return this.str.substring(this.current);
	}

	/**
     * Skip reading next N characters
     *
     * @param numCharacters
     *            the number of characters to skip reading.
     *
     * @return the number of characters actually skipped.
     */
	public int skipNext(int numCharacters) {
	    if(!this.hasNext()) {
            return 0;
        }

        int end = this.current + numCharacters;
        if (end > this.length) {
            end = this.length;
        }

        int skipped = end - this.current;
        this.current = end;
        return skipped;
    }

	public String readNext(int numCharacters) {
	    if(!this.hasNext()) {
	        return null;
	    }

	    int end = this.current + numCharacters;
	    if(end > this.length) {
	        end = this.length;
	    }
	    
	    String result = this.str.substring(this.current, end);
	    this.current = end;
	    return result;
	}
	
	public String readAfter(char character) {
	    if(!this.hasNext()) {
            return null;
        }
	    
	    int index;
	    for(index = this.current; index < this.length; index++) {
	        if(this.str.charAt(index) == character) {
	            break;
	        }
	    }
	    
	    index++;
	    this.current = this.length;
	    return this.str.substring(index);
	}
	
	public String readFrom(char character) {
	    if(!this.hasNext()) {
            return null;
        }
        
        int index;
        for(index = this.current; index < this.length; index++) {
            if(this.str.charAt(index) == character) {
                break;
            }
        }
        
        this.current = this.length;
        return this.str.substring(index);
	}
	
	public String peekNext(int numCharacters) {
	    if(!this.hasNext()) {
            return null;
        }

        int end = this.current + numCharacters;
        if(end > this.length) {
            end = this.length;
        }
        
        return this.str.substring(this.current, end);
	}

	/**
     * Read the string between the first occurrence of starting character, and
     * the next subsequent occurrence of the closing character. If the starting
     * character cannot be found, will return <code>null</code>. If the starting
     * character is found, but closing character cannot be found, will return
     * the entire string after and including the starting character.
     *
     * @param starting
     *            the character to start reading from
     *
     * @param closing
     *            the character to stop reading at
     *
     * @return the sub-string thus extracted, or <code>null</code> if there are
     *         no more characters remaining.
     */
	public String readBetween(char starting, char closing) {
		if(!this.hasNext()) {
			return null;
		}

		if(starting == closing) {
		    // this is a special case
		    // find the two indexes
		    int start = this.str.indexOf(starting, this.current);
		    if(start == -1) {
		        return null;
		    }

		    start++;

		    int end = this.str.indexOf(closing, start + 1);
		    if(end == -1) {
		        end = this.length;
		    }

		    this.current = end + 1;
		    return this.str.substring(start, end);
		}

		int count = 0;
		int start = -1;
		boolean found = false;
		for(int index = this.current; index < this.length; index++) {
			char c = this.str.charAt(index);
			if(c == starting) {
                if(!found) {
                    start = index;
                }

				count++;
				found = true;
				continue;
			}

			if(c == closing) {
				count--;
				found = true;

				if(found && count == 0) {
					this.current = index + 1;
					return this.str.substring(start + 1, index);
				}
			}
		}

		return null;
	}

	/**
     * Peek the first non-white-space character available. The {@link #current}
     * pointer is not moved ahead.
     *
     * @return the first non-white-space character available to read next.
     */
	public char peekNextNonWhitespace() {
		int start = this.current;
		do {
			if(start >= this.length) {
				return 0;
			}

			char c = this.str.charAt(start);
			if(Character.isWhitespace(c)) {
				start++;
				continue;
			}

			return c;
		} while(true);
	}

	/**
     * Skip all whitespace characters that follow the {@link #current} reading
     * position.
     *
     * @return the number of characters skipped
     */
	public int skipWhiteSpace() {
	    int count = 0;
	    do {
	        if(this.current >= this.length) {
                return count;
            }

	        char c = this.str.charAt(this.current);
            if(Character.isWhitespace(c)) {
                this.current++;
                count++;
                continue;
            }

            return count;
	    } while(true);
	}

	/**
	 * Reset the reading position to the start of the actual string. This sets the
	 * {@link #current} pointer back to zero.
	 */
	public void reset() {
	    this.current = 0;
	}

}
