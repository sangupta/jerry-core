/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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

		for(int index = 0; index < 20; index++) {
			table.addRow("user" + index, "user" + index + "@somedummydomain.com");
		}

		// no pagination
		table.write(System.out);

		System.out.println("\n\n\n");

		// pagination at 10
		table.write(System.out, 10);

		System.out.println("\n\n\n");

		// pagination at 10 with break line
		table.write(System.out, 10, new ConsoleTablePaginationBreakHandler() {

			@Override
			public boolean continuePagination(ConsoleTable table) {
				System.out.println("Another table break");
				return true;
			}
		});
	}

}
