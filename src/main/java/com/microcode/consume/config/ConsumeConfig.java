package com.microcode.consume.config;
import com.microcode.consume.services.ManageDocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumeConfig {

    @Bean
    public ManageDocumentService manageDocumentServices(
            @Value("${api.environment}") String apiEnvironment,
            @Value("${api.key}") String apiKey
    ) {
        return new ManageDocumentService(apiEnvironment,apiKey);
    }

}
