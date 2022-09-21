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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sangupta.jerry.store.PropertyName;

/**
 * Utility methods around object reflection.
 *
 * @author sangupta
 *
 */
public abstract class ReflectionUtils {
	
	protected ReflectionUtils() throws InstantiationException {
		throw new InstantiationException("Instances of this class are forbidden");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * Utility function to create the getter method name for a given attribute
	 * by converting the first character to uppercase and prefixing it with a
	 * get word.
	 *
	 * @param attribute
	 *            the name of the attribute for which the getter method is being
	 *            looked for
	 *
	 * @return the getter method name for the attribute
	 *
	 */
    public static String getGetterMethodName(String attribute) {
    	if(AssertUtils.isEmpty(attribute)) {
    		return null;
    	}

        String methodName = "get" + Character.toUpperCase(attribute.charAt(0)) + attribute.substring(1);
        return methodName;
    }

    /**
	 * Return the getter method for the given attribute name on the given object
	 * instance.
	 *
	 * @param instance
	 * 			  the {@link Object} instance for which the method is required
	 *
	 * @param attribute
	 *            the name of the attribute for which the getter method is being
	 *            looked for
	 *
	 * @return the {@link Method} instance or <code>null</code>
	 */
    public static Method getGetterMethod(Object instance, String attribute) {
    	if(instance == null) {
    		return null;
    	}

    	if(AssertUtils.isEmpty(attribute)) {
    		return null;
    	}

    	Method[] methods = instance.getClass().getMethods();
    	if(AssertUtils.isEmpty(methods)) {
    		return null;
    	}

    	String getterMethodName = getGetterMethodName(attribute);
    	for(Method method : methods) {
    		if(method.getName().equals(getterMethodName)) {
    			return method;
    		}
    	}

    	return null;
    }

    /**
	 * Method that invokes a getter method over the given object.
	 *
	 * @param object
	 *            object over which the getter method is to be invoked
	 *
	 * @param getterMethodName
	 *            the name of the getter method
	 *
	 * @return the return value of the getter method
	 *
	 * @throws IllegalArgumentException
	 *             if the supplied object param is <code>null</code>, or if
	 *             there is no getter method available on the object by the
	 *             given name.
	 *
	 * @throws IllegalAccessException
	 *             if there is an access violation when invoking the getter
	 *             method
	 *
	 * @throws InvocationTargetException
	 *             if there is an invocation exception during getter method call
	 *
	 */
    public static Object executeGetter(Object object, String getterMethodName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if(object == null) {
            throw new IllegalArgumentException("Object passed for executing getter method " + getterMethodName + " is NULL");
        }

        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName() != null && method.getName().equals(getterMethodName)) {
                return method.invoke(object, new Object[0]);
            }
        }

        throw new IllegalArgumentException("Unable to find a getter method on object by the name: " + getterMethodName);
    }

    /**
	 * Method that invokes a getter method over the given object for the given
	 * attribute name
	 *
	 * @param object
	 *            object over which the getter method is to be invoked
	 *
	 * @param attributeName
	 *            the name of the attribute for which the getter is to be
	 *            invoked
	 *
	 * @return the return value of the getter method
	 *
	 * @throws IllegalArgumentException
	 *             if the supplied object param is <code>null</code>, or if
	 *             there is no getter method available for the given
	 *             attributeName on the object
	 *
	 * @throws IllegalAccessException
	 *             if there is an access violation when invoking the getter
	 *             method
	 *
	 * @throws InvocationTargetException
	 *             if there is an invocation exception during getter method call
	 *
	 */
    public static Object executeGetAttribute(Object object, String attributeName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    	String getterMethodName = getGetterMethodName(attributeName);

    	return executeGetter(object, getterMethodName);
    }

    /**
	 * Check if the given field is transient or not.
	 *
	 * @param field
	 *            {@link Field} to be tested
	 *
	 * @return <code>true</code> if field is non-transient, <code>false</code>
	 *         if it is or the instance is <code>null</code>
	 */
    public static boolean isTransient(Field field) {
    	if(field == null) {
    		return false;
    	}

    	return Modifier.isTransient(field.getModifiers());
    }

    /**
	 * Bind the value as a {@link Boolean} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindBoolean(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Boolean) {
			field.setBoolean(instance, (Boolean) value);
		} else {
			field.setBoolean(instance, StringUtils.getBoolean(value.toString(), field.getBoolean(instance)));
		}
    }
    
    public static void bindBooleanObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Boolean) {
			field.set(instance, (Boolean) value);
		} else {
			Boolean boolValue = Boolean.parseBoolean(value.toString());
			field.set(instance, boolValue);
		}
    }

    /**
	 * Bind the value as a {@link Byte} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindByte(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Byte) {
			field.setByte(instance, (Byte) value);
		} else {
			field.setByte(instance, StringUtils.getByteValue(value.toString(), field.getByte(instance)));
		}
    }
    
    public static void bindByteObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Byte) {
			field.set(instance, (Byte) value);
		} else {
			Byte byteValue = Byte.parseByte(value.toString());
			field.set(instance, byteValue);
		}
    }

    /**
	 * Bind the value as a {@link Short} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindShort(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Short) {
			field.setShort(instance, (Short) value);
		} else {
			field.setShort(instance, StringUtils.getShortValue(value.toString(), field.getShort(instance)));
		}
    }
    
    public static void bindShortObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Short) {
			field.set(instance, (Short) value);
		} else {
			Short shortValue = Short.parseShort(value.toString());
			field.set(instance, shortValue);
		}
    }

    /**
	 * Bind the value as a {@link Integer} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindInteger(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value instanceof Integer) {
			field.setInt(instance, (Integer) value);
		} else {
			field.setInt(instance, StringUtils.getIntValue(value.toString(), field.getInt(instance)));
		}
    }
    
    public static void bindIntegerObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Integer) {
			field.set(instance, (Integer) value);
		} else {
			Integer intValue = Integer.parseInt(value.toString());
			field.set(instance, intValue);
		}
    }

    /**
	 * Bind the value as a {@link Long} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindLong(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Long) {
			field.setLong(instance, (Long) value);
		} else {
			field.setLong(instance, StringUtils.getLongValue(value.toString(), field.getLong(instance)));
		}
    }
    
    public static void bindLongObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Long) {
			field.set(instance, (Long) value);
		} else {
			Long longValue = Long.parseLong(value.toString());
			field.set(instance, longValue);
		}
    }

    /**
	 * Bind the value as a {@link Character} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindChar(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Character) {
			field.setChar(instance, (Character) value);
		} else {
			field.setChar(instance, StringUtils.getCharValue(value.toString(), field.getChar(instance)));
		}
    }
    
    public static void bindCharacterObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Character) {
			field.set(instance, (Character) value);
		} else {
			Character charValue = value.toString().toCharArray()[0];
			field.set(instance, charValue);
		}
    }

    /**
	 * Bind the value as a {@link Float} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindFloat(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Float) {
			field.setFloat(instance, (Float) value);
		} else {
			field.setFloat(instance, StringUtils.getFloatValue(value.toString(), field.getFloat(instance)));
		}
    }
    
    public static void bindFloatObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Float) {
			field.set(instance, (Float) value);
		} else {
			Float floatValue = Float.parseFloat(value.toString());
			field.set(instance, floatValue);
		}
    }

    /**
	 * Bind the value as a {@link Double} to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindDouble(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	if(value instanceof Double) {
			field.setDouble(instance, (Double) value);
		} else {
			field.setDouble(instance, StringUtils.getDoubleValue(value.toString(), field.getDouble(instance)));
		}
    }
    
    public static void bindDoubleObject(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}
    	
    	if(value == null) {
    		field.set(instance, null);
    		return;
    	}

    	if(value instanceof Double) {
			field.set(instance, (Double) value);
		} else {
			Double doubleValue = Double.parseDouble(value.toString());
			field.set(instance, doubleValue);
		}
    }

    /**
	 * Bind the value to the given field on the given object instance by detecting the
	 * correct field type, and converting the value to proper type if not directly in
	 * the format. Any {@link IllegalArgumentException} or {@link IllegalAccessException}
	 * is eaten up and logged.
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 */
    public static void bindValueQuiet(Field field, Object instance, Object value) {
    	try {
    		bindValue(field, instance, value);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Cannot set field " + field + "to value", e);
		} catch (IllegalAccessException e) {
			LOGGER.warn("Cannot set field " + field + "to value", e);
		}
    }

    /**
	 * Bind the value to the given field on the given object instance by detecting the
	 * correct field type, and converting the value to proper type if not directly in
	 * the format.
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindValue(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

    	Class<?> type = field.getType();

		// set accessible so that we can work with private fields
		field.setAccessible(true);

		if(type.equals(boolean.class)) {
			bindBoolean(field, instance, value);
			return;
		}
		if(type.equals(Boolean.class)) {
			bindBooleanObject(field, instance, value);
			return;
		}

		if(type.equals(byte.class)) {
			bindByte(field, instance, value);
			return;
		}
		if(type.equals(Byte.class)) {
			bindByteObject(field, instance, value);
			return;
		}

		if(type.equals(short.class)) {
			bindShort(field, instance, value);
			return;
		}
		if(type.equals(Short.class)) {
			bindShortObject(field, instance, value);
			return;
		}

		if(type.equals(char.class)) {
			bindChar(field, instance, value);
			return;
		}
		if(type.equals(Character.class)) {
			bindCharacterObject(field, instance, value);
			return;
		}

		if(type.equals(int.class)) {
			bindInteger(field, instance, value);
			return;
		}
		if(type.equals(Integer.class)) {
			bindIntegerObject(field, instance, value);
			return;
		}

		if(type.equals(long.class)) {
			bindLong(field, instance, value);
			return;
		}
		if(type.equals(Long.class)) {
			bindLongObject(field, instance, value);
			return;
		}

		if(type.equals(float.class)) {
			bindFloat(field, instance, value);
			return;
		}
		if(type.equals(Float.class)) {
			bindFloatObject(field, instance, value);
			return;
		}

		if(type.equals(double.class)) {
			bindDouble(field, instance, value);
			return;
		}
		if(type.equals(Double.class)) {
			bindDoubleObject(field, instance, value);
			return;
		}

		// check if this is an array and the value is of type String
		if(type.equals(byte[].class)) {
			bindByteArray(field, instance, value);
			return;
		}
		if(type.equals(char[].class)) {
			bindCharArray(field, instance, value);
			return;
		}
		if(type.equals(short[].class)) {
			bindShortArray(field, instance, value);
			return;
		}
		if(type.equals(int[].class)) {
			bindIntArray(field, instance, value);
			return;
		}
		if(type.equals(long[].class)) {
			bindLongArray(field, instance, value);
			return;
		}
		if(type.equals(float[].class)) {
			bindFloatArray(field, instance, value);
			return;
		}
		if(type.equals(double[].class)) {
			bindDoubleArray(field, instance, value);
			return;
		}
		if(type.equals(boolean[].class)) {
			bindBooleanArray(field, instance, value);
			return;
		}

		// just set the value
		field.set(instance, value);
    }

    /**
	 * Bind the value as a <code>byte[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindByteArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof byte[]) {
			field.set(instance, (byte[]) value);
		} else {
			if(value instanceof String) {
				byte[] array = StringUtils.deStringifyByteArray((String) value);
				field.set(instance, array);
			}
		}
	}

    /**
	 * Bind the value as a <code>char[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindCharArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof char[]) {
			field.set(instance, (char[]) value);
		} else {
			if(value instanceof String) {
				char[] array = StringUtils.deStringifyCharArray((String) value);
				field.set(instance, array);
			}
		}
	}

    /**
	 * Bind the value as a <code>short[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindShortArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof short[]) {
			field.set(instance, (short[]) value);
		} else {
			if(value instanceof String) {
				short[] array = StringUtils.deStringifyShortArray((String) value);
				field.set(instance, array);
			}
		}
	}

    /**
	 * Bind the value as a <code>int[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindIntArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof int[]) {
			field.set(instance, (int[]) value);
		} else {
			if(value instanceof String) {
				int[] array = StringUtils.deStringifyIntArray((String) value);
				field.set(instance, array);
			}
		}
	}

    /**
	 * Bind the value as a <code>long[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
    public static void bindLongArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof long[]) {
			field.set(instance, (long[]) value);
		} else {
			if(value instanceof String) {
				long[] array = StringUtils.deStringifyLongArray((String) value);
				field.set(instance, array);
			}
		}
	}

    /**
	 * Bind the value as a <code>float[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
	public static void bindFloatArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof float[]) {
			field.set(instance, (float[]) value);
		} else {
			if(value instanceof String) {
				float[] array = StringUtils.deStringifyFloatArray((String) value);
				field.set(instance, array);
			}
		}
	}

	/**
	 * Bind the value as a <code>double[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
	public static void bindDoubleArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof double[]) {
			field.set(instance, (double[]) value);
		} else {
			if(value instanceof String) {
				double[] array = StringUtils.deStringifyDoubleArray((String) value);
				field.set(instance, array);
			}
		}
	}

	/**
	 * Bind the value as a <code>boolean[]</code> to the given field on the given
	 * object instance
	 *
	 * @param field
	 *            the {@link Field} to bind to
	 *
	 * @param instance
	 *            the Object instance to bind to
	 *
	 * @param value
	 *            the value to be bound
	 *
	 * @throws IllegalArgumentException
	 *             if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof), or if an unwrapping conversion fails.
	 *
	 * @throws IllegalAccessException
	 *             if this {@link Field} object is enforcing Java language
	 *             access control and the underlying field is either
	 *             inaccessible or final.
	 */
	public static void bindBooleanArray(Field field, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    	if(field == null) {
    		return;
    	}

    	if(instance == null) {
    		return;
    	}

		if(value instanceof boolean[]) {
			field.set(instance, (boolean[]) value);
		} else {
			if(value instanceof String) {
				boolean[] array = StringUtils.deStringifyBooleanArray((String) value);
				field.set(instance, array);
			}
		}
	}

	/**
	 * Convert the object instance into a {@link Map} where keys are the field
	 * names, and values are represented as {@link Object}.
	 *
	 * It also takes care of the {@link PropertyName} annotation, and converts
	 * the property names to them if provided.
	 *
	 * @param instance
	 *            the instance to be converted
	 *
	 * @param includeTransientFields
	 *            set this to <code>true</code> if you would like to dump the
	 *            values of transient variables as well.
	 *
	 * @return {@link Map} instance containing all properties, <code>null</code>
	 *         if the instance is <code>null</code>
	 */
	public static Map<String, Object> convertToMap(Object instance, boolean includeTransientFields) {
		if(instance == null) {
			return null;
		}

		final Map<String, Object> map = new HashMap<String, Object>();

		Field[] fields = instance.getClass().getDeclaredFields();
		if(AssertUtils.isEmpty(fields)) {
			return map;
		}

		for(Field field : fields) {
			// set accessible
			field.setAccessible(true);

			// skip if field is transient
			if(!includeTransientFields) {
				if(isTransient(field)) {
					continue;
				}
			}

			// skip the this variable
			if(field.getName().equals("this$0")) {
				// skip this field
				continue;
			}

			// check if fields has the annotation of property name
			PropertyName propertyName = field.getAnnotation(PropertyName.class);

			// get name to save to
			String name;
			if(propertyName != null) {
				name = propertyName.value();
			} else {
				name = field.getName();
			}

			// read the fields value
			Object value = null;
			try {
				value = field.get(instance);
			} catch (IllegalArgumentException e) {
				LOGGER.error("Unable to read value for field: " + field, e);
			} catch (IllegalAccessException e) {
				LOGGER.error("Unable to read value for field: " + field, e);
			}

			// save the value
			map.put(name, value);
		}

		return map;
	}
	
	/**
	 * Obtain the value of the field defined by <code>name</code> on the given
	 * object <code>instance</code> and cast the returned value to the given
	 * <code>castTo</code> class.
	 * 
	 * @param <T>
	 *            the {@link Class} type of the expected field value
	 * 
	 * @param instance
	 *            the object instance to obtain field value from
	 * 
	 * @param name
	 *            the name of the attribute being requested
	 * 
	 * @param castTo
	 *            the {@link Class} to which the returned value needs to be cast
	 * 
	 * @return the field value, or <code>null</code> if the object instance is
	 *         <code>null</code> or field name is <code>null/empty</code> or if
	 *         the field does not exist on the object.
	 * 
	 * @throws RuntimeException
	 *             if {@link IllegalArgumentException} or
	 *             {@link IllegalAccessException} is thrown during reflection
	 */
	public static <T> T getFieldForName(Object instance, String name, Class<T> castTo) {
		if(instance == null) {
			return null;
		}
		
		if(AssertUtils.isEmpty(name)) {
			return null;
		}
		
		Class<?> clazz = instance.getClass();
		List<Field> fields = getAllFields(clazz);
		if(fields.isEmpty()) {
			return null;
		}
		
		for(Field field : fields) {
			if(field.getName().equals(name)) {
				try {
					field.setAccessible(true);
					Object object = field.get(instance);
					if(object == null) {
						return null;
					}
					
					return castTo.cast(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return null;
	}

	/**
	 * Get a list of all {@link Field}s that are defined by the given
	 * {@link Class} and all its parent classes, including all private fields
	 * 
	 * @param clazz
	 *            the {@link Class} to obtain fields from
	 * 
	 * @return the {@link List} of all {@link Field}s. If the supplied class is
	 *         <code>null</code>, an empty {@link List} is returned
	 */
	public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        populateAllFields(clazz, fields);
        return fields;
    }
    
	/**
	 * Populate the list of all {@link Field}s that are defined by the given
	 * {@link Class} and all its parent classes, including all private fields,
	 * in the given {@link List} instance.
	 * 
	 * @param clazz
	 *            the {@link Class} to obtain fields from
	 * @param fields
	 *            the {@link List} to populate fields into
	 */
    public static void populateAllFields(Class<?> clazz, List<Field> fields) {
        if(clazz == null) {
            return;
        }
        
        Field[] array = clazz.getDeclaredFields();
        if(array != null && array.length > 0) {
            fields.addAll(Arrays.asList(array));
        }
        
        if(clazz.getSuperclass() == null) {
            return;
        }
        
        populateAllFields(clazz.getSuperclass(), fields);
    }

	public static boolean isFieldNumber(Class<?> type) {
		return true;
	}

	public static boolean isEqualNumber(Object srcValue, int i) {
		return false;
	}

}
