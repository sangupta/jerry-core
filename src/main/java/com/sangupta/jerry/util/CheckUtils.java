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
     * Private as static methods do not need instantiation
     */
    public CheckUtils() {
        // prevent instantiation for utility class
    }

	/**
	 * Check whether the file represented by the given absolute file path is a
	 * valid file, and does exists on the disk.
	 * 
	 * @param absoluteFilePath
	 *            the absolute file path to test
	 * 
	 * @return <code>true</code> if the file exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkFileExists(String absoluteFilePath) {
		if(AssertUtils.isEmpty(absoluteFilePath)) {
			throw new IllegalArgumentException("Absolute file path cannot be null/empty");
		}
		
		File file = new File(absoluteFilePath);
		return checkFileExists(file);
	}
	
	/**
	 * Check whether the file represented by the given {@link File} object
	 * exists and is a valid file.
	 * 
	 * @param absoluteFilePath
	 *            the absolute file path to test
	 * 
	 * @return <code>true</code> if the file exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkFileExists(File absoluteFilePath) {
		if(absoluteFilePath == null) {
			throw new IllegalArgumentException("File instance cannot be null");
		}
		if(!absoluteFilePath.exists()) {
			throw new IllegalArgumentException("File does not exist on device");
		}
		
		if(!absoluteFilePath.isFile()) {
			throw new IllegalArgumentException("File does not represent a valid file");
		}
		
		return true;
	}
	
	/**
	 * Check if the file can be read successfully.
	 * 
	 * @param absoluteFilePath
	 *            the absolute file path to test
	 * 
	 * @return <code>true</code> if the file is readable, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkReadableFile(File absoluteFilePath) {
		CheckUtils.checkFileExists(absoluteFilePath);
		
		if(!absoluteFilePath.canRead()) {
			throw new IllegalArgumentException("File cannot be read");
		}
		
		return true;
	}
	
	/**
	 * Check if the file can be written successfully.
	 * 
	 * @param absoluteFilePath
	 *            the absolute file path to test
	 * 
	 * @return <code>true</code> if the file can be written to,
	 *         <code>false</code> otherwise
	 */
	public static boolean checkWritableFile(File absoluteFilePath) {
		CheckUtils.checkFileExists(absoluteFilePath);
		
		if(!absoluteFilePath.canWrite()) {
			throw new IllegalArgumentException("File cannot be written to");
		}
		
		return true;
	}
	
	/**
	 * Check if the file can be executed.
	 * 
	 * @param absoluteFilePath
	 *            the absolute file path to test
	 * 
	 * @return <code>true</code> if the file can be executed, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkExecutableFile(File absoluteFilePath) {
		CheckUtils.checkFileExists(absoluteFilePath);
		
		if(!absoluteFilePath.canExecute()) {
			throw new IllegalArgumentException("File cannot be executed");
		}
		
		return true;
	}

	/**
	 * Check whether the directory represented by the given absolute file path
	 * is a valid directory, and does exists on the disk.
	 * 
	 * @param absoluteDirPath
	 *            the absolute folder path to test
	 * 
	 * @return <code>true</code> if the directory exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkDirectoryExists(String absoluteDirPath) {
		if(AssertUtils.isEmpty(absoluteDirPath)) {
			throw new IllegalArgumentException("Absolute dir path cannot be null/empty");
		}
		
		File file = new File(absoluteDirPath);
		return checkDirectoryExists(file);
	}
	
	/**
	 * Check whether the directory represented by the given {@link File} object
	 * exists and is a valid directory.
	 * 
	 * @param absoluteDirPath
	 *            the absolute folder path to test
	 * 
	 * @return <code>true</code> if the directory exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean checkDirectoryExists(File absoluteDirPath) {
		if(absoluteDirPath == null) {
			throw new IllegalArgumentException("File instance cannot be null");
		}
		if(!absoluteDirPath.exists()) {
			throw new IllegalArgumentException("Directory does not exist on device");
		}
		
		if(!absoluteDirPath.isDirectory()) {
			throw new IllegalArgumentException("File does not represent a valid directory");
		}
		
		return true;
	}
	
	/**
	 * Check if the given argument is <code>true</code> or not. If the condition
	 * is <code>false</code>, an {@link IllegalArgumentException} with the
	 * supplied message is thrown
	 * 
	 * @param condition
	 *            the condition to evaluate
	 * 
	 * @param message
	 *            if the condition evaluates to <code>false</code>
	 * 
	 * @throws IllegalArgumentException
	 *             if the condition evaluates to <code>false</code>
	 * 
	 * @return <code>true</code> if everything is fine, will never return a
	 *         <code>false</code>
	 */
	public static boolean checkArgument(boolean condition, String message) {
		if(condition) {
			return true;
		}
		
		throw new IllegalArgumentException(message);
	}
	
	/**
	 * Check if the given argument is <code>true</code> or not. If the condition
	 * is <code>false</code>, an {@link IllegalStateException} with the
	 * supplied message is thrown
	 * 
	 * @param condition
	 *            the condition to evaluate
	 * 
	 * @param message
	 *            if the condition evaluates to <code>false</code>
	 * 
	 * @throws IllegalStateException
	 *             if the condition evaluates to <code>false</code>
	 *             
	 * @return <code>true</code> if everything is fine, will never return a
	 *         <code>false</code>
	 */
	public static boolean checkState(boolean condition, String message) {
		if(condition) {
			return true;
		}
		
		throw new IllegalStateException(message);
	}
	
}