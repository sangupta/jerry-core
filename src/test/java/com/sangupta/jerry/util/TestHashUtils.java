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

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link HashUtils} class.
 *
 * @author sangupta
 *
 */
public class TestHashUtils {

	private static final String TEXT = "Hello World!";

	private static final byte[] BYTES = TEXT.getBytes();

	@Test
	public void testMD5() {
		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", StringUtils.asHex(HashUtils.getMD5(TEXT)));
		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", StringUtils.asHex(HashUtils.getMD5(BYTES)));

		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", HashUtils.getMD5Hex(TEXT));
		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", HashUtils.getMD5Hex(BYTES));
	}

	@Test
	public void testSHA1() {
		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", StringUtils.asHex(HashUtils.getSHA1(TEXT)));
		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", StringUtils.asHex(HashUtils.getSHA1(BYTES)));

		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", HashUtils.getSHA1Hex(TEXT));
		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", HashUtils.getSHA1Hex(BYTES));
	}

	@Test
	public void testSHA224() {
		// the following null checks are added because in Travis-CI Oracle JDK 7 - SHA224 is not available
		
		String hashed = StringUtils.asHex(HashUtils.getSHA224(TEXT));
		if(hashed != null) {
			Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", hashed);
		}
		
		hashed = StringUtils.asHex(HashUtils.getSHA224(BYTES));
		if(hashed != null) {
			Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", hashed);
		}

		hashed = HashUtils.getSHA224Hex(TEXT);
		if(hashed != null) {
			Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", hashed);
		}
		
		hashed = HashUtils.getSHA224Hex(BYTES);
		if(hashed != null) {
			Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", hashed);
		}
	}

	@Test
	public void testSHA256() {
		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", StringUtils.asHex(HashUtils.getSHA256(TEXT)));
		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", StringUtils.asHex(HashUtils.getSHA256(BYTES)));

		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", HashUtils.getSHA256Hex(TEXT));
		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", HashUtils.getSHA256Hex(BYTES));
	}

	@Test
	public void testSHA384() {
		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", StringUtils.asHex(HashUtils.getSHA384(TEXT)));
		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", StringUtils.asHex(HashUtils.getSHA384(BYTES)));

		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", HashUtils.getSHA384Hex(TEXT));
		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", HashUtils.getSHA384Hex(BYTES));
	}

	@Test
	public void testSHA512() {
		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", StringUtils.asHex(HashUtils.getSHA512(TEXT)));
		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", StringUtils.asHex(HashUtils.getSHA512(BYTES)));

		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", HashUtils.getSHA512Hex(TEXT));
		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", HashUtils.getSHA512Hex(BYTES));
	}

	@Test
	public void testHMAC() {
		Assert.assertEquals("nH3h4e6Jr4J4p/zIr/y4Xf32Juk=", HashUtils.getHMAC(TEXT, "sangupta"));
		Assert.assertEquals("7F+3Vl+LjgJJcCKInup4kPn1fQc=", HashUtils.getHMAC(TEXT, "keyString"));
	}
	
	@Test
	public void testGetPBKDF2() {
		Assert.assertEquals("92fb2ee7ffdb0e86e6ef9c44", HashUtils.getPBKDF2Hex("signable", "salt", 100, 100));
		Assert.assertEquals("2b1255902c88eb0d8152b300", HashUtils.getPBKDF2Hex(TEXT, "salt", 100, 100));
	}
	
}
