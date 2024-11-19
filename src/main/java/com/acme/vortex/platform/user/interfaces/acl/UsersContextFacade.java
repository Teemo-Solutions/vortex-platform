package com.acme.vortex.platform.user.interfaces.acl;

/**
 * Users Context Facade
 */
public interface UsersContextFacade {

    /**
     * Create a new user
     * @param name The name
     * @param email The email address
     * @param password The password
     * @param role The role
     * @param birthDate The birth date
     * @return The user ID
     */
    Long createUser(String name, String email, String password, String role, String birthDate);

    /**
     * Fetch a user ID by email
     * @param email The email address
     * @return The user ID
     */
    Long fetchUserIdByEmail(String email);
}
