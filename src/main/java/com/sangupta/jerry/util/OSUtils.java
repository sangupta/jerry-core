/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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

import com.sangupta.jerry.constants.OperatingSystem;
import com.sangupta.jerry.constants.SystemPropertyNames;

/**
 * Utility functions around the Operating System.
 *
 * @author sangupta
 *
 */
public abstract class OSUtils {
	
	protected OSUtils() throws InstantiationException {
		throw new InstantiationException("Instances of this class are forbidden");
	}

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
