package com.analytique.entity.monitor;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class CompletedMessage  implements Serializable {

    private static final long serialVersionUID = -4679862758855783481L;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
