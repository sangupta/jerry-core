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

import org.junit.Test;

/**
 * Unit tests for {@link ConsoleTable}.
 * 
 * @author sangupta
 *
 */
public class TestConsoleTable {
	
	@Test
	public void testTableJson() {
		ConsoleTable table = new ConsoleTable();
		table.addHeaderRow("name", "email");
		table.addRow("user1", "user1@somedummydomain.com");
		table.addRow("user2", "user2@somedummydomain.com");
		table.addRow("user3", "user3@somedummydomain.com");
		
		ConsoleTableWriter.writeCsv(table, System.out);
	}

}
