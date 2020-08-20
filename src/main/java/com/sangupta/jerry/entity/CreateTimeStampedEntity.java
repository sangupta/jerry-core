package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to track the creation time of itself in
 * the data store.
 * 
 * @author sangupta
 *
 * @since 4.0.0
 */
public interface CreateTimeStampedEntity {

    /**
     * Get the time when the entity was created.
     * 
     * @return
     */
    public long getCreateTime();

    /**
     * Set the time when the entity was created.
     * 
     * @param creationTime
     */
    public void setCreateTime(long creationTime);

}
