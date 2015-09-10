package com.analytique.config;

import com.analytique.entity.monitor.CompletedMessage;
import com.analytique.repository.monitor.CompletedMessageRepository;
import com.analytique.transformer.monitor.CompletedMessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class CompleteMessageConfig {



    @Autowired
    CompletedMessageTransformer completedMessageTransformer;

    @Autowired
    CompletedMessageRepository completedMessageRepository;

    @Bean
    public IntegrationFlow completedMessageFlow(){
        return IntegrationFlows.from(PropertiesConfig.COMPLETED_MGS_CHANNEL)
                .transform(completedMessageTransformer)
                .<CompletedMessage>handle((p,h) -> completedMessageRepository.save(p))
                .channel(IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME)
                .get();
    }
}
