/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
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

import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * This class helps us write an indented string that breaks at a given length
 * without the callee being concerned over the line length. The indentation
 * level as well as number of characters to use for a tab are configurable.
 * 
 * An initial space of 10240 characters is assigned as initial capacity (can be
 * overridden). The writer can resize depending as we add more strings to it.
 *
 * Usage:
 * <pre>
 * IndentedStringWriter writer = new IndentedStringWriter();
 * 
 * writer.write("hello world");
 * 
 * writer.incrementIndent();
 * writer.write("there are 4 spaces to the left of this line");
 * 
 * writer.incrementIndent();
 * writer.write("there are 8 spaces to the left of this line");
 * 
 * writer.decrementIndent();
 * writer.write("there are again 4 spaces to the left of this line");
 * 
 * writer.decrementIndent();
 * writer.write("there is no space to the left of this line");
 * </pre>
 * 
 * @author sangupta
 * 
 */
public class IndentedStringWriter {

    private final StringBuilder builder; // initialize with 10kb of character space

    /**
     * The line length to break at
     */
    private final int lineLength;

    /**
     * Replace tabs to 4 spaces
     */
    private final int tabLength;

    /**
     * The current indentation level
     */
    private int indentLevel = 0;

    /**
     * The current pointer in the line - specifies how many number of characters
     * have already been written to the line.
     */
    private int currentPointer = 0;

    public IndentedStringWriter() {
        this(100); // default line length is 60 chars
    }

    public IndentedStringWriter(int lineLength) {
        this(lineLength, 10 * 1024, 4);
    }

    /**
     * 
     * @param lineLength
     * @param initialCapacity
     * @param tabLength
     */
    public IndentedStringWriter(int lineLength, int initialCapacity, int tabLength) {
        this.lineLength = lineLength;
        this.tabLength = tabLength;
        this.builder = new StringBuilder(initialCapacity);
    }

    public boolean hasContent() {
        return this.builder.length() > 0;
    }

    /**
     * Set the indent level to the given value. If the value is less than zero, an
     * {@link IllegalArgumentException} is thrown.
     * 
     * @param indentLevel the level to be seto
     */
    public void setIndentLevel(int indentLevel) {
        if (indentLevel < 0) {
            throw new IllegalArgumentException("Indent level cannot be less than zero");
        }

        this.indentLevel = indentLevel;

        if (this.builder.length() == 0) {
            return;
        }

        if (this.currentPointer > 0) {
            this.newLine();
        }
    }

    /**
     * Increment the indent by one.
     * 
     */
    public void incrementIndent() {
        this.indentLevel++;

        if (this.currentPointer > 0) {
            this.newLine();
        }
    }

    /**
     * Decrement the indent by one. If the indentation level is already at zero,
     * this method will not change anything.
     * 
     */
    public void decrementIndent() {
        if (this.indentLevel == 0) {
            return;
        }

        this.indentLevel--;

        if (this.currentPointer > 0) {
            this.newLine();
        }
    }

    /**
     * Write the given character to the underlying writer.
     * 
     * @param ch the character to be written.
     * 
     */
    public void write(char ch) {
        this.write(String.valueOf(ch));
    }

    /**
     * Write the given string fragment to current line.
     * 
     * @param str the string to be written
     */
    public void write(String str) {
        if (AssertUtils.isEmpty(str)) {
            return;
        }

        String[] tokens = str.split("\n");
        for (int index = 0; index < tokens.length; index++) {
            if (index > 1) {
                this.newLine();
            }

            this.writeInternal(tokens[index]);
        }
    }

    /**
     * Write the given string values without breaking the strings unless there is no
     * other way.
     * 
     * @param strings the {@link String} values to be written
     * 
     */
    public void writeNonBreaking(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                builder.append(str);
            }
        }

        this.write(builder.toString());
    }

    private void writeInternal(String str) {
        if (AssertUtils.isEmpty(str)) {
            return;
        }

        // add indentation if needed
        this.addIndentation();

        // compute breakpoint
        int len = str.length();
        boolean breakNeeded = this.currentPointer + str.length() > this.lineLength;

        // no break we can write directly
        if (!breakNeeded) {
            this.builder.append(str);
            this.currentPointer += len;
            return;
        }

        // add the prefix - whatever we can achieve in the remaining space
        int breakPoint = this.lineLength - this.currentPointer;

        // check if break point is not breaking a word
        boolean breakPointUpdated = false;
        for (int index = breakPoint; index > 0; index--) {
            if (Character.isWhitespace(str.charAt(index))) {
                breakPoint = index;
                breakPointUpdated = true;
                break;
            }
        }

        if (!breakPointUpdated) {
            if (str.length() < (this.lineLength - this.indentLevel * this.tabLength)) {
                this.newLine();
                this.writeInternal(StringUtils.ltrim(str));
                return;
            }
        }

        // add the prefix
        String prefix = str.substring(0, breakPoint);
        this.builder.append(prefix);
        this.newLine();

        // call recursive
        if (breakPoint == 0) {
            this.builder.append(str);
            this.currentPointer = str.length();
            return;
        }

        this.write(StringUtils.ltrim(str.substring(breakPoint)));
    }

    /**
     * Write the given string fragment to current line and start a new line at the
     * end.
     * 
     * @param str the {@link String} to be written followed by a new line
     */
    public void writeLine(String str) {
        this.write(str);
        this.newLine();
    }

    /**
     * Start a new indented line.
     * 
     */
    public void newLine() {
        this.builder.append('\n');
        this.currentPointer = 0;
    }

    private void addIndentation() {
        if (this.indentLevel <= 0 || this.currentPointer > 0) {
            // no indentation needed
            return;
        }

        int chars = this.indentLevel * this.tabLength;
        if (chars == 0) {
            return;
        }

        this.builder.append(StringUtils.repeat(' ', chars));
        this.currentPointer = chars;
    }

    /**
     * Return the current {@link String} representation.
     * 
     * @return the {@link String} value thus far written to this writer
     * 
     */
    public String getString() {
        return this.builder.toString();
    }

    @Override
    public String toString() {
        return this.getString();
    }
}
