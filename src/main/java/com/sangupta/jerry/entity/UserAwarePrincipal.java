package com.sangupta.jerry.entity;

import java.security.Principal;

/**
 * Contract for any entity that is a {@link Principal} and also is a
 * {@link UserOwnedEntity} to possess an ID that is different from name.
 * 
 * @author sangupta
 * 
 * @since 4.0.0
 *
 */
public interface UserAwarePrincipal extends UserOwnedEntity, Principal {

}
