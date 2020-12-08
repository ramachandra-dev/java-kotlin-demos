package com.loan.repayment.config;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.OAuth2SchemeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.OAuth2Scheme;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Ramachandra
 *
 */
@EnableSwagger2
@ConfigurationProperties(prefix = "security.oauth2.client")
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * Application Name.
     */
    private static final String APP_NAME = "Plan Generator API";

    /**
     * Client ID.
     */
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    /**
     * Client Secret.
     */
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    /**
     * Object that is used for resolving generic type information of a class so that
     * it is accessible using simple API. Resolved types are also starting point for
     * accessing resolved (generics aware) return and argument types of class
     * members (methods, fields, constructors).
     */
    @Autowired
    private TypeResolver typeResolver;

    /**
     * A builder which is intended to be the primary interface into the Springfox
     * framework. Provides sensible defaults and convenience methods for
     * configuration.
     *
     * @return docket
     */
    @Bean
    public Docket planGeneratorApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.loan.repayment.controller"))
                .paths(regex("/generate-plan.*")).build().pathMapping("/")
                .directModelSubstitute(ZonedDateTime.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(
                        typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false).securitySchemes(singletonList(securitySchema()))
                .securityContexts(singletonList(securityContext())).enableUrlTemplating(true)
                .globalRequestParameters(List.of(new RequestParameter("Authorization", ParameterType.HEADER,
                        "Authorization Token", null, null, null, null, null, null, 0, null, 0)))
                .tags(new Tag(APP_NAME, "Generate Repayment plans for a loan")).apiInfo(apiInfo());
    }

    /**
     * This builds the Authentication Security Schema.
     *
     * @return oAuth2Scheme
     */
    private OAuth2Scheme securitySchema() {
        return new OAuth2SchemeBuilder("OAuth2 Authentication")
                .tokenUrl("http://localhost:8080/oauth/token").scopes(List
                        .of(new AuthorizationScope("read", "read all"), new AuthorizationScope("write", "access all")))
                .build();
    }

    /**
     * This builds the Authentication Security Context for the API's.
     *
     * @return SecurityContext
     */
    @SuppressWarnings("deprecation")
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**")).build();
    }

    /**
     * This method defines the default authorization scopes.
     *
     * @return List<SecurityReference>
     */
    private List<SecurityReference> defaultAuth() {
        return singletonList(new SecurityReference("apikeys",
                List.of(new AuthorizationScope("read", "read all"), new AuthorizationScope("write", "write all"))
                        .stream().toArray(AuthorizationScope[]::new)));
    }

    /**
     * This method defines the default security.
     *
     * @return SecurityConfiguration
     */
    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().clientId(clientId).clientSecret(clientSecret)
                .realm("OAuth2 Realm").appName(APP_NAME).scopeSeparator(",")
                .useBasicAuthenticationWithAccessCodeGrant(true).enableCsrfSupport(false).build();
    }

    /**
     * This methods generates the UI Layout to enable or disable layout items.
     *
     * @return UiConfiguration
     */
    @Bean
    private UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE).filter(false).operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true).showCommonExtensions(false).tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS).build();
    }

    /**
     * This method generates the Contact details.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(APP_NAME, "Generate Repayment plans for a loan", "1.0", "http:localhost:8084",
                new Contact("test", "local", "testemail@gmail.com"), "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
