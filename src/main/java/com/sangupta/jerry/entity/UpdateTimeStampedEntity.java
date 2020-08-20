package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to track the updated time of the entity
 * in the data store.
 * 
 * @author sangupta
 * 
 * @since 4.0.0
 * 
 */
public interface UpdateTimeStampedEntity {

    public long getUpdateTime();

    public void setUpdateTime(long updatedTime);

}
