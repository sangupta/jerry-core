package com.sangupta.jerry.entity;

/**
 * Contract for any entity that is owned by a user and thus access controls
 * should apply.
 * 
 * @author sangupta
 * 
 */
public interface UserOwnedEntity {

    public String getUserID();

    public void setUserID(String userID);

}
