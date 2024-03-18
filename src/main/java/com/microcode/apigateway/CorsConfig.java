package com.microcode.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration authCorsConfig = new CorsConfiguration();
        authCorsConfig.addAllowedOrigin("*");
        authCorsConfig.addAllowedMethod("*");
        authCorsConfig.addAllowedHeader("*");
        authCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/auth/**", authCorsConfig);

        CorsConfiguration consoleCorsConfig = new CorsConfiguration();
        consoleCorsConfig.addAllowedOrigin("http://localhost:5173");
        consoleCorsConfig.addAllowedOrigin("http://localhost:5174");
        consoleCorsConfig.addAllowedOrigin("http://localhost:5175");
        consoleCorsConfig.addAllowedMethod("*");
        consoleCorsConfig.addAllowedHeader("*");
        consoleCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/console/**", consoleCorsConfig);

        CorsConfiguration clientCorsConfig = new CorsConfiguration();
        clientCorsConfig.addAllowedOrigin("http://localhost:5173");
        clientCorsConfig.addAllowedOrigin("http://localhost:5174");
        clientCorsConfig.addAllowedOrigin("http://localhost:5175");
        clientCorsConfig.addAllowedMethod("*");
        clientCorsConfig.addAllowedHeader("*");
        clientCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/client/**", clientCorsConfig);

        CorsConfiguration authorizationCorsConfiguration = new CorsConfiguration();
        authorizationCorsConfiguration.addAllowedOrigin("http://localhost:5173");
        authorizationCorsConfiguration.addAllowedOrigin("http://localhost:5174");
        authorizationCorsConfiguration.addAllowedOrigin("http://localhost:5175");
        authorizationCorsConfiguration.addAllowedMethod("*");
        authorizationCorsConfiguration.addAllowedHeader("*");
        authorizationCorsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/authorization/**", authorizationCorsConfiguration);

        CorsConfiguration userCorsConfiguration = new CorsConfiguration();
        userCorsConfiguration.addAllowedOrigin("http://localhost:5173");
        userCorsConfiguration.addAllowedOrigin("http://localhost:5174");
        userCorsConfiguration.addAllowedOrigin("http://localhost:5175");
        userCorsConfiguration.addAllowedMethod("*");
        userCorsConfiguration.addAllowedHeader("*");
        userCorsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/user/**", userCorsConfiguration);

        CorsConfiguration mailCorsConfig = new CorsConfiguration();
        mailCorsConfig.addAllowedOrigin("http://localhost:5173");
        mailCorsConfig.addAllowedOrigin("http://localhost:5174");
        mailCorsConfig.addAllowedOrigin("http://localhost:5175");
        mailCorsConfig.addAllowedMethod("*");
        mailCorsConfig.addAllowedHeader("*");
        mailCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/mail/**", mailCorsConfig);


        CorsConfiguration documentCorsConfig = new CorsConfiguration();
        documentCorsConfig.addAllowedOrigin("http://localhost:5173");
        documentCorsConfig.addAllowedOrigin("http://localhost:5174");
        documentCorsConfig.addAllowedOrigin("http://localhost:5175");
        documentCorsConfig.addAllowedMethod("*");
        documentCorsConfig.addAllowedHeader("*");
        documentCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/document/**", documentCorsConfig);

        CorsConfiguration appProviderGeneral = new CorsConfiguration();
        appProviderGeneral.addAllowedOrigin("http://localhost:5173");
        appProviderGeneral.addAllowedOrigin("http://localhost:5174");
        appProviderGeneral.addAllowedOrigin("http://localhost:5175");
        appProviderGeneral.addAllowedMethod("*");
        appProviderGeneral.addAllowedHeader("*");
        appProviderGeneral.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/app-provider/general/**", appProviderGeneral);

        CorsConfiguration appProviderIndicator = new CorsConfiguration();
        appProviderIndicator.addAllowedOrigin("http://localhost:5173");
        appProviderIndicator.addAllowedOrigin("http://localhost:5174");
        appProviderIndicator.addAllowedOrigin("http://localhost:5175");
        appProviderIndicator.addAllowedMethod("*");
        appProviderIndicator.addAllowedHeader("*");
        appProviderIndicator.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/app-provider/indicator/**", appProviderIndicator);

        CorsConfiguration providerCorsConfig = new CorsConfiguration();
        providerCorsConfig.addAllowedOrigin("http://localhost:5173");
        providerCorsConfig.addAllowedOrigin("http://localhost:5174");
        providerCorsConfig.addAllowedOrigin("http://localhost:5175");
        providerCorsConfig.addAllowedMethod("*");
        providerCorsConfig.addAllowedHeader("*");
        providerCorsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/provider/**", providerCorsConfig);

        CorsConfiguration providerDocument = new CorsConfiguration();
        providerDocument.addAllowedOrigin("http://localhost:5173");
        providerDocument.addAllowedOrigin("http://localhost:5174");
        providerDocument.addAllowedOrigin("http://localhost:5175");
        providerDocument.addAllowedMethod("*");
        providerDocument.addAllowedHeader("*");
        providerDocument.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/provider-document/**", providerDocument);

        CorsConfiguration audit = new CorsConfiguration();
        audit.addAllowedOrigin("http://localhost:5173");
        audit.addAllowedOrigin("http://localhost:5174");
        audit.addAllowedOrigin("http://localhost:5175");
        audit.addAllowedMethod("*");
        audit.addAllowedHeader("*");
        audit.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/audit/**", audit);

        return new CorsWebFilter(source);
    }
}
