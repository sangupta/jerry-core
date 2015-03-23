/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.sangupta.jerry.constants.SystemPropertyNames;
import com.sangupta.jerry.consume.GenericConsumer;
import com.sangupta.jerry.io.FileByteChunkConsumer;

/**
 * Utility functions around common file operations.
 * 
 * @author sangupta
 *
 */
public class FileUtils {

	/**
	 * One KB as number of bytes
	 */
	public static final long ONE_KB = 1024l;
	
	/**
	 * One MB as number of bytes
	 */
	public static final long ONE_MB = ONE_KB * ONE_KB;
	
	/**
	 * One GB as number of bytes
	 */
	public static final long ONE_GB = ONE_MB * ONE_KB;
	
	/**
	 * One GB as number of bytes
	 */
	public static final long ONE_TB = ONE_GB * ONE_KB;
	
	/**
	 * One TB as number of bytes
	 */
	public static final long ONE_PB = ONE_TB * ONE_KB;
	
	/**
	 * One EB as number of bytes
	 */
	public static final long ONE_EB = ONE_PB * ONE_KB;

	/**
	 * Return a {@link File} object for the user's home directory.
	 * 
	 * @return
	 */
	public static File getUsersHomeDirectory() {
		return new File(System.getProperty(SystemPropertyNames.USER_HOME));
	}
	
	/**
	 * Return a {@link File} object for the user's working directory.
	 * 
	 * @return
	 */
	public static File getUsersWorkingDirectory() {
		return new File(System.getProperty(SystemPropertyNames.USER_DIR));
	}
	
	/**
	 * Returns if the given file path is an absolute file path or not.
	 * 
	 * @param filePath
	 *            the file path to search for
	 * 
	 * @return <code>true</code> if the filepath is absolute, <code>false</code>
	 *         otherwise
	 */
	public static boolean isAbsolutePath(String filePath) {
		if(filePath == null) {
			throw new IllegalArgumentException("Filepath cannot be null");
		}
		
		// normalize
		filePath = FilenameUtils.normalize(filePath);
		
		// now check
		String prefix = FilenameUtils.getPrefix(filePath);
		
		if(AssertUtils.isEmpty(prefix)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * List all files in the given path, assuming the current directory to be
	 * <code>.</code> and without recursive scanning.
	 * 
	 * @param filePathWithWildCards
	 *            the path to scan for
	 * 
	 * @return collection of matched files
	 */
	public static List<File> listFiles(String filePathWithWildCards) {
		return listFiles(new File("."), filePathWithWildCards, false);
	}
	
	/**
	 * List all files in the given path, assuming the current directory to be
	 * <code>.</code> and without recursive scanning.
	 * 
	 * @param filePathWithWildCards
	 *            the path to scan for
	 * 
	 * @param recursive
	 *            whether to recurse into sub-folders or not
	 * 
	 * @return collection of matched files
	 */
	public static List<File> listFiles(String filePathWithWildCards, boolean recursive) {
		return listFiles(new File("."), filePathWithWildCards, recursive);
	}
	
	/**
	 * List all files in the given path.
	 * 
	 * @param baseDir
	 *            the base directory to use
	 * 
	 * @param filePathWithWildCards
	 *            the file path to scan for
	 * 
	 * @param recursive
	 *            whether to recurse into sub-folders or not
	 * 
	 * @return collection of matched files
	 */
	public static List<File> listFiles(File baseDir, String filePathWithWildCards, boolean recursive) {
		return listFiles(baseDir, filePathWithWildCards, recursive, (List<IOFileFilter>) null);
	}
	
	/**
	 * List all files in the given path.
	 * 
	 * @param baseDir
	 *            the base directory to use
	 * 
	 * @param filePathWithWildCards
	 *            the file path to scan for
	 * 
	 * @param recursive
	 *            whether to recurse into sub-folders or not
	 * 
	 * @param additionalFilters
	 *            additional {@link IOFileFilter}s to use during scan
	 * 
	 * @return collection of matched files
	 */
	public static List<File> listFiles(File baseDir, String filePathWithWildCards, boolean recursive, IOFileFilter[] additionalFilters) {
		final List<IOFileFilter> list = new ArrayList<IOFileFilter>();
		if(AssertUtils.isNotEmpty(additionalFilters)) {
			list.addAll(Arrays.asList(additionalFilters));
		}
		
		return listFiles(baseDir, filePathWithWildCards, recursive, list);
	}

	/**
	 * List the files in the given path string with wild cards.
	 * 
	 * @param baseDir
	 *            the base directory to search for files in
	 * 
	 * @param filePathWithWildCards
	 *            the file path to search in - can be absolute
	 * 
	 * @param recursive
	 *            whether to look in sub-directories or not
	 * 
	 * @param additionalFilters
	 *            additional file filters that need to be used when scanning for
	 *            files
	 * 
	 * @return the list of files and directories that match the criteria
	 */
	public static List<File> listFiles(File baseDir, String filePathWithWildCards, boolean recursive, List<IOFileFilter> additionalFilters) {
		if(filePathWithWildCards == null) {
			throw new IllegalArgumentException("Filepath cannot be null");
		}
		
		// change *.* to *
		filePathWithWildCards = filePathWithWildCards.replace("*.*", "*");
		
		// normalize
		filePathWithWildCards = FilenameUtils.normalize(filePathWithWildCards);
		
		if(filePathWithWildCards.endsWith(File.pathSeparator)) {
			filePathWithWildCards += "*";
		}
		
		// check if this is an absolute path or not
		String prefix = FilenameUtils.getPrefix(filePathWithWildCards);
		final boolean isAbsolute = !prefix.isEmpty();
		
		// change the base dir if absolute directory
		if(isAbsolute) {
			baseDir = new File(filePathWithWildCards);
			if(!baseDir.isDirectory()) {
				// not a directory - get the base directory
				filePathWithWildCards = baseDir.getName();
				if(filePathWithWildCards.equals("~")) {
					filePathWithWildCards = "*";
				}
				
				if(AssertUtils.isEmpty(filePathWithWildCards)) {
					filePathWithWildCards = "*";
				}
				
				baseDir = baseDir.getParentFile();
				if(baseDir == null) {
					baseDir = getUsersHomeDirectory();
				}
			}
		}
		
		// check if the provided argument is a directory
		File dir = new File(filePathWithWildCards);
		if(dir.isDirectory()) {
			baseDir = dir.getAbsoluteFile();
			filePathWithWildCards = "*";
		} else {
			// let's check for base directory
			File parent = dir.getParentFile();
			if(parent != null) {
				baseDir = parent.getAbsoluteFile();
				filePathWithWildCards = dir.getName();
			}
		}
		
		// check for user home
		String basePath = baseDir.getPath();
		if(basePath.startsWith("~")) {
			basePath = getUsersHomeDirectory().getAbsolutePath() + File.separator + basePath.substring(1);
			basePath = FilenameUtils.normalize(basePath);
			baseDir = new File(basePath);
		}
		
		// now read the files
		WildcardFileFilter wildcardFileFilter = new WildcardFileFilter(filePathWithWildCards, IOCase.SYSTEM);
		IOFileFilter finalFilter = wildcardFileFilter;
		if(AssertUtils.isNotEmpty(additionalFilters)) {
			additionalFilters.add(0, wildcardFileFilter);
			finalFilter = new AndFileFilter(additionalFilters);
		}
		
		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(baseDir, finalFilter, recursive ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
		
		return (List<File>) files;
	}

	/**
	 * Find the extension of the file. If the file has no extension, an
	 * empty string is returned. If the file instance is <code>null</code>,
	 * a <code>null</code> is returned back.
	 * 
	 * @param file file for which extension is needed
	 * 
	 * @return
	 */
	public static String getExtension(File file) {
		if(file == null) {
			return null;
		}
		
		String name = file.getName();
		int index = name.lastIndexOf('.');
		if(index == -1) {
			return StringUtils.EMPTY_STRING;
		}
		
		return name.substring(index + 1);
	}
	
	/**
	 * Return the MD5 value for the file.
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getMD5(File file) {
		final MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			return null;
		}
		
		return getFileDigestValue(file, digest);
	}
	
	/**
	 * Return the SHA-256 value for the file.
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getSHA256(File file) {
		final MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch(NoSuchAlgorithmException e) {
			return null;
		}
		
		return getFileDigestValue(file, digest);
	}
	
	/**
	 * Run a {@link MessageDigest} algorithm over the contents of the entire
	 * file
	 * 
	 * @param file
	 * 
	 * @param digest
	 * 
	 * @return <code>null</code> if file is <code>null</code>, cannot be read,
	 *         or digest is <code>null</code>. Otherwise returns the actual
	 *         digest value
	 */
	public static byte[] getFileDigestValue(File file, final MessageDigest digest) {
		if(digest == null) {
			return null;
		}
		
		GenericConsumer<byte[]> byteConsumer = new GenericConsumer<byte[]>() {

			@Override
			public boolean consume(byte[] data) {
				digest.update(data);
				return true;
			}
			
		};
		
		new FileByteChunkConsumer(file, byteConsumer).consume();
		
		return digest.digest();
	}
	
	/**
	 * Dump an entire file to HEX
	 * 
	 * @param out
	 * @param file
	 * @throws IOException
	 */
	public static void hexDump(PrintStream out, File file) throws IOException {
		hexDump(out, file, 0, 0);
	}
	
	/**
	 * Dump a given file into HEX starting at given offset and reading given number of rows where
	 * a row consists of 16-bytes.
	 * 
	 * @param out
	 * @param file
	 * @param offset
	 * @param maxRows
	 * @throws IOException
	 */
	public static void hexDump(PrintStream out, File file, long offset, int maxRows) throws IOException {
		InputStream is = null;
		BufferedInputStream bis = null;
		
		try {
			is = new FileInputStream(file);
			bis = new BufferedInputStream(is);
			bis.skip(offset);
			
			int row = 0;
			if(maxRows == 0) {
				maxRows = Integer.MAX_VALUE;
			}
			
			StringBuilder builder1 = new StringBuilder(100);
			StringBuilder builder2 = new StringBuilder(100);
			
			while (bis.available() > 0) {
				out.printf("%04X  ", row * 16);
				for (int j = 0; j < 16; j++) {
					if (bis.available() > 0) {
						int value = (int) bis.read();
						builder1.append(String.format("%02X ", value));
						
						if (!Character.isISOControl(value)) {
							builder2.append((char) value);
						} else {
							builder2.append(".");
						}
					} else {
						for (; j < 16; j++) {
							builder1.append("   ");
						}
					}
				}
				out.print(builder1);
				out.println(builder2);
				row++;
				
				if(row > maxRows) {
					break;
				}
				
				builder1.setLength(0);
				builder2.setLength(0);
			}
		} finally {
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(is);
		}
	}
}
