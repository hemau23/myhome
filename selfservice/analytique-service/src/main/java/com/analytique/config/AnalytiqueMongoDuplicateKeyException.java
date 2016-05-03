package com.analytique.config;

import com.mongodb.DuplicateKeyException;

import java.io.Serializable;

import com.mongodb.MongoServerException;
import com.mongodb.WriteConcernResult;
import org.bson.BsonDocument;
import org.bson.BsonValue;

import static java.lang.String.*;

public class AnalytiqueMongoDuplicateKeyException extends MongoServerException {
    private static final long serialVersionUID = 7466099402959308525L;
    private BsonDocument response;
    private NucleusMongoWriteConcernResult writeConcernResult;

    public AnalytiqueMongoDuplicateKeyException(DuplicateKeyException dke) {
        super(DuplicateKeyException.extractErrorCode(dke.getResponse()),
                format("Write failed with error code %d and error message '%s'", DuplicateKeyException.extractErrorCode(dke.getResponse()), DuplicateKeyException.extractErrorMessage(dke.getResponse())),
                dke.getServerAddress());
        this.response = dke.getResponse();
        this.writeConcernResult = new NucleusMongoWriteConcernResult(dke.getWriteConcernResult());
    }

    public BsonDocument getResponse() {
        return response;
    }

    public NucleusMongoWriteConcernResult getWriteConcernResult() {
        return writeConcernResult;
    }

    public class NucleusMongoWriteConcernResult implements Serializable {
        private static final long serialVersionUID = -3440553897821532886L;
        private boolean wasAcknowledged;
        private int count;
        private boolean updateOfExisting;
        private BsonValue upsertedId;

        public NucleusMongoWriteConcernResult(WriteConcernResult writeConcernResult) {
            this.wasAcknowledged = writeConcernResult.wasAcknowledged();
            this.count = writeConcernResult.getCount();
            this.updateOfExisting = writeConcernResult.isUpdateOfExisting();
            this.upsertedId = writeConcernResult.getUpsertedId();
        }

        public boolean isWasAcknowledged() {
            return wasAcknowledged;
        }

        public int getCount() {
            return count;
        }

        public boolean isUpdateOfExisting() {
            return updateOfExisting;
        }

        public BsonValue getUpsertedId() {
            return upsertedId;
        }
    }
}