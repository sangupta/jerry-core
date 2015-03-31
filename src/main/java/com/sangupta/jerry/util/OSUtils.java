package com.sangupta.jerry.util;

import com.sangupta.jerry.constants.OperatingSystem;
import com.sangupta.jerry.constants.SystemPropertyNames;

/**
 * Utility functions around the Operating System.
 * 
 * @author sangupta
 *
 */
public class OSUtils {
	
	public static final OperatingSystem OS = detectOperatingSystem();
	
	private static OperatingSystem detectOperatingSystem() {
		String os = System.getProperty(SystemPropertyNames.OS_NAME).toLowerCase();
		if(os.indexOf("win") >= 0) {
			return OperatingSystem.Windows;
		}
		
		if(os.indexOf("mac") >= 0 || os.indexOf("darwin") >= 0) {
			return OperatingSystem.MacOSX;
		}
		
		if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 ) {
			return OperatingSystem.Linux;
		}
		
		if(os.indexOf("sunos") >= 0) {
			return OperatingSystem.Solaris;
		}
		
		return OperatingSystem.Unknown;
	}

}
