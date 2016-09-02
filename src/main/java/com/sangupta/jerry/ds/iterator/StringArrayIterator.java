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

public class StringArrayIterator implements Iterator<String> {
    
    private final String[] items;
    
    private int position = 0;
    
    public StringArrayIterator(String[] items) {
        this.items = items;
    }
    
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

    @Override
    public String next() {
        return this.items[this.position++];
    }
    
    public String peek() {
        if(!this.hasNext()) {
            return null;
        }
        
        return this.items[this.position];
    }
    
}
