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

import com.sangupta.jerry.util.StringUtils;

/**
 * Unit-tests for {@link AdvancedStringReader}.
 *
 * @author sangupta
 *
 */
public class TestAdvancedStringReader {

    @Test
    public void testNullString() {
        AdvancedStringReader reader = new AdvancedStringReader(null);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readRemaining());

        reader = new AdvancedStringReader(null);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readTillNext('x'));
        Assert.assertNull(reader.readTillNext("yz"));
        Assert.assertNull(reader.readBetween('a', 'z'));
        Assert.assertNull(reader.readTillNext('h', 5));
        Assert.assertNull(reader.readTillNext("yz", 5));
    }

    @Test
    public void testEmptyString() {
        AdvancedStringReader reader = new AdvancedStringReader(StringUtils.EMPTY_STRING);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readRemaining());

        reader = new AdvancedStringReader(StringUtils.EMPTY_STRING);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readTillNext('x'));
        Assert.assertNull(reader.readTillNext("yz"));
        Assert.assertNull(reader.readBetween('a', 'z'));
        Assert.assertNull(reader.readTillNext('h', 5));
        Assert.assertNull(reader.readTillNext("yz", 5));
    }

    @Test
    public void testReset() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals("he", reader.readNext(2));
        reader.reset();
        Assert.assertEquals("hell", reader.readNext(4));
        reader.reset();
        Assert.assertEquals("hello", reader.readNext(5));
    }

    @Test
    public void testSkipWhiteSpace() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world:      this is prefixed by spaces");
        reader.readTillNext(':');
        Assert.assertEquals(6, reader.skipWhiteSpace());
        reader.readNext(4);
        Assert.assertEquals(1, reader.skipWhiteSpace());
        reader.readNext(3);
        Assert.assertEquals(0, reader.skipWhiteSpace());
    }

    @Test
    public void testPeekNextNonWhitespace() {
        AdvancedStringReader reader = new AdvancedStringReader("    hello world:      this is prefixed by spaces");
        Assert.assertEquals('h', reader.peekNextNonWhitespace());
    }
    
    @Test
    public void testPeekAhead() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals('h', reader.peekAhead());
    	Assert.assertEquals('e', reader.peekAhead(1));
    	Assert.assertEquals('l', reader.peekAhead(2));
    }

    @Test
    public void testSkipNext() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals(5, reader.skipNext(5));
        reader.skipWhiteSpace();
        Assert.assertEquals(3, reader.skipNext(3));
        Assert.assertEquals(2, reader.skipNext(3));
        Assert.assertEquals(0, reader.skipNext(3));
    }
    
    @Test
    public void testReadAfter() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals("orld", reader.readAfter('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("orld, its my world", reader.readAfter('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("hello world", reader.readTillNext(','));
        Assert.assertEquals("y world", reader.readAfter('m'));
    }
    
    @Test
    public void testReadFrom() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals("world", reader.readFrom('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("world, its my world", reader.readFrom('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("hello world", reader.readTillNext(','));
        Assert.assertEquals("my world", reader.readFrom('m'));
    }
    
    @Test
    public void testReadTillPosition() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals("hell", reader.readTillPosition(4));
    	Assert.assertEquals("o", reader.readTillPosition(1));
    }
    
    @Test
    public void testPeekIndex() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals(2, reader.peekIndex('l'));
    	Assert.assertEquals(6, reader.peekIndex('w'));
    }
    
    @Test
    public void testReadTillNext() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello ", reader.readTillNext("world"));
    	Assert.assertEquals(". this is a world, just another ", reader.readTillNext("world", 2));
    	
    	reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello world. this is a ", reader.readTillNext("world", 2));
    	
    	reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello ", reader.readTillNext("world"));
    	Assert.assertEquals(". this is a world, just another world.", reader.readTillNext("sangupta", 4));
    }
    
    @Test
    public void testRemaining() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello world. this is a world, just another ", reader.readTillNext("world", 3));
    	Assert.assertEquals(".", reader.readRemaining());
    }
    
    @Test
    public void testPeekNext() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	
    	Assert.assertEquals("h", reader.peekNext(1));
    	Assert.assertEquals("hell", reader.peekNext(4));
    	Assert.assertEquals("hello world. this is a world, just another ", reader.readTillNext("world", 3));
    	Assert.assertEquals(".", reader.peekNext(1));
    	Assert.assertEquals(".", reader.peekNext(2));
    	Assert.assertEquals(".", reader.readRemaining());
    }
}
