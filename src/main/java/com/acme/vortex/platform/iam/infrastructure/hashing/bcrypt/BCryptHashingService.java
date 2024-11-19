package com.acme.vortex.platform.iam.infrastructure.hashing.bcrypt;

import com.acme.vortex.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This interface is a market interface for the BCrypt hashing service
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
