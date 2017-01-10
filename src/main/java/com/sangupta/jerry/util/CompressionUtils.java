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


package com.sangupta.jerry.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility function to compress given data into a byte-array and vice-versa.
 * Useful for situation where data needs to be stored in database etc. The default
 * compression happens using <code>DEFLATE</code> algorithm.
 *
 * @author sangupta
 *
 */
public abstract class CompressionUtils {

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompressionUtils.class);

	/**
	 * Compress the given string data into byte-array. The method is
	 * <code>null</code> safe. The incoming string is translated to bytes
	 * using the platform-encoding.
	 *
	 * @param string
	 *            the string to be compressed
	 *
	 * @return the bytes of the compressed representation, or <code>null</code>
	 *         if incoming string is null.
	 */
	public static byte[] compress(String string) {
		if(string == null) {
			return null;
		}

		return compress(string.getBytes(StringUtils.DEFAULT_CHARSET));
	}

	/**
	 * Compress the given string data into byte-array. The method is
	 * <code>null</code> safe. The incoming string is translated to bytes using
	 * the given charset-name.
	 *
	 * @param string
	 *            the string to be compressed
	 *
	 * @param charsetName
	 *            the character set to use for converting string to byte-array
	 *
	 * @return the bytes of the compressed representation, or <code>null</code>
	 *         if incoming string is null.
	 *
	 * @throws UnsupportedEncodingException
	 *             if the incoming string cannot be converted into bytes using
	 *             the given charset name.
	 */
	public static byte[] compress(String string, String charsetName) throws UnsupportedEncodingException {
		if(string == null) {
			return null;
		}

		return compress(string.getBytes(charsetName));
	}

	/**
	 * Compress the given string data into byte-array. The method is
	 * <code>null</code> safe. The incoming string is translated to bytes using
	 * the given {@link Charset}.
	 *
	 * @param string
	 *            the string to be compressed
	 *
	 * @param charset
	 *            the character set to use for converting string to byte-array
	 *
	 * @return the bytes of the compressed representation, or <code>null</code>
	 *         if incoming string is null.
	 *
	 * @throws UnsupportedEncodingException
	 *             if the incoming string cannot be converted into bytes using
	 *             the given {@link Charset}.
	 */
	public static byte[] compress(String string, Charset charset) throws UnsupportedEncodingException {
		if(string == null) {
			return null;
		}

		return compress(string.getBytes(charset));
	}

	/**
	 * Compress the given bytes using DEFLATE algorithm. The method is
	 * <code>null</code> safe.
	 *
	 * @param inputBytes
	 *            the bytes to be compressed
	 *
	 * @return the bytes of the compressed representation, or <code>null</code>
	 *         if incoming bytes is null.
	 */
	public static byte[] compress(byte[] inputBytes) {
		if(AssertUtils.isEmpty(inputBytes)) {
			return null;
		}

		byte[] output = new byte[inputBytes.length];

		Deflater deflater = new Deflater(Deflater.DEFLATED);
		deflater.setInput(inputBytes);
		deflater.finish();

		int compressedDataLength = deflater.deflate(output);

		deflater.end();

		if(compressedDataLength != 0) {
			byte[] finalOutput = new byte[compressedDataLength];
			System.arraycopy(output, 0, finalOutput, 0, compressedDataLength);
			return finalOutput;
		}

		return null;
	}

	/**
	 * Uncompress the given input byte-array assuming that the max uncompressed
	 * size is not known. The method will try and allocate a buffer of 3 times
	 * the size of input array. If the uncompressed size is known, use the
	 * method {@link CompressionUtils#uncompress(byte[], int)}.
	 *
	 * @param inputBytes
	 *            the bytes to uncompress
	 *
	 * @return the uncompressed byte stream
	 *
	 */
	public static byte[] uncompress(byte[] inputBytes) {
		if(inputBytes == null) {
			return null;
		}

		return uncompress(inputBytes, inputBytes.length * 3);
	}

	/**
	 * Uncompress the byte-array when the uncompressed size is known.
	 *
	 * @param inputBytes
	 *            the compressed bytes
	 *
	 * @param uncompressedSize
	 *            the uncompressed size of the stream
	 *
	 * @return the uncompressed byte stream
	 *
	 */
	public static byte[] uncompress(byte[] inputBytes, int uncompressedSize) {
		if(AssertUtils.isEmpty(inputBytes)) {
			return null;
		}

		Inflater inflater = new Inflater();
		inflater.setInput(inputBytes);

		byte[] output = new byte[uncompressedSize + 1];
		try {
			int total = inflater.inflate(output);
			inflater.end();

			return Arrays.copyOf(output, total);
		} catch (DataFormatException e) {
			LOGGER.error("Unable to uncompress data", e);
		}

		return null;
	}

	/**
	 * Uncompress the given byte-array and convert it into a string.
	 *
	 * @param inputBytes
	 *            the compressed bytes
	 *
	 * @return string representation of the bytes in the default platform
	 *         encoding
	 *
	 */
	public static String uncompressToString(byte[] inputBytes) {
		return new String(uncompress(inputBytes), StringUtils.DEFAULT_CHARSET);
	}

	/**
	 * Uncompress the given byte-array and convert it into a string.
	 *
	 * @param inputBytes
	 *            the compressed bytes
	 *
	 * @param charset
	 * 			  the charset in which to construct {@link String} back
	 *
	 * @return string representation of the bytes in the default platform
	 *         encoding
	 *
	 */
	public static String uncompressToString(byte[] inputBytes, Charset charset) {
		return new String(uncompress(inputBytes), charset);
	}

	/**
	 * Uncompress the given byte-array and convert it into a string where uncompressed
	 * size is known.
	 *
	 * @param inputBytes the compressed bytes
	 *
	 * @param uncompressedSize the uncompressed size
	 *
	 * @return string representation of the bytes in the default platform
	 *         encoding
	 */
	public static String uncompressToString(byte[] inputBytes, int uncompressedSize) {
		return new String(uncompress(inputBytes, uncompressedSize));
	}

	/**
	 * GZip a given byte-array in-memory.
	 *
	 * @param bytes
	 *            the uncompressed bytes
	 *
	 * @return the GZipped byte stream
	 *
	 * @throws IOException
	 *             if something fails
	 */
	public static byte[] gzipByteArray(byte[] bytes) throws IOException {
		if(bytes == null) {
			throw new IllegalArgumentException("byte-array cannot be null");
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);

		GZIPOutputStream gzip = new GZIPOutputStream(baos);
		gzip.write(bytes);
		gzip.close();

		return baos.toByteArray();
	}

	/**
	 * De-compress a GZipped byte-array in-memory.
	 *
	 * @param bytes
	 *            the GZipped compressed bytes
	 *
	 * @return the uncompressed bytes
	 *
	 * @throws IOException
	 *             if something fails
	 */
	public static byte[] ungzipByteArray(byte[] bytes) throws IOException {
		if(bytes == null) {
			throw new IllegalArgumentException("byte-array cannot be null");
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);

		try{
            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(bytes)), baos);
        } catch(IOException e){
            throw new RuntimeException(e);
        }

		return baos.toByteArray();
	}
}
