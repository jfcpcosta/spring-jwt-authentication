package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class InternalServerErrorResponse extends AbstractErrorResponse {

    public InternalServerErrorResponse(String message) {
        super(500, "Internal Server Error", message);
    }
}
