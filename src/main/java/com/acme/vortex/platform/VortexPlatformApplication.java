package com.acme.vortex.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.acme.vortex.platform")
public class VortexPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(VortexPlatformApplication.class, args);
    }
    @Configuration
    public static class MyConfiguration {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**").allowedOrigins("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH" );
                }
            };
        }
    }
}
