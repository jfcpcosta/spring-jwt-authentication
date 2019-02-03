package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class CustomErrorResponse extends AbstractErrorResponse {

    public CustomErrorResponse(Integer status, String error, String message) {
        super(status, error, message);
    }
}
