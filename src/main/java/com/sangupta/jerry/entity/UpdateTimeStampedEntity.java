package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to track the updated time of the entity
 * in the data store.
 * 
 * @author sangupta
 * 
 */
public interface UpdateTimeStampedEntity {

    public long getUpdated();

    public void setUpdated(long updatedTime);

}
