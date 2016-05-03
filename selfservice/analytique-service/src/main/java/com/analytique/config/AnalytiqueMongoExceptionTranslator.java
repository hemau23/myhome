package com.analytique.config;

import com.mongodb.DuplicateKeyException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;

public class AnalytiqueMongoExceptionTranslator extends MongoExceptionTranslator {

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        // override handling of the DuplicateKeyException with a fully serializable exception type.
        if (ex instanceof DuplicateKeyException) {
            return new org.springframework.dao.DuplicateKeyException(ex.getMessage(), new AnalytiqueMongoDuplicateKeyException((DuplicateKeyException) ex));
        }
        return super.translateExceptionIfPossible(ex);
    }
}
