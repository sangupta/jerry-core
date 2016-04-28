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
}
