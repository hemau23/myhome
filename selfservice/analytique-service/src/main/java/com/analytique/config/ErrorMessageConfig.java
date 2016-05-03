package com.analytique.config;

import com.analytique.entity.monitor.ErrorMessage;
import com.analytique.repository.monitor.ErrorMesssageRepository;
import com.analytique.transformer.monitor.ErrorMessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class ErrorMessageConfig {

    @Autowired
    ErrorMessageTransformer errorMessageTransformer;

    @Autowired
    ErrorMesssageRepository errorMesssageRepository;


    @Bean
    public IntegrationFlow errorChannelFlow() {
        return IntegrationFlows
                .from(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME)
                .transform(errorMessageTransformer)
                .<ErrorMessage>handle((p,h) -> errorMesssageRepository.save(p))
                .channel(IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME)
                .get();
    }
}
