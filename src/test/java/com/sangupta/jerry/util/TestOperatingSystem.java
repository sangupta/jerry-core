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

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.constants.OperatingSystem;

public class TestOperatingSystem {
	
	@Test
	public void testOS() {
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows XP"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 95"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 98"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows CE"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 2003"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 7"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 10"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 8"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows 2000"));
		Assert.assertEquals(OperatingSystem.Windows, OSUtils.detectOperatingSystem("Windows Me"));
		
		Assert.assertEquals(OperatingSystem.MacOSX, OSUtils.detectOperatingSystem("Mac OS"));
		Assert.assertEquals(OperatingSystem.MacOSX, OSUtils.detectOperatingSystem("Mac OS X"));
		
		Assert.assertEquals(OperatingSystem.Linux, OSUtils.detectOperatingSystem("Linux"));
		Assert.assertEquals(OperatingSystem.SunOS, OSUtils.detectOperatingSystem("SunOS"));
		Assert.assertEquals(OperatingSystem.Solaris, OSUtils.detectOperatingSystem("Solaris"));
		Assert.assertEquals(OperatingSystem.HP_UX, OSUtils.detectOperatingSystem("HP-UX"));
		Assert.assertEquals(OperatingSystem.AIX, OSUtils.detectOperatingSystem("AIX"));
		Assert.assertEquals(OperatingSystem.Irix, OSUtils.detectOperatingSystem("Irix"));
		
		Assert.assertEquals(OperatingSystem.OS_2, OSUtils.detectOperatingSystem("OS/2"));
		Assert.assertEquals(OperatingSystem.OS_390, OSUtils.detectOperatingSystem("OS/390"));
		Assert.assertEquals(OperatingSystem.OS_400, OSUtils.detectOperatingSystem("OS/400"));
		
		Assert.assertEquals(OperatingSystem.FreeBSD, OSUtils.detectOperatingSystem("FreeBSD"));
		Assert.assertEquals(OperatingSystem.NetBSD, OSUtils.detectOperatingSystem("NetBSD"));
		
		Assert.assertEquals(OperatingSystem.Unknown, OSUtils.detectOperatingSystem("sangupta"));
	}
	
}