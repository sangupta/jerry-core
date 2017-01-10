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

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link IndentedStringWriter}.
 * 
 * @author sangupta
 *
 */
public class TestIndentedStringWriter {

	@Test
	public void testSimple() {
		IndentedStringWriter writer = new IndentedStringWriter();
		
		Assert.assertNotNull(writer.getString());
		Assert.assertEquals("", writer.getString());
		
		writer.write("hello");
		Assert.assertEquals("hello", writer.getString());
		writer.write(" world");
		Assert.assertEquals("hello world", writer.getString());
		writer.newLine();
		writer.write("hello");
		Assert.assertEquals("hello world\nhello", writer.getString());
		writer.writeLine(" world");
		Assert.assertEquals("hello world\nhello world\n", writer.getString());
	}
	
	@Test
	public void testIndentation() {
		IndentedStringWriter writer = new IndentedStringWriter();
		writer.setIndentLevel(1);
		
		Assert.assertNotNull(writer.getString());
		Assert.assertEquals("", writer.getString());
		
		writer.write("hello");
		Assert.assertEquals("    hello", writer.getString());
		
		writer.setIndentLevel(0);
		writer.write("world");
		Assert.assertEquals("    hello\nworld", writer.getString());
	}
	
	@Test
	public void testTabSpaces() {
		IndentedStringWriter writer = new IndentedStringWriter();
		writer.setIndentLevel(1);
		
		Assert.assertNotNull(writer.getString());
		Assert.assertEquals("", writer.getString());
		
		writer.write("hello");
		Assert.assertEquals("    hello", writer.getString());
		
		writer.setIndentLevel(2);
		writer.write("world");
		Assert.assertEquals("    hello\n        world", writer.getString());
		
		// change tab spaces to 2
		writer = new IndentedStringWriter(60, 1024, 2);
		writer.setIndentLevel(1);
		
		Assert.assertNotNull(writer.getString());
		Assert.assertEquals("", writer.getString());
		
		writer.write("hello");
		Assert.assertEquals("  hello", writer.getString());
		
		writer.setIndentLevel(2);
		writer.write("world");
		Assert.assertEquals("  hello\n    world", writer.getString());
		
		// change tab spaces to 0
		writer = new IndentedStringWriter(60, 1024, 0);
		writer.setIndentLevel(1);
		
		Assert.assertNotNull(writer.getString());
		Assert.assertEquals("", writer.getString());
		
		writer.write("hello");
		Assert.assertEquals("hello", writer.getString());
		
		writer.setIndentLevel(2);
		writer.write("world");
		Assert.assertEquals("hello\nworld", writer.getString());
	}
}
