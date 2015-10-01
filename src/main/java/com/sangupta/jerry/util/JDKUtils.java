package com.sangupta.jerry.util;

/**
 * Utility functions around the JDK
 * 
 * @author sangupta
 *
 */
public class JDKUtils {
	
	/**
	 * Check if we are running under OracleJDK
	 * 
	 * @return
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
	 * @return
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
