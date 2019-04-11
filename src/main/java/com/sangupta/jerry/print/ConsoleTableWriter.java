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

package com.sangupta.jerry.print;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.WriterOutputStream;

import com.sangupta.jerry.util.StringUtils;

/**
 * A simple writer that converts {@link ConsoleTable} into various other data
 * formats.
 *
 * @author sangupta
 *
 */
public class ConsoleTableWriter {

	/**
	 * Output the data of the table as a JSON
	 *
	 * @param table  the {@link ConsoleTable} to output
	 *
	 * @param writer the {@link PrintWriter} to write to
	 */
	public void writeJson(ConsoleTable table, PrintWriter writer) {
		this.writeJson(table, writer, Charset.defaultCharset());
	}

	/**
	 * Output the data of the table as a JSON
	 *
	 * @param table  the {@link ConsoleTable} to output
	 *
	 * @param writer the {@link PrintWriter} to write to
	 * 
	 * @param charset the {@link Charset} to use
	 * 
	 * @since 3.1.0
	 */
	public void writeJson(ConsoleTable table, PrintWriter writer, Charset charset) {
		OutputStream os = new WriterOutputStream(writer, charset);
		PrintStream ps = new PrintStream(os);
		try {
			writeJson(table, ps);
		} finally {
			ps.close();
		}
	}

	/**
	 * Output the data of the table as a JSON
	 *
	 * @param table the {@link ConsoleTable} to output
	 *
	 * @param out   the {@link PrintStream} to write to
	 */
	public static void writeJson(ConsoleTable table, PrintStream out) {
		if (table == null) {
			throw new IllegalArgumentException("ConsoleTable cannot be null");
		}

		if (out == null) {
			throw new IllegalArgumentException("PrintStream to write to cannot be null");
		}

		if (table.headerRow == null) {
			throw new IllegalStateException("Header row must be present for conversion to JSON");
		}

		List<String> names = new ArrayList<String>();
		for (int index = 0; index < table.headerRow.numColumns(); index++) {
			String name = table.headerRow.column(index);

			name = StringUtils.convertToJsonPropertyName(name);
			names.add(name);
		}

		// now start iterating over each row
		out.print("[\n");
		for (int rowIndex = 0; rowIndex < table.rows.size(); rowIndex++) {
			ConsoleTableRow row = table.rows.get(rowIndex);

			if (rowIndex > 0) {
				out.print(", ");
			}
			out.print("{\n");
			for (int index = 0; index < table.headerRow.numColumns(); index++) {
				if (index > 0) {
					out.print(", ");
				}
				out.print("\"");
				out.print(names.get(index));
				out.print("\" : \"");
				out.print(row.column(index));
				out.print("\"\n");
			}
			out.print("}\n");
		}
		out.print("]");
	}

	/**
	 * Output the data as an XML.
	 *
	 * @param table        the {@link ConsoleTable} to output
	 *
	 * @param writer       the {@link PrintStream} to write to
	 *
	 * @param parentXmlTag the uber XML tag to wrap items into
	 *
	 * @param rowTag       the tag to use for wrapping each row of data
	 */
	public void writeXml(ConsoleTable table, PrintWriter writer, String parentXmlTag, String rowTag) {
		this.writeXml(table, writer, Charset.defaultCharset(), parentXmlTag, rowTag);
	}

	/**
	 * Output the data as an XML.
	 *
	 * @param table        the {@link ConsoleTable} to output
	 *
	 * @param writer       the {@link PrintStream} to write to
	 *
	 * @param parentXmlTag the uber XML tag to wrap items into
	 *
	 * @param rowTag       the tag to use for wrapping each row of data
	 * 
	 * @since 3.1.0
	 */
	public void writeXml(ConsoleTable table, PrintWriter writer, Charset charset, String parentXmlTag, String rowTag) {
		OutputStream os = new WriterOutputStream(writer, charset);
		PrintStream ps = new PrintStream(os);
		try {
			writeJson(table, ps);
		} finally {
			ps.close();
		}
	}

	/**
	 * Output the data as an XML.
	 *
	 * @param table        the {@link ConsoleTable} to output
	 *
	 * @param out          the {@link PrintWriter} to write to
	 *
	 * @param parentXmlTag the uber XML tag to wrap items into
	 *
	 * @param rowTag       the tag to use for wrapping each row of data
	 */
	public static void writeXml(ConsoleTable table, PrintStream out, String parentXmlTag, String rowTag) {
		if (table == null) {
			throw new IllegalArgumentException("ConsoleTable cannot be null");
		}

		if (out == null) {
			throw new IllegalArgumentException("PrintStream to write to cannot be null");
		}

		if (table.headerRow == null) {
			throw new IllegalStateException("Header row must be present for conversion to XML");
		}

		List<String> names = new ArrayList<String>();
		for (int index = 0; index < table.headerRow.numColumns(); index++) {
			String name = table.headerRow.column(index);

			name = StringUtils.convertToJsonPropertyName(name);
			names.add(name);
		}

		// now start itearting over each row
		out.print("<" + parentXmlTag + ">\n");
		for (int rowIndex = 0; rowIndex < table.rows.size(); rowIndex++) {
			ConsoleTableRow row = table.rows.get(rowIndex);
			out.print("<" + rowTag + ">\n");
			for (int index = 0; index < table.headerRow.numColumns(); index++) {
				out.print("<");
				out.print(names.get(index));
				out.print(">");

				out.print(row.column(index));

				out.print("</");
				out.print(names.get(index));
				out.print(">\n");

			}
			out.print("</" + rowTag + ">\n");
		}
		out.print("</" + parentXmlTag + ">\n");
	}

	/**
	 * Output the data of the table as a CSV
	 *
	 * @param table  the {@link ConsoleTable} to output
	 *
	 * @param writer the {@link PrintWriter} to write to
	 */
	public void writeCsv(ConsoleTable table, PrintWriter writer) {
		this.writeCsv(table, writer, Charset.defaultCharset());
	}

	/**
	 * Output the data of the table as a CSV
	 *
	 * @param table   the {@link ConsoleTable} to output
	 *
	 * @param writer  the {@link PrintWriter} to write to
	 * 
	 * @param charset the {@link Charset} to use
	 * 
	 * @since 3.1.0
	 */
	public void writeCsv(ConsoleTable table, PrintWriter writer, Charset charset) {
		OutputStream os = new WriterOutputStream(writer, charset);
		PrintStream ps = new PrintStream(os);
		try {
			writeCsv(table, ps);
		} finally {
			ps.close();
		}
	}

	/**
	 * Output the data of the table as a CSV
	 *
	 * @param table the {@link ConsoleTable} to output
	 *
	 * @param out   the {@link PrintStream} to write to
	 */
	public static void writeCsv(ConsoleTable table, PrintStream out) {
		if (table == null) {
			throw new IllegalArgumentException("ConsoleTable cannot be null");
		}

		if (out == null) {
			throw new IllegalArgumentException("PrintStream to write to cannot be null");
		}

		final int columns = table.headerRow.numColumns();
		if (table.headerRow != null) {
			for (int index = 0; index < columns; index++) {
				if (index > 0) {
					out.print(",");
				}
				out.print(table.headerRow.column(index));
			}
			out.print("\n");
		}

		// each row
		for (ConsoleTableRow row : table.rows) {
			for (int index = 0; index < columns; index++) {
				if (index > 0) {
					out.print(",");
				}
				out.print(row.column(index));
			}
			out.print("\n");
		}
	}
}
