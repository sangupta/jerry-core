package com.sangupta.jerry.util;

/**
 * Utility functions around the JDK
 * 
 * @author sangupta
 *
 */
public class JDKUtils {
	
	/**
	 * Check if JDK version is 1.7 or not
	 * 
	 * @return <code>true</code> if version is 1.7, <code>false</code> otherwise
	 */
	public static boolean isJDK7() {
		String version = System.getProperty("java.version");
		if(version.startsWith("1.7")) {
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
