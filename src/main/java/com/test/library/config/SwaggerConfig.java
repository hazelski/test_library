package com.test.library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Teste de aplicativo de Biblioteca")
                        .version("v1")
                        .description("Teste de aplicativo de Biblioteca")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .tags(List.of(
                        new Tag().name("Autor").description("Operações do Autor"),
                        new Tag().name("Livro").description("Operações do Livro")
                ));
    }
}
