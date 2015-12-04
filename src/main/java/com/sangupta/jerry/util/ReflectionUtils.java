/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Utility methods around object reflection.
 * 
 * @author sangupta
 *
 */
public class ReflectionUtils {
	
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
    public static String getGetterMethod(String attribute) {
        String methodName = "get" + Character.toUpperCase(attribute.charAt(0)) + attribute.substring(1);
        return methodName;
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
    	String getterMethodName = getGetterMethod(attributeName);

    	return executeGetter(object, getterMethodName);
    }

    public static boolean isTransient(Field field) {
    	if(field == null) {
    		return false;
    	}
    	
    	return Modifier.isTransient(field.getModifiers());
    }
    
    public static void bindValue(Field field, Object instance, Object value) {
    	if(field == null) {
    		return;
    	}
    	
    	if(instance == null) {
    		return;
    	}
    	
    	Class<?> type = field.getType();
		
		try {
			if(type.equals(boolean.class)) {
				if(value instanceof Boolean) {
					field.setBoolean(instance, (Boolean) value);
				} else {
					field.setBoolean(instance, StringUtils.getBoolean(value.toString(), field.getBoolean(instance)));
				}
			}

			if(type.equals(byte.class)) {
				if(value instanceof Byte) {
					field.setByte(instance, (Byte) value);
				} else {
					field.setByte(instance, StringUtils.getByteValue(value.toString(), field.getByte(instance)));
				}
			}
			
			if(type.equals(short.class)) {
				if(value instanceof Short) {
					field.setShort(instance, (Short) value);
				} else {
					field.setShort(instance, StringUtils.getShortValue(value.toString(), field.getShort(instance)));
				}
			}
			
			if(type.equals(char.class)) {
				if(value instanceof Character) {
					field.setChar(instance, (Character) value);
				} else {
					field.setChar(instance, StringUtils.getCharValue(value.toString(), field.getChar(instance)));
				}
			}
			
			if(type.equals(int.class)) {
				if(value instanceof Integer) {
					field.setInt(instance, (Integer) value);
				} else {
					field.setInt(instance, StringUtils.getIntValue(value.toString(), field.getInt(instance)));
				}
			}
			
			if(type.equals(long.class)) {
				if(value instanceof Long) {
					field.setLong(instance, (Long) value);
				} else {
					field.setLong(instance, StringUtils.getLongValue(value.toString(), field.getLong(instance)));
				}
			}
			
			if(type.equals(float.class)) {
				if(value instanceof Float) {
					field.setFloat(instance, (Float) value);
				} else {
					field.setFloat(instance, StringUtils.getFloatValue(value.toString(), field.getFloat(instance)));
				}
			}

			if(type.equals(double.class)) {
				if(value instanceof Double) {
					field.setDouble(instance, (Double) value);
				} else {
					field.setDouble(instance, StringUtils.getDoubleValue(value.toString(), field.getDouble(instance)));
				}
			}

			// just set the value
			field.set(instance, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
