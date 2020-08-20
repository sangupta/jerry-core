package com.sangupta.jerry.entity;

/**
 * Contract for any entity that allows soft-deletion than a
 * hard-deletion in the data store.
 * 
 * @author sangupta
 *
 * @since 4.0.0
 */
public interface SoftDeleteEntity {
    
    /**
     * Returns whether the entity is deleted or not.
     * 
     * @return
     */
    public boolean isDeleted();
    
    /**
     * Mark that the entity is now deleted.
     * 
     * @param deleted
     */
    public void setDeleted(boolean deleted);

}
