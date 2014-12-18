package com.sangupta.jerry.print;

import org.junit.Test;

public class ConsoleTableTest {
	
	@Test
	public void testTableJson() {
		ConsoleTable table = new ConsoleTable();
		table.addHeaderRow("name", "email");
		table.addRow("user1", "user1@somedummydomain.com");
		table.addRow("user2", "user2@somedummydomain.com");
		table.addRow("user3", "user3@somedummydomain.com");
		
		ConsoleTableWriter.writeXml(table, System.out, "items", "row");
	}

}
