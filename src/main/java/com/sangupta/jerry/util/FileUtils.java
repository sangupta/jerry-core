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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.sangupta.jerry.constants.SystemPropertyNames;

/**
 * Utility functions around common file operations.
 * 
 * @author sangupta
 *
 */
public class FileUtils {

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
}
