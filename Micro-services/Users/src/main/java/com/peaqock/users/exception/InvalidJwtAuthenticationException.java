package com.peaqock.users.exception;

import static com.peaqock.users.exception.FunctionalErrorCode.INVALID_JWT_TOKEN;

public class InvalidJwtAuthenticationException extends FunctionalException {

    public InvalidJwtAuthenticationException() {
        super(INVALID_JWT_TOKEN);
    }
}
