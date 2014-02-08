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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.sangupta.jerry.encoder.Base64Encoder;

/**
 * @author sangupta
 * 
 */
public class HashUtils {

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

}
