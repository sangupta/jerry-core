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
 
package com.sangupta.jerry.print;

import java.util.ArrayList;
import java.util.List;

import com.sangupta.jerry.util.AssertUtils;

/**
 * A row of data inside the {@link ConsoleTable}.
 * 
 * @author sangupta
 *
 */
public class ConsoleTableRow {
	
	private final List<String> columns = new ArrayList<String>();

	/**
	 * Default constructor
	 */
	public ConsoleTableRow() {
		
	}
	
	public ConsoleTableRow(String[] columnValues) {
		if(AssertUtils.isEmpty(columnValues)) {
			throw new IllegalArgumentException("Column values cannot be empty/null");
		}
		
		for(String columnValue : columnValues) {
			this.columns.add(columnValue);
		}
	}

	public ConsoleTableRow(Object[] columnValues) {
		if(AssertUtils.isEmpty(columnValues)) {
			throw new IllegalArgumentException("Column values cannot be empty/null");
		}
		
		for(Object object : columnValues) {
			if(object instanceof String) {
				this.columns.add(((String) object));
			} else {
				this.columns.add(object.toString());
			}
		}
	}

	public ConsoleTableRow addColumn(String value) {
		this.columns.add(value);
		return this;
	}
	
	public int numColumns() {
		return this.columns.size();
	}
	
	public int columnSize(int index) {
		String value = column(index);
		if(value == null) {
			return 0;
		}
		
		return value.length();
	}
	
	public String column(int index) {
		return this.columns.get(index);
	}
	
	// Usual accessors follow

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}
}
