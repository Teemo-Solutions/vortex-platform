package com.acme.vortex.platform.iam.application.internal.outboundservices.hashing;

/**
 * Hashing interface
 * This interface is used to encode and match passwords
 */
public interface HashingService {

    /**
     * Encode a password
     * @param rawPassword the password to encode
     * @return String the encoded password
     */
    String encode(CharSequence rawPassword);

    /**
     * Match a raw password with an encoded password
     * @param rawPassword the raw password
     * @param encodedPassword the encoded password
     * @return boolean true if the raa password matches the encoded password, false otherwise
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
