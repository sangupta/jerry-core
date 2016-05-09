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
        AdvancedStringReader reader = new AdvancedStringReader("hello world:      this is prefixed by spaces");
        Assert.assertEquals('h', reader.peekNextNonWhitespace());
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
}
