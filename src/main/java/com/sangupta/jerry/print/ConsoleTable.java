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
	 * Layout options for the table
	 * 
	 * @author sangupta
	 *
	 */
	public static enum ConsoleTableLayout {
		
		/**
		 * Default option - run for the full line width
		 * 
		 */
		FULL_WIDTH,
		
		/**
		 * Strip the line when max size specified by user is encountered
		 * 
		 */
		STRIPPED,
		
		/**
		 * Convert the line to multi-line output
		 * 
		 */
		MULTI_LINE;
		
	}

	/**
	 * The current layout for the 
	 */
	private ConsoleTableLayout layout;
	
	/**
	 * The header row if specified
	 */
	private ConsoleTableRow headerRow;
	
	/**
	 * String used to divide two columns of information
	 */
	private String columnSeparator = " | ";
	
	/**
	 * All rows inside the table
	 */
	private final List<ConsoleTableRow> rows = new ArrayList<ConsoleTableRow>();
	
	/**
	 * The column size of added rows including header
	 */
	private final List<MutableInt> columnSizes = new ArrayList<MutableInt>();
	
	/**
	 * Holds column size as provided by user
	 */
	private final List<MutableInt> userSizes = new ArrayList<MutableInt>();
	
	/**
	 * Default constructor
	 */
	public ConsoleTable() {
		this.layout = ConsoleTableLayout.FULL_WIDTH;
	}
	
	/**
	 * Convenience constructor
	 * 
	 * @param layout
	 */
	public ConsoleTable(ConsoleTableLayout layout) {
		if(layout == null) {
			throw new IllegalArgumentException("Table layout cannot be null");
		}
		
		this.layout = layout;
	}

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
		// update column sizes
		if(this.headerRow != null) {
			updateColumnSizes(this.headerRow);
		}
		for(ConsoleTableRow row : this.rows) {
			updateColumnSizes(row);
		}
		
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
			out.print(this.columnSeparator); // prepend every table cell with a space as a separator
			
			final String column = row.column(index);
			final int colSize = getMaxColSize(index);
			final int size = column.length();
			final int delta = colSize - size;
			
			switch(this.layout) {
				case FULL_WIDTH:
					out.print(column);;
					if(delta > 0) {
						out.print(StringUtils.repeat(' ', delta));
					}
					
					break;

				case MULTI_LINE:
					break;
				
				case STRIPPED:
					if(delta == 0) {
						out.print(column);
					} else {
						if(delta < 0) {
							out.print(column.substring(0, colSize));
						} else {
							out.print(column);;
							out.print(StringUtils.repeat(' ', delta));
						}
					}
					break;
				
				default:
					throw new IllegalStateException("Layout has not yet been implemented");
			}
		}
		out.println();
	}

	private int getMaxColSize(int index) {
		int colSize = this.columnSizes.get(index).get();
		int userSize = 0;
		
		if(index < this.userSizes.size()) {
			userSize = this.userSizes.get(index).get();
		}
		
		if(userSize > 0) {
			return userSize;
		}
		
		return colSize;
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
	
	/**
	 * Return the number of columns inside the table
	 * 
	 * @return
	 */
	public int numColumns() {
		return this.columnSizes.size();
	}
	
	/**
	 * Return the column size for the column.
	 * 
	 * @param index
	 * @return
	 */
	public int getColumnSize(int index) {
		return this.columnSizes.get(index).get();
	}
	
	/**
	 * Change the column separator to be used.
	 * 
	 * @param separator
	 */
	public void setColumnSeparator(String separator) {
		if(AssertUtils.isEmpty(separator)) {
			throw new IllegalArgumentException("Column separator cannot be null/empty");
		}
		
		this.columnSeparator = separator;
	}
	
	/**
	 * 
	 * @param index
	 * @param size
	 */
	public void setColumnSize(final int index, final int size) {
		while(this.userSizes.size() <= index) {
			this.userSizes.add(new MutableInt());
		}
		
		this.userSizes.get(index).set(size);
	}
	
	// Usual accessors follow

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(ConsoleTableLayout layout) {
		if(layout == null) {
			throw new IllegalArgumentException("Table layout cannot be null");
		}
		
		this.layout = layout;
	}

}
