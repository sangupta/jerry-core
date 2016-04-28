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

import com.sangupta.jerry.constants.SystemPropertyNames;

/**
 * Utility functions around the JDK
 * 
 * @author sangupta
 *
 */
public abstract class JDKUtils {
    
	/**
	 * Check if JDK version is 1.7 or not
	 * 
	 * @return <code>true</code> if version is 1.7, <code>false</code> otherwise
	 */
	public static boolean isJDK7() {
		String version = System.getProperty(SystemPropertyNames.JAVA_VERSION);
		if(version.startsWith("1.7")) {
			return true;
		}
		
		return false;
	}
	
	/**
     * Check if JDK version is 1.8 or not
     * 
     * @return <code>true</code> if version is 1.8, <code>false</code> otherwise
     */
    public static boolean isJDK8() {
        String version = System.getProperty(SystemPropertyNames.JAVA_VERSION);
        if(version.startsWith("1.8")) {
            return true;
        }
        
        return false;
    }
	
    /**
     * Check if JDK version is 1.9 or not
     * 
     * @return <code>true</code> if version is 1.9, <code>false</code> otherwise
     */
    public static boolean isJDK9() {
        String version = System.getProperty(SystemPropertyNames.JAVA_VERSION);
        if(version.startsWith("1.9")) {
            return true;
        }
        
        return false;
    }
    
	/**
	 * Check if we are running under OracleJDK
	 * 
	 * @return <code>true</code> if VM vendor is Oracle, <code>false</code>
	 *         otherwise
	 */
	public static boolean isOracleJDK() {
		String name = System.getProperty("java.vm.name");
		name = name.toLowerCase();
		if(name.contains("java hotspot(tm)")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if we are running under OpenJDK
	 * 
	 * @return <code>true</code> if VM vendor is OpenJDK, <code>false</code>
	 *         otherwise
	 */
	public static boolean isOpenJDK() {
		String name = System.getProperty("java.vm.name");
		name = name.toLowerCase();
		if(name.contains("openjdk")) {
			return true;
		}
		
		return false;
	}
	
}