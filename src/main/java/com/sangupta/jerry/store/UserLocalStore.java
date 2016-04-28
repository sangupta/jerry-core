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


package com.sangupta.jerry.store;

import java.util.Collection;
import java.util.List;

/**
 * Contract for a data store that intends to store
 * key-value pairs on behalf of the user in the user's
 * home directory.
 *
 * @author sangupta
 *
 */
public interface UserLocalStore {

	/**
	 * Read the value of the data-store.
	 *
	 * @param property
	 *            the property to read
	 *
	 * @return the value of the property or <code>null</code>
	 */
	public String get(String property);

	/**
	 * Get the boolean value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Boolean}
	 *
	 * @return the boolean value
	 */
	public boolean getBoolean(String property, boolean defaultValue);

	/**
	 * Get the byte value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Byte}
	 *
	 * @return the byte value
	 */
	public byte getByte(String property, byte defaultValue);

	/**
	 * Get the char value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Character}
	 *
	 * @return the char value
	 */
	public char getChar(String property, char defaultValue);

	/**
	 * Get the short value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Short}
	 *
	 * @return the short value
	 */
	public short getShort(String property, short defaultValue);

	/**
	 * Get the int value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Integer}
	 *
	 * @return the int value
	 */
	public int getInt(String property, int defaultValue);

	/**
	 * Get the long value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Long}
	 *
	 * @return the long value
	 */
	public long getLong(String property, long defaultValue);

	/**
	 * Get the float value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Float}
	 *
	 * @return the float value
	 */
	public float getFloat(String property, float defaultValue);

	/**
	 * Get the double value for the given property name. Return the default
	 * value if the property does not exists, or cannot be converted into a
	 * boolean.
	 *
	 * @param property
	 *            the name of the property
	 *
	 * @param defaultValue
	 *            the default value if the property is not found, or cannot be
	 *            converted to {@link Double}
	 *
	 * @return the double value
	 */
	public double getDouble(String property, double defaultValue);

	/**
	 * Read all the properties from the underlying {@link UserLocalStore} into
	 * the given instance.
	 *
	 * If the instance is <code>null</code>, no error is thrown
	 *
	 * @param instance
	 *            the instance to read values into
	 *
	 * @return <code>true</code> if values were read, <code>false</code>
	 *         otherwise
	 */
	public boolean readTo(Object instance);

	/**
	 * Write all the non-static non-transient properties from the given instance
	 * variable to the underlying {@link UserLocalStore}.
	 *
	 * If the instance is <code>null</code>, no error is thrown
	 *
	 * @param instance
	 *            the instance to write value from
	 *
	 * @return <code>true</code> if values were written, <code>false</code>
	 *         otherwise
	 */
	public boolean writeFrom(Object instance);

	/**
	 * Return the name of all keys within the data-store
	 *
	 * @return a {@link List} instance consisting of all property names within
	 *         the store
	 */
	public Collection<String> getAllKeys();

	/**
	 * Return the value associated with the property if found, or the provided
	 * default value.
	 *
	 * @param property
	 *            the property being looked for
	 *
	 * @param defaultValue
	 *            the default value
	 *
	 * @return the actual value if found, or the default value
	 */
	public String get(String property, String defaultValue);

	/**
	 * Store the given value in the data-store.
	 *
	 * @param key
	 *            the key to use
	 *
	 * @param property
	 *            the value of the property
	 */
	public void put(String key, Object property);

	/**
	 * Delete the value associated with the key in the data-store.
	 *
	 * @param key
	 *            the property name to delete
	 */
	public void delete(String key);

}
