package com.company.maboa.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Maboa Tech Shop API")
                        .version("1.0")
                        .description("API for Maboa Tech Shop. "
                            + "This API manages products, categories, and related operations for the online store, "
                            + "enabling creation, update, retrieval, and removal of products and categories. "
                            + "Designed for integration with e-commerce systems and Maboa Tech Shop front-end applications."));
    }
}