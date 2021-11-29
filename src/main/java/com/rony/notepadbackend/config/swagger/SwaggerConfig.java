package com.rony.notepadbackend.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";


    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Diary Service")
                .description("Diary application")
                .version("v1")
                .contact(new Contact("Rony", "rony.com", "rony@gmail.com"))
                .build();
    }

    public Docket initializeDocket (String name, String regexp) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(name)
                .pathMapping("/")
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)

                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKeyScheme()))
                .useDefaultResponseMessages(false);

        docket.select().paths(PathSelectors.regex(regexp))
                .build();
        return docket;
    }

    private ApiKey apiKeyScheme() {
        return new ApiKey("Access Token", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> list = new ArrayList<>();
        list.add(new SecurityReference("Access Token", authorizationScopes()));
        return list;
    }

    private AuthorizationScope[] authorizationScopes() {
        AuthorizationScope readScope = new AuthorizationScope("read", "Read access");
        AuthorizationScope writeScope = new AuthorizationScope("write", "Write access");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = readScope;
        authorizationScopes[1] = writeScope;

        return authorizationScopes;
    }

    @Bean
    public Docket countryControllerDocket () {
        String regex = "/countries.*";
        return this.initializeDocket("Country", regex);
    }

    @Bean
    public Docket notesControllerDocket () {
        String regex = "/notes.*";
        return this.initializeDocket("Notes", regex);
    }

    @Bean
    public Docket authControllerDocket () {
        String regex = "/auth.*";
        return this.initializeDocket("Auth", regex);
    }
}
