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

/**
 * Common assertions functions that throw an {@link IllegalArgumentException},
 * {@link IllegalStateException} or a {@link RuntimeException} when the
 * assertion is not met. If you are looking for obtaining a <code>boolean</code>
 * for handling the assertion failure in code, use {@link AssertUtils}.
 * 
 * @author sangupta
 * @since 1.2.0
 */
public class CheckUtils {

	/**
	 * Check whether the file represented by the given absolute file path is a valid
	 * file, and does exists on the disk.
	 * 
	 * @param absoluteFilePath
	 */
	public static void checkFileExists(String absoluteFilePath) {
		if(AssertUtils.isEmpty(absoluteFilePath)) {
			throw new IllegalArgumentException("Absolute file path cannot be null/empty");
		}
		
		File file = new File(absoluteFilePath);
		checkFileExists(file);
	}
	
	/**
	 * Check whether the file represented by the given {@link File} object
	 * exists and is a valid file.
	 * 
	 * @param file
	 */
	public static void checkFileExists(File file) {
		if(file == null) {
			throw new IllegalArgumentException("File instance cannot be null");
		}
		if(!file.exists()) {
			throw new IllegalArgumentException("File does not exist on device");
		}
		
		if(!file.isFile()) {
			throw new IllegalArgumentException("File does not represent a valid file");
		}
	}
	
	/**
	 * Check if the file can be read successfully.
	 * 
	 * @param file
	 */
	public static void checkReadableFile(File file) {
		CheckUtils.checkFileExists(file);
		
		if(!file.canRead()) {
			throw new IllegalArgumentException("File cannot be read");
		}
	}
	
	/**
	 * Check if the file can be written successfully.
	 * 
	 * @param file
	 */
	public static void checkWritableFile(File file) {
		CheckUtils.checkFileExists(file);
		
		if(!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written to");
		}
	}
	
	/**
	 * Check if the file can be executed.
	 * 
	 * @param file
	 */
	public static void checkExecutableFile(File file) {
		CheckUtils.checkFileExists(file);
		
		if(!file.canExecute()) {
			throw new IllegalArgumentException("File cannot be executed");
		}
	}

	/**
	 * Check whether the directory represented by the given absolute file path
	 * is a valid directory, and does exists on the disk.
	 * 
	 * @param absoluteDirPath
	 */
	public static void checkDirectoryExists(String absoluteDirPath) {
		if(AssertUtils.isEmpty(absoluteDirPath)) {
			throw new IllegalArgumentException("Absolute dir path cannot be null/empty");
		}
		
		File file = new File(absoluteDirPath);
		checkDirectoryExists(file);
	}
	
	/**
	 * Check whether the directory represented by the given {@link File} object
	 * exists and is a valid directory.
	 * 
	 * @param directory
	 */
	public static void checkDirectoryExists(File directory) {
		if(directory == null) {
			throw new IllegalArgumentException("File instance cannot be null");
		}
		if(!directory.exists()) {
			throw new IllegalArgumentException("Directory does not exist on device");
		}
		
		if(!directory.isFile()) {
			throw new IllegalArgumentException("File does not represent a valid directory");
		}
	}
	
	/**
	 * 
	 * @param condition
	 * @param message
	 */
	public static void checkArgument(boolean condition, String message) {
		if(condition) {
			return;
		}
		
		throw new IllegalArgumentException(message);
	}
	
	public static void checkState(boolean condition, String message) {
		if(condition) {
			return;
		}
		
		throw new IllegalStateException(message);
	}
	
}
