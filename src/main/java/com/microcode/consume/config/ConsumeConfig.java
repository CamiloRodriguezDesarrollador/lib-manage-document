package com.microcode.consume.config;
import com.microcode.consume.services.ManageDocumentServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumeConfig {

    @Bean
    public ManageDocumentServices manageDocumentServices(
            @Value("${api.environment}") String apiEnvironment
    ) {
        return new ManageDocumentServices(apiEnvironment);
    }

}
