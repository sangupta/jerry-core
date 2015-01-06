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

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility functions to read string input from command line. Makes use of the
 * new {@link Console} class. If it is unavailable, switches mode to read from
 * the standard input, via {@link System} class.
 * 
 * @author sangupta
 *
 */
public class ConsoleUtils {
	
	private static BufferedReader reader;
	
	/**
	 * Function to read a line from the command line
	 * 
	 * @param allowEmpty whether empty strings should be accepted as input or not
	 * 
	 * @return the String representation of the user input
	 */
	public static String readLine(boolean allowEmpty) {
		return readLine("", allowEmpty);
	}
	
	/**
	 * Function to read a line from the command line with the given message.
	 * 
	 * @param message message to be shown before waiting for user input
	 * 
	 * @param allowEmpty whether empty strings should be accepted as input or not
	 * 
	 * @return the String representation of the user input
	 */
	public static String readLine(String message, boolean allowEmpty) {
		String line = null;
		
		do {
			// read the password
			Console console = System.console();
			if(console != null) {
				line = console.readLine(message);
			} else {
				// unable to find a console - default to original mode
				// System.out.println("*** Unable to find console object, defaulting to normal input mode");
				try {
					if(reader == null) {
						reader = new BufferedReader(new InputStreamReader(System.in));
					}
					
					System.out.print(message);
					line = reader.readLine();
				} catch (IOException e) {
					throw new RuntimeException("Unable to read password from standard input", e);
				}
			}
			
			if(allowEmpty) {
				if(line != null) {
					break;
				}
			} else {
				if(AssertUtils.isNotEmpty(line)) {
					break;
				}
			}
			
			
		} while(true);
		
		return line;
	}
	
	/**
	 * Function to read a password from the command line.
	 * 
	 * @param allowEmpty whether empty strings should be accepted as input or not
	 * 
	 * @return the String representation of the user input
	 */
	public static String readPassword(boolean allowEmpty) {
		return readPassword("", allowEmpty);
	}
	

	/**
	 * Function to read a password from the command line with the given message.
	 * 
	 * @param message message to be shown before waiting for user input
	 * 
	 * @param allowEmpty whether empty strings should be accepted as input or not
	 * 
	 * @return the String representation of the user input
	 */
	public static String readPassword(String message, boolean allowEmpty) {
		String password = null;
		
		do {
			// read the password
			Console console = System.console();
			if(console != null) {
				char[] charsRead = console.readPassword(message);
				password = new String(charsRead);
			} else {
				// unable to find a console - default to original mode
				// System.out.println("*** Unable to find console object, defaulting to normal input mode");
				try {
					if(reader == null) {
						reader = new BufferedReader(new InputStreamReader(System.in));
					}
					
					System.out.print(message);
					password = reader.readLine();
				} catch (IOException e) {
					throw new RuntimeException("Unable to read password from standard input", e);
				}
			}
			
			if(allowEmpty) {
				if(password != null) {
					break;
				}
			} else {
				if(AssertUtils.isNotEmpty(password)) {
					break;
				}
			}
			
			
		} while(true);
		
		return password;
	}

}
