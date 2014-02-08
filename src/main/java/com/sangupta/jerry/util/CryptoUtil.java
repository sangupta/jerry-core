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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides easier access to cryptographic functions.
 * 
 * @author sangupta
 * @since 0.2
 */
public class CryptoUtil {
	
	/**
	 * Computes the MD5 hash of the given data.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 * there is no provider available for the given hash algorithm.
	 * 
	 */
	public static byte[] getMD5(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(data);
			return digest;
		} catch (NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Computes the MD5 hash of the given data and returns 
	 * the representation in Hex format.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented as String in hex-format
	 * 
	 */
	public static String getMD5Hex(byte[] data) {
		byte[] digest = getMD5(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getMD5Hex(String data) {
		return getMD5Hex(data.getBytes());
	}
	
	/**
	 * Computes the SHA-1 hash of the given data.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array
	 * 
	 */
	public static byte[] getSHA1(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(data);
			return digest;
		} catch (NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}

	/**
	 * Computes the SHA-1 hash of the given data and returns the
	 * representation in Hex format.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented as String in hex-format
	 * 
	 */
	public static String getSHA1Hex(byte[] data) {
		byte[] digest = getSHA1(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA1Hex(String data) {
		return getSHA1Hex(data.getBytes());
	}
	
	/**
	 * Computes the SHA-256 hash of the given data.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array
	 * 
	 */
	public static byte[] getSHA256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(data);
			return digest;
		} catch(NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Computes the SHA-256 hash of the given data and returns the
	 * representation in Hex format.
	 * 
	 * @param data the data for which the hash needs to be computed
	 * 
	 * @return the hash represented as String in hex-format
	 * 
	 */
	public static String getSHA256Hex(byte[] data) {
		byte[] digest = getSHA256(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA256Hex(String data) {
		return getSHA256Hex(data.getBytes());
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getSHA224(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			byte[] digest = md.digest(data);
			return digest;
		} catch(NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA224Hex(byte[] data) {
		byte[] digest = getSHA224(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA224Hex(String data) {
		return getSHA224Hex(data.getBytes());
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getSHA384(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			byte[] digest = md.digest(data);
			return digest;
		} catch(NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA384Hex(byte[] data) {
		byte[] digest = getSHA384(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA384Hex(String data) {
		return getSHA384Hex(data.getBytes());
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getSHA512(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digest = md.digest(data);
			return digest;
		} catch(NoSuchAlgorithmException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA512Hex(byte[] data) {
		byte[] digest = getSHA512(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getSHA512Hex(String data) {
		return getSHA512Hex(data.getBytes());
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getWhirlpool(byte[] data) {
		return null;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getWhirlpoolHex(byte[] data) {
		byte[] digest = getWhirlpool(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String getWhirlpoolHex(String data) {
		return getWhirlpoolHex(data.getBytes());
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getRipemd160(byte[] data) {
		return null;
	}
	
	/**
	 * @param bytes
	 * @return
	 */
	public static String getRipemd160Hex(byte[] data) {
		byte[] digest = getRipemd160(data);
		if(digest == null) {
			return null;
		}
		
		return StringUtils.getHex(digest);
	}

	/**
	 * @param text
	 * @return
	 */
	public static String getRipemd160Hex(String data) {
		return getRipemd160Hex(data.getBytes());
	}

}
