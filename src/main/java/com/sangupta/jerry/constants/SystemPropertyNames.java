/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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


package com.sangupta.jerry.constants;

/**
 * Constants regarding the system properties available to a Java
 * process.
 *
 * @author sangupta
 *
 */
public interface SystemPropertyNames {

	/**
	 * Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
	 *
	 */
	public static final String FILE_SEPARATOR = "file.separator";

	/**
	 * Path used to find directories and JAR archives containing class files.
	 * Elements of the class path are separated by a platform-specific character
	 * specified in the <code>path.separator</code> property.
	 *
	 */
	public static final String JAVA_CLASS_PATH = "java.class.path";

	/**
	 * Installation directory for Java Runtime Environment (JRE)
	 *
	 */
	public static final String JAVA_HOME = "java.home";

	/**
	 * JRE vendor name
	 *
	 */
	public static final String JAVA_VENDOR = "java.vendor";

	/**
	 * JRE vendor URL
	 *
	 */
	public static final String JAVA_VENDOR_URL = "java.vendor.url";

	/**
	 * JRE version number
	 *
	 */
	public static final String JAVA_VERSION = "java.version";

	/**
	 * Sequence used by operating system to separate lines in text files
	 *
	 */
	public static final String LINE_SEPARATOR = "line.separator";

	/**
	 * Operating system architecture
	 *
	 */
	public static final String OS_ARCHITECTURE = "os.arch";

	/**
	 * Operating system name
	 *
	 */
	public static final String OS_NAME = "os.name";

	/**
	 * Operating system version
	 */
	public static final String OS_VERSION = "os.version";

	/**
	 * Path separator character used in <code>java.class.path</code>
	 */
	public static final String PATH_SEPARATOR = "path.separator";

	/**
	 * User working directory
	 *
	 */
	public static final String USER_DIR = "user.dir";

	/**
	 * User home directory
	 */
	public static final String USER_HOME = "user.home";

	/**
	 * User account name
	 */
	public static final String USER_NAME = "user.name";

	/**
	 * Temporary directory as returned by OS. On Windows it is %TEMP%, except
	 * on Windows 8 where it is %TMP%.
	 */
	public static final String JAVA_TMPDIR = "java.io.tmpdir";

}
