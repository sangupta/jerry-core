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
	
	/**
	 * Global enum depicting the current OS the JVM is running in.
	 * 
	 */
	public static final OperatingSystem OS = detectOperatingSystem();
	
	/**
	 * Detect the {@link OperatingSystem} we are running inside.
	 * 
	 * @return
	 */
	private static OperatingSystem detectOperatingSystem() {
		String osName = System.getProperty(SystemPropertyNames.OS_NAME);
		return detectOperatingSystem(osName);
	}
	
	/**
	 * Detect the operating system based on the given OS name.
	 * 
	 * @param osName
	 *            the string representing the OS name
	 * 
	 * @return the deciphered {@link OperatingSystem} enum, or <code>null</code>
	 *         if name is <code>null</code> or <code>empty</code>
	 */
	public static OperatingSystem detectOperatingSystem(String osName) {
		if(AssertUtils.isEmpty(osName)) {
			return null;
		}
		
		osName = osName.toLowerCase();
		
		if(osName.contains("win")) {
			return OperatingSystem.Windows;
		}
		
		if(osName.contains("mac") || osName.contains("darwin")) {
			return OperatingSystem.MacOSX;
		}
		
		if(osName.contains("linux")) {
			return OperatingSystem.Linux;
		}
		
		if(osName.contains("sunos")) {
			return OperatingSystem.SunOS;
		}
		
		if(osName.contains("solaris")) {
			return OperatingSystem.Solaris;
		}
		
		if(osName.contains("aix")) {
			return OperatingSystem.AIX;
		}
		
		if(osName.contains("hp-ux")) {
			return OperatingSystem.HP_UX;
		}
		
		if(osName.contains("os/2")) {
			return OperatingSystem.OS_2;
		}
		
		if(osName.contains("os/390")) {
			return OperatingSystem.OS_390;
		}
		
		if(osName.contains("os/400")) {
			return OperatingSystem.OS_400;
		}
		
		if(osName.contains("freebsd")) {
			return OperatingSystem.FreeBSD;
		}
		
		if(osName.contains("irix")) {
			return OperatingSystem.Irix;
		}
		
		if(osName.contains("netbsd")) {
			return OperatingSystem.NetBSD;
		}
		
		return OperatingSystem.Unknown;
	}

}
