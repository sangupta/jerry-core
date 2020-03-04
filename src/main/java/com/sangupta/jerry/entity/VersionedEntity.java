package com.sangupta.jerry.entity;

/**
 * Contract for any entity this is versioned and can only be updated if the
 * incoming version is overwriting and creating a newer version atomically.
 * 
 * @author sangupta
 * 
 */
public interface VersionedEntity {

    public int getVersion();

}
