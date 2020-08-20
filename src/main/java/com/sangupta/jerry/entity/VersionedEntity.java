package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to be versioned in the data store. A
 * versioned entity can only be updated if the incoming version is overwriting
 * and creating a newer version atomically.
 * 
 * @author sangupta
 * 
 * @since 4.0.0
 */
public interface VersionedEntity {

    public int getVersion();
    
}
