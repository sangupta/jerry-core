package com.sangupta.jerry.transform;

/**
 * A generic processing contract that can help transform
 * an entity from one form to another.
 * 
 * @author sangupta
 *
 * @param <T> the original entity type
 * 
 * @param <X> the transformed entity type
 * 
 */
public interface Transformer<T, X> {

	public X transform(T entity);
	
}
