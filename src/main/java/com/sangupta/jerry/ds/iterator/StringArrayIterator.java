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

package com.sangupta.jerry.ds.iterator;

import java.util.Iterator;

import net.jcip.annotations.NotThreadSafe;

/**
 * An iterator that works over a <code>String[]</code> (String array). The
 * iterator is safe even if the backing array provided is <code>null</code>.
 * 
 * @author sangupta
 *
 */
@NotThreadSafe
public class StringArrayIterator implements Iterator<String> {
    
	/**
	 * The backing array
	 */
    private final String[] items;
    
    /**
     * The current position in the array
     */
    private int position = 0;
    
    /**
	 * Construct an iterator over the given backing array.
	 * 
	 * @param items
	 *            the backing array to use. The array can be <code>null</code>.
	 * 
	 */
    public StringArrayIterator(String[] items) {
        this.items = items;
    }
    
    /**
     * Get the current position in the backing string array.
     * 
     * @return the index position in the backing array
     * 
     */
    public int getPosition() {
    	return this.position;
    }
    
    @Override
    public void remove() {
    	throw new UnsupportedOperationException("Remove is not supported on StringArray iteration");
    }

    @Override
    public boolean hasNext() {
    	if(items == null) {
    		return false;
    	}
    	
        if(position < items.length) {
            return true;
        }
        
        return false;
    }

    /**
     * Get the next element from the array.
     * 
     */
    @Override
    public String next() {
        return this.items[this.position++];
    }
    
    /**
	 * Peek the next element without removing it.
	 * 
	 * @return the next element if available, or <code>null</code>.
	 * 
	 */
    public String peek() {
        if(!this.hasNext()) {
            return null;
        }
        
        return this.items[this.position];
    }
    
}
