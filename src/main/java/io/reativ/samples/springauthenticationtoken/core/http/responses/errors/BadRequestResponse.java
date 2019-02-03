package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class BadRequestResponse extends AbstractErrorResponse {

    public BadRequestResponse(String message) {
        super(400, "Bad Request", message);
    }
}
