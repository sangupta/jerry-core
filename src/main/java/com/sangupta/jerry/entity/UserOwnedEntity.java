package com.sangupta.jerry.entity;

/**
 * Contract for any entity that is owned by a user and thus access controls
 * should apply.
 * 
 * @author sangupta
 * 
 * @since 4.0.0
 * 
 */
public interface UserOwnedEntity {

    /**
     * Return the userID associated with this entity.
     * 
     * @return
     */
    public String getUserID();

    /**
     * Set the userID associated with this entity.
     * 
     * @param userID
     */
    public void setUserID(String userID);

}
