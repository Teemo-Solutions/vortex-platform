package com.acme.vortex.platform.shared;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI vortexOpenAPI() {
        // General configuration
        var openApi = new OpenAPI();

        openApi.info(new Info()
                .title("Vortex Platform application REST API documentation.")
                .description("This is a Sprint Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Vortex Platform wiki documentation")
                        .url("https://vortex-platform.wiki.github.io/docs"));

        return openApi;
    }
}
