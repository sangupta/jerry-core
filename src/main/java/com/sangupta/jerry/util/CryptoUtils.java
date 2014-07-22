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

import java.security.AlgorithmParameters;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sangupta.jerry.encoder.Base64Encoder;

/**
 * Utility functions related to cryptography.
 * 
 * @author sangupta
 *
 */
public class CryptoUtils {

	/**
	 * AES-256 encrypt the text.
	 * 
	 * @param plainText
	 *            the text to be encrypted
	 * 
	 * @param password
	 *            the password to use
	 * 
	 * @param saltBytes
	 *            the salt to use
	 * 
	 * @param iterations
	 *            the number of iterations to run on password - should be 65536
	 *            or more
	 * 
	 * @param keySize
	 *            the key size to use - should be 256 or more
	 * 
	 * @return a string-array that contains base-64 encoded IV used to encrypt
	 *         and the second, a base-64 based string representing the encrypted
	 *         text
	 * 
	 * @throws Exception
	 *             if something fails
	 */
	public String[] encryptAES256(String plainText, char[] password, byte[] saltBytes, int iterations, int keySize) throws Exception {   
        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(password, saltBytes, iterations, keySize);
 
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
 
        //encrypt the message
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();
        byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return new String[] { Base64Encoder.encodeToString(ivBytes, false), Base64Encoder.encodeToString(encryptedTextBytes, true) };
    }
	
	/**
	 * AES-256 decrypt the text.
	 * 
	 * @param encryptedText
	 *            the encrypted text represented as base-64
	 * 
	 * @param password
	 *            the password to use
	 * 
	 * @param salt
	 *            the salt to use
	 * 
	 * @param iterations
	 *            the number of iterations to run on password - should be 65536
	 *            or more
	 * 
	 * @param keySize
	 *            the key size to use - should be 256 or more
	 * 
	 * @param base64IV
	 *            the base-64 encoded IV that was generated during encryption
	 *            process
	 * 
	 * @return the decrypted text
	 * 
	 * @throws Exception
	 *             if something fails
	 */
	public String decryptAES256(String encryptedText, char[] password, byte[] salt, int iterations, int keySize, String base64IV) throws Exception {
        byte[] encryptedTextBytes = Base64Encoder.decode(encryptedText);
 
        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keySize); 
 
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
 
        // Decrypt the message
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(Base64Encoder.decode(base64IV)));
     
        byte[] decryptedTextBytes = null;
        decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
 
        return new String(decryptedTextBytes);
    }
	
}
