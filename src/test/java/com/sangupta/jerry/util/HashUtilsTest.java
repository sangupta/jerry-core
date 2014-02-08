/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/jerry
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
 * @author sangupta
 *
 */
public class HashUtilsTest {
	
	private static final String TEXT = "Hello World!";
	
	private static final byte[] BYTES = TEXT.getBytes();
	
	@Test
	public void testMD2() {
//		Assert.assertEquals("315f7c67223f01fb7cab4b95100e872e", CryptoUtil.getMD2Hex(TEXT));
//		Assert.assertEquals("315f7c67223f01fb7cab4b95100e872e", CryptoUtil.getMD2Hex(BYTES));
	}
	
	@Test
	public void testMD4() {
//		Assert.assertEquals("b2a5cc34fc21a764ae2fad94d56fadf6", CryptoUtil.getMD4Hex(TEXT));
//		Assert.assertEquals("b2a5cc34fc21a764ae2fad94d56fadf6", CryptoUtil.getMD4Hex(BYTES));
	}
	
	@Test
	public void testMD5() {
		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", StringUtils.getHex(HashUtils.getMD5(TEXT)));
		Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", StringUtils.getHex(HashUtils.getMD5(BYTES)));
	}
	
	@Test
	public void testSHA1() {
		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", StringUtils.getHex(HashUtils.getSHA1(TEXT)));
		Assert.assertEquals("2ef7bde608ce5404e97d5f042f95f89f1c232871", StringUtils.getHex(HashUtils.getSHA1(BYTES)));
	}

	@Test
	public void testSHA224() {
//		Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", CryptoUtil.getSHA224Hex(TEXT));
//		Assert.assertEquals("4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b", CryptoUtil.getSHA224Hex(BYTES));
	}
	
	@Test
	public void testSHA256() {
		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", StringUtils.getHex(HashUtils.getSHA256(TEXT)));
		Assert.assertEquals("7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069", StringUtils.getHex(HashUtils.getSHA256(BYTES)));
	}
	
	@Test
	public void testSHA384() {
		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", StringUtils.getHex(HashUtils.getSHA384(TEXT)));
		Assert.assertEquals("bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a", StringUtils.getHex(HashUtils.getSHA384(BYTES)));
	}
	
	@Test
	public void testSHA512() {
		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", StringUtils.getHex(HashUtils.getSHA512(TEXT)));
		Assert.assertEquals("861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8", StringUtils.getHex(HashUtils.getSHA512(BYTES)));
	}
	
	@Test
	public void testWhirlpool() {
//		Assert.assertEquals("d4b3ad3619bc70157376c5426b558dbdad30654cf441ab21d7c08e993873256becc80f32448d0218d5b1aab30bf4209e20e3928df002d3cbcfbe501a184680a8", CryptoUtil.getWhirlpoolHex(TEXT));
//		Assert.assertEquals("d4b3ad3619bc70157376c5426b558dbdad30654cf441ab21d7c08e993873256becc80f32448d0218d5b1aab30bf4209e20e3928df002d3cbcfbe501a184680a8", CryptoUtil.getWhirlpoolHex(BYTES));
	}
	
	@Test
	public void testRipemd160() {
//		Assert.assertEquals("8476ee4631b9b30ac2754b0ee0c47e161d3f724c", CryptoUtil.getRipemd160Hex(TEXT));
//		Assert.assertEquals("8476ee4631b9b30ac2754b0ee0c47e161d3f724c", CryptoUtil.getRipemd160Hex(BYTES));
	}
}
