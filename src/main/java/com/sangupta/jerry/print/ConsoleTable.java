/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.sangupta.jerry.ds.MutableInt;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * A simple table that allows to display data in a table style
 * usually on consoles.
 * 
 * @author sangupta
 *
 */
public class ConsoleTable {
	
	/**
	 * The header row if specified
	 */
	private ConsoleTableRow headerRow;
	
	/**
	 * All rows inside the table
	 */
	private final List<ConsoleTableRow> rows = new ArrayList<ConsoleTableRow>();
	
	/**
	 * The column size of added rows including header
	 */
	private final List<MutableInt> columnSizes = new ArrayList<MutableInt>();

	/**
	 * Add a header row to the table
	 * 
	 * @param columnNames
	 */
	public ConsoleTableRow addHeaderRow(String... columnNames) {
		if(this.headerRow != null) {
			throw new IllegalStateException("Table already has a header row");
		}
		
		this.headerRow = new ConsoleTableRow(columnNames);
		
		updateColumnSizes(this.headerRow);
		return this.headerRow;
	}

	/**
	 * Add a row to the table
	 * 
	 * @param objects
	 */
	public ConsoleTableRow addRow(Object... objects) {
		if(AssertUtils.isEmpty(objects)) {
			throw new IllegalArgumentException("Nothing to add for row");
		}
		
		ConsoleTableRow row = new ConsoleTableRow(objects);
		this.rows.add(row);
		
		updateColumnSizes(row);
		return row;
	}

	/**
	 * Write the table to a {@link PrintStream}.
	 * 
	 * @param out
	 */
	public void write(PrintStream out) {
		// output header row
		if(this.headerRow != null) {
			this.displayRow(out, this.headerRow);
			
			ConsoleTableRow separator = new ConsoleTableRow();
			for(int index = 0; index < this.columnSizes.size(); index++) {
				separator.addColumn(StringUtils.repeat('-', this.columnSizes.get(index).get()));
			}
			this.displayRow(out, separator);
		}
		
		for(ConsoleTableRow row : this.rows) {
			this.displayRow(out, row);
		}
	}
	
	/**
	 * Display one row of information
	 *  
	 * @param out
	 * @param row
	 */
	private void displayRow(PrintStream out, ConsoleTableRow row) {
		for(int index = 0; index < row.getColumns().size(); index++) {
			String column = row.column(index);
			
			out.print(' '); // prepend every table cell with a space as a separator
			out.print(column);;
			int size = column.length();
			int delta = this.columnSizes.get(index).get() - size;
			
			out.print(StringUtils.repeat(' ', delta));
		}
		out.println();
	}

	/**
	 * Update the max column sizes for the just added row
	 * 
	 * @param row
	 */
	private void updateColumnSizes(ConsoleTableRow row) {
		for(int index = 0; index < row.numColumns(); index++) {
			int size = row.columnSize(index);
			if(index == this.columnSizes.size()) {
				this.columnSizes.add(new MutableInt(size));
			} else {
				this.columnSizes.get(index).setIfMax(size);
			}
		}
	}

}
