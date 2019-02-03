package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

import io.reativ.samples.springauthenticationtoken.core.http.responses.AbstractJsonResponse;

public abstract class AbstractErrorResponse extends AbstractJsonResponse {

    private String error;

    public AbstractErrorResponse(Integer status, String error, String message) {
        super(status, message);
        this.error = error;
    }

    public AbstractErrorResponse(String error, String message) {
        this(400, "Bad request", message);
        this.error = error;
    }

    public AbstractErrorResponse(String message) {
        this("Bad request", message);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
