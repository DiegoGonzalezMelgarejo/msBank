package com.bank.diego.configuracion;


import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bank.diego.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        Contact developer = new Contact("Diego Gonzalez", "https://www.linkedin.com/in/diegogonzalez97/", "diegoaliriogm@gmail.com");
        return new ApiInfoBuilder().title("MS - BANK").description("Diego Gonzalez ").contact(developer).version("1.0").build();
    }




}