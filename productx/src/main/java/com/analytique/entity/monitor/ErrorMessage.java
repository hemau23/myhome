package com.analytique.entity.monitor;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hemant on 9/9/2015.
 */
public class ErrorMessage  implements Serializable {

    private static final long serialVersionUID = -4990617176439462236L;

    @Id
    private String id;

    private String errorMessage;
    private Date createDate = new Date();
    private Object payload;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
