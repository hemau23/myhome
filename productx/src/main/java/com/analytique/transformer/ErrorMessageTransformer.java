package com.analytique.transformer;

import com.analytique.entity.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.transformer.GenericTransformer;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageTransformer implements GenericTransformer<MessagingException,ErrorMessage> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public ErrorMessage transform(MessagingException messagingException) {

        Throwable cause = messagingException.getMostSpecificCause();
        logger.error("MessagingException: " + cause.getMessage());

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(cause.getMessage());
        errorMessage.setPayload(messagingException.getFailedMessage().getPayload());
        return errorMessage;
    }
}
