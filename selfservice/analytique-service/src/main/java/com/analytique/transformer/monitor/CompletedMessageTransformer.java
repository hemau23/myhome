package com.analytique.transformer.monitor;

import com.analytique.entity.monitor.CompletedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class CompletedMessageTransformer extends AbstractTransformer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected CompletedMessage doTransform(Message<?> message) throws Exception {
        logger.debug("Transforming Message: " + message + " into a CompletedMessage");

        // Extract the common fields
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();

        // Construct the CompletedMessage object
        CompletedMessage completedMessage = new CompletedMessage();


        // Return the CompletedMessage
        return completedMessage;
    }
}