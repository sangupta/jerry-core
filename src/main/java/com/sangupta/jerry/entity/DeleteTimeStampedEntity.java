package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to track the soft-deletion time of itself
 * in the data store.
 * 
 * @author sangupta
 *
 */
public interface DeleteTimeStampedEntity {

    /**
     * Get the time when the entity was soft deleted.
     * 
     * @return
     */
    public long getDeleteTime();

    /**
     * Set the time when the entity was soft deleted.
     * 
     * @param creationTime
     */
    public void setDeleteTime(long deletionTime);

}
