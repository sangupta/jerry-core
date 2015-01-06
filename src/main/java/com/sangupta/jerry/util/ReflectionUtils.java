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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

}
