package br.com.escola.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bibliotecaOpenAPI() {
       return new OpenAPI()
        .info(new Info()
        .title("API de Catálogo de Livros e Autores")
        .version("1.0")
        .description("Trabalho API restful - Grupo 5 - Sistema de Biblioteca Simples"));
    }
}