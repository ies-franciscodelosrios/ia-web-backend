package apirestful.iawebbackend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(jwtScheme()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API IA-WEB 2.0")
                .description(
                        "API para gestionar la jornada laboral de los trabajadores")
                .version("1.0")
                .contact(new Contact("Atmira", "https://atmira.com/", "marketing@atmira.com"))
                .build();
    }


    private SecurityScheme jwtScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER
                .name("JWT")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(defaultAuth()))
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build();
    }

    private SecurityReference defaultAuth() {
        return SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference("JWT")
                .build();
    }
}
