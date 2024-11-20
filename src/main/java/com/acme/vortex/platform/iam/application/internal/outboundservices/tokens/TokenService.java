package com.acme.vortex.platform.iam.application.internal.outboundservices.tokens;

/**
 * TokenService interface
 * This interface is used to generate anda validate tokens
 */
public interface TokenService {

    /**
     * Generate a token for a given username
     * @param username the username
     * @return String the token
     */
    String generateToken(String username);

    /**
     * Extract username from a token
     * @param token the token
     * @return String the username
     */
    String getUsernameFromToken(String token);

    /**
     * Validate a token
     * @param token the token
     * @return boolean true if the token is valid, false otherwise
     */
    boolean validateToken(String token);
}