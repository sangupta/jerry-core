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
        Assert.assertNull(reader.readBefore('x'));
        Assert.assertNull(reader.readBefore("yz"));
        Assert.assertNull(reader.readBetween('a', 'z'));
        Assert.assertNull(reader.readBefore('h', 5));
        Assert.assertNull(reader.readBefore("yz", 5));
    }

    @Test
    public void testEmptyString() {
        AdvancedStringReader reader = new AdvancedStringReader(StringUtils.EMPTY_STRING);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readRemaining());

        reader = new AdvancedStringReader(StringUtils.EMPTY_STRING);
        Assert.assertFalse(reader.hasNext());
        Assert.assertNull(reader.readBefore('x'));
        Assert.assertNull(reader.readBefore("yz"));
        Assert.assertNull(reader.readBetween('a', 'z'));
        Assert.assertNull(reader.readBefore('h', 5));
        Assert.assertNull(reader.readBefore("yz", 5));
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
        Assert.assertEquals("hello world", reader.readBefore(':'));
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
        Assert.assertEquals(5, reader.skip(5));
        reader.skipWhiteSpace();
        Assert.assertEquals(3, reader.skip(3));
        Assert.assertEquals(2, reader.skip(3));
        Assert.assertEquals(0, reader.skip(3));
    }
    
    @Test
    public void testReadAfter() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals("orld", reader.readAfter('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("orld, its my world", reader.readAfter('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("hello world", reader.readBefore(','));
        Assert.assertEquals("y world", reader.readAfter('m'));
    }
    
    @Test
    public void testReadFrom() {
        AdvancedStringReader reader = new AdvancedStringReader("hello world");
        Assert.assertEquals("world", reader.readFrom('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("world, its my world", reader.readFrom('w'));
        
        reader = new AdvancedStringReader("hello world, its my world");
        Assert.assertEquals("hello world", reader.readBefore(','));
        Assert.assertEquals("my world", reader.readFrom('m'));
    }
    
    @Test
    public void testReadTillPosition() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals("hell", reader.readNext(4));
    	Assert.assertEquals("o", reader.readNext(1));
    }
    
    @Test
    public void testPeekIndex() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals(2, reader.peekCharIndex('l'));
    	Assert.assertEquals(6, reader.peekCharIndex('w'));
    }
    
    @Test
    public void testReadTillNext() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello ", reader.readBefore("world"));
    	Assert.assertEquals(". this is a world, just another ", reader.readBefore("world", 2));
    	
    	reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello world. this is a ", reader.readBefore("world", 2));
    	
    	reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello ", reader.readBefore("world"));
    	Assert.assertEquals(". this is a world, just another world.", reader.readBefore("sangupta", 4));
    }
    
    @Test
    public void testRemaining() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals("hello world. this is a world, just another ", reader.readBefore("world", 3));
    	Assert.assertEquals(".", reader.readRemaining());
    }
    
    @Test
    public void testPeekNext() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	
    	Assert.assertEquals("h", reader.peekString(1));
    	Assert.assertEquals("hell", reader.peekString(4));
    	Assert.assertEquals("hello world. this is a world, just another ", reader.readBefore("world", 3));
    	Assert.assertEquals(".", reader.peekString(1));
    	Assert.assertEquals(".", reader.peekString(2));
    	Assert.assertEquals(".", reader.readRemaining());
    }
    
    @Test
    public void testSkip() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	reader.skip(4);
    	Assert.assertEquals('o', reader.peekAhead());
    	reader.skip(3);
    	Assert.assertEquals('o', reader.peekAhead());
    }
    
    @Test
    public void testReadBetweenCharacters() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world");
    	Assert.assertEquals(" w", reader.readBetween('o', 'o')); // both exist
    	reader.reset();
    	Assert.assertEquals("ello world", reader.readBetween('h', 'x')); // end does not exist
    	reader.reset();
    	Assert.assertNull(reader.readBetween('x', 'o')); // starting does not exist
    	reader.reset();
    	Assert.assertEquals("", reader.readBetween('l', 'l'));
    	Assert.assertEquals(" w", reader.readBetween('o', 'o'));
    	Assert.assertEquals("", reader.readBetween('l', 'd'));
    }
    
    @Test
    public void testReadBetweenString() {
    	AdvancedStringReader reader = new AdvancedStringReader("hello world. this is a world, just another world.");
    	Assert.assertEquals(". this is a ", reader.readBetween("world", "world")); // both exist
    	reader.reset();
    	Assert.assertEquals(" another world.", reader.readBetween("just", "many")); // end does not exist
    	reader.reset();
    	Assert.assertNull(reader.readBetween("many", "world")); // starting does not exist
    	reader.reset();
    	Assert.assertEquals("", reader.readBetween('l', 'l'));
    	Assert.assertEquals(" w", reader.readBetween('o', 'o'));
    	Assert.assertEquals("", reader.readBetween('l', 'd'));
    	Assert.assertEquals("this", reader.readBetween(" ", " "));
    }
}
