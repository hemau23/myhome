package com.analytique.exception;

/**
 * Created by hemant on 9/8/2015.
 */
public class AnalytiqueException  extends RuntimeException{

    private static final long serialVersionUID = -2706843730474712057L;

    public AnalytiqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalytiqueException(String message) {
        super(message);
    }
}
