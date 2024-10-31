package com.acme.vortex.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.acme.vortex.platform")
public class VortexPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(VortexPlatformApplication.class, args);
    }
}
