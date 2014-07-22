/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sangupta.jerry.encoder.Base64Encoder;

/**
 * @author sangupta
 * 
 */
public class HashUtils {

	/**
	 * Computes the MD5 hash of the given data.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
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
		
		return StringUtils.asHex(digest);
	}
	
	/**
	 * Compute the MD5 of the given string data. It is converted to bytes using
	 * platform specific default encoding.
	 * 
	 * @param data
	 *            the string for which hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getMD5(String data) {
		return getMD5(data.getBytes());
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
	 * @param data
	 *            the data for which the hash needs to be computed
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
		
		return StringUtils.asHex(digest);
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
	 * Computes the SHA-1 hash of the given string data. It is converted to
	 * bytes using platform specific default encoding.
	 * 
	 * @param data
	 *            the string for which hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getSHA1(String data) {
		return getSHA1(data.getBytes());
	}
	
	/**
	 * Computes the SHA-256 hash of the given data.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
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
	 * Computes the SHA-256 hash of the given string data. It is converted to
	 * bytes using platform specific default encoding.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getSHA256(String data) {
		return getSHA256(data.getBytes());
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
		
		return StringUtils.asHex(digest);
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
	 * Compute the SHA-224 hash for the given bytes.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
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
	 * Computes the SHA-224 hash of the given string data. It is converted to
	 * bytes using platform specific default encoding.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getSHA224(String data) {
		return getSHA224(data.getBytes());
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
		
		return StringUtils.asHex(digest);
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
	 * Compute the SHA-384 hash for the given bytes.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
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
	 * Computes the SHA-384 hash of the given string data. It is converted to
	 * bytes using platform specific default encoding.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getSHA384(String data) {
		return getSHA384(data.getBytes());
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
		
		return StringUtils.asHex(digest);
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
	 * Compute the SHA-512 hash for the given bytes.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
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
	 * Computes the SHA-512 hash of the given string data. It is converted to
	 * bytes using platform specific default encoding.
	 * 
	 * @param data
	 *            the data for which the hash needs to be computed
	 * 
	 * @return the hash represented in a byte-array, or <code>null</code> if
	 *         there is no provider available for the given hash algorithm.
	 */
	public static byte[] getSHA512(String data) {
		return getSHA512(data.getBytes());
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
		
		return StringUtils.asHex(digest);
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
	 * Generate the HMAC_SHA1 signature for the given string using the given key
	 * string. The method never returns a <code>null</code>.
	 * 
	 * @param signable
	 *            the string to be signed or generated hash of
	 * 
	 * @param keyString
	 *            the key string or secret to use to generate the hash.
	 * 
	 * @return the Base64 encoded string representing the hash
	 * 
	 * @throws RuntimeException
	 *             if the algorithm implementation is not found, or the key
	 *             provided is invalid.
	 * 
	 */
	public static String getHMAC(String signable, String keyString) {
		SecretKeySpec key = new SecretKeySpec((keyString).getBytes(StringUtils.CHARSET_UTF8), "HmacSHA1");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(key);
			byte[] bytes = mac.doFinal(signable.getBytes(StringUtils.CHARSET_UTF8));
			return Base64Encoder.encodeToString(bytes, false);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("This JRE version does not provide an implementation of HMAC-SHA1 algorithm");
		} catch (InvalidKeyException e) {
			throw new RuntimeException("Invalid key exception, unable to sign string!");
		}
	}

	/**
	 * Generate the PBKDF2-with-HMAC-SHA1 hash for the given signable string.
	 * 
	 * @param signable
	 *            the string to be hashed
	 * 
	 * @param salt
	 *            the salt to be used
	 * 
	 * @param numIterations
	 *            the number of iterations to run
	 * 
	 * @param entropy
	 *            the entropy to use
	 * 
	 * @return the byte-array representing the hash
	 */
	public static byte[] getPBKDF2(String signable, String salt, int numIterations, int entropy) {
		// generate the hash
		try {
			PBEKeySpec spec = new PBEKeySpec(signable.toCharArray(), salt.getBytes(), numIterations, entropy);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        return skf.generateSecret(spec).getEncoded();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException("General Security Exception", e);
		}
	}
}
