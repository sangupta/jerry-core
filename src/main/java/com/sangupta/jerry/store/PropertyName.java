package com.sangupta.jerry.store;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface PropertyName {

	/**
	 * @return the desired name of the field when it is serialized or deserialized into
	 * a {@link UserLocalStore}
	 */
	String value();

	String defaultValue();
	
}
