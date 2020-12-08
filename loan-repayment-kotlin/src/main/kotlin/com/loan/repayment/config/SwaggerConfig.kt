package com.loan.repayment.config

import com.fasterxml.classmate.TypeResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.OAuth2SchemeBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.AlternateTypeRules.newRule
import springfox.documentation.schema.WildcardType
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.ZonedDateTime

/**
 *
 * @author Rama chandra
 */
@Suppress("UNCHECKED_CAST")
@EnableSwagger2
@ConfigurationProperties(prefix = "security.oauth2.client")
class SwaggerConfig : WebMvcConfigurationSupport() {
    /**
     * Client ID.
     */
    @Value("{security.oauth2.client.client-id}")
    lateinit var clientId : String

    /**
     * Client Secret.
     */
    @Value("{security.oauth2.client.client-secret}")
    lateinit var clientSecret : String

    /**
     * Object that is used for resolving generic type information of a class so that
     * it is accessible using simple API. Resolved types are also starting point for
     * accessing resolved (generics aware) return and argument types of class
     * members (methods, fields, constructors).
     */
    @Autowired
    private lateinit var typeResolver : TypeResolver

    /**
     * A builder which is intended to be the primary interface into the Springfox
     * framework. Provides sensible defaults and convenience methods for
     * configuration.
     *
     * @return docket
     */
    @Bean
    fun planGeneratorApi() : Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.loan.repayment.controller"))
                .paths(regex("/generate-plan.*"))
                .build()
                .pathMapping("/")
                .directModelSubstitute(ZonedDateTime::class.java , String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .alternateTypeRules(newRule(
                        typeResolver.resolve(DeferredResult::class.java ,
                                typeResolver.resolve(ResponseEntity::class.java , WildcardType::class.java)) ,
                        typeResolver.resolve(WildcardType::class.java)))
                .useDefaultResponseMessages(false)
                .securitySchemes(listOf(securitySchema()))
                .securityContexts(listOf(securityContext()))
                .enableUrlTemplating(true)
                .globalRequestParameters(listOf(RequestParameter("Authorization" , ParameterType.HEADER ,
                        "Authorization Token" , null , null , null , null , null , null , 0 , null , 0)))
                .tags(Tag(APP_NAME , "Generate Repayment plans for a loan"))
                .apiInfo(apiInfo())
    }

    /**
     * This builds the Authentication Security Schema.
     *
     * @return oAuth2Scheme
     */
    private fun securitySchema() : OAuth2Scheme {
        return OAuth2SchemeBuilder("OAuth2 Authentication")
                .tokenUrl("http://localhost:8080/oauth/token").scopes(listOf(AuthorizationScope("read" , "read all") , AuthorizationScope("write" , "access all")))
                .build()
    }

    /**
     * This builds the Authentication Security Context for the API's.
     *
     * @return SecurityContext
     */
    private fun securityContext() : SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors
                        .ant("/**")).build()
    }

    /**
     * This method defines the default authorization scopes.
     *
     * @return List<SecurityReference>
    </SecurityReference> */
    private fun defaultAuth() : List<SecurityReference> {
        return listOf(element = SecurityReference("apikey" ,
                listOf(AuthorizationScope("read" , "read all") ,
                        AuthorizationScope("write" , "write all"))
                        .stream().toArray() as Array<out AuthorizationScope>?))
    }

    /**
     * This method defines the default security.
     *
     * @return SecurityConfiguration
     */
    @Bean
    fun security() : SecurityConfiguration {
        return SecurityConfigurationBuilder
                .builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .realm("OAuth2 Realm")
                .appName(APP_NAME)
                .scopeSeparator(",")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .enableCsrfSupport(false)
                .build()
    }

    /**
     * This methods generates the UI Layout to enable or disable layout items.
     *
     * @return UiConfiguration
     */
    @Bean
    fun uiConfig() : UiConfiguration {
        return UiConfigurationBuilder.builder().deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .showCommonExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS)
                .build()
    }

    /**
     * This method generates the Contact details.
     *
     * @return ApiInfo
     */
    private fun apiInfo() : ApiInfo {
        return ApiInfo(APP_NAME , "Generate Repayment plans for a loan" ,
                "1.0" , "http:localhost:8084" ,
                Contact("test" , "local" , "testemail@gmail.com") ,
                "Apache 2.0" ,
                "http://www.apache.org/licenses/LICENSE-2.0" , emptyList())
    }

    companion object {
        /**
         * Application Name.
         */
        private const val APP_NAME = "Plan Generator API"
    }
}