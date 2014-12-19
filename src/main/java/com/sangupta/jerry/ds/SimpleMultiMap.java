package com.sangupta.jerry.ds;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A very simple implementation of a Multi-Map for use that does not
 * implement the {@link Map} interface. It is useful for situations where
 * a very light-weight implementation is needed.
 * 
 * @author sangupta
 *
 * @param <K>
 * @param <V>
 */
public class SimpleMultiMap<K, V> {

	private final ConcurrentMap<K, List<V>> map = new ConcurrentHashMap<K, List<V>>();
	
	public int size() {
		return this.map.size();
	}
	
	public boolean isEmpty() {
		return this.map.isEmpty();
	}
	
	public boolean containsKey(K key) {
		return this.map.containsKey(key);
	}
	
	public List<V> getValues(K key) {
		return this.map.get(key);
	}
	
	public void put(K key, V value) {
		
	}
	
	public List<V> remove(K key) {
		return this.map.remove(key);
	}
	
	public void clear() {
		this.map.clear();
	}
	
	@Override
	public int hashCode() {
		return this.map.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.map.equals(obj);
	}
	
}
