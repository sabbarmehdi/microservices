package com.peaqock.users.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.String.format;

/**
 * This class represents a functional exception.
 */
@SuppressWarnings("squid:S1165")
@EqualsAndHashCode(callSuper = true)
public class FunctionalException extends RuntimeException {

    private static final long serialVersionUID = 4273322132185545866L;

    @Getter
    private final FunctionalErrorCode errorCode;

    FunctionalException(String messageTemplate, FunctionalErrorCode errorCode, Throwable cause, String... arguments) {
        super(format(messageTemplate, arguments), cause);
        this.errorCode = errorCode;
    }

    FunctionalException(FunctionalErrorCode errorCode, String... arguments) {
        this(errorCode.getMessageTemplate(), errorCode, null, arguments);
    }

}
