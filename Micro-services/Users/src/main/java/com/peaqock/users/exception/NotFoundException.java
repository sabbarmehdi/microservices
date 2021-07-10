package com.peaqock.users.exception;

import static com.peaqock.users.exception.FunctionalErrorCode.*;
import static java.lang.String.valueOf;

public class NotFoundException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String value) {
        super(USER_NOT_FOUND, value);
    }

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(Class<?> entityClass) {
        super(NOT_FOUND_ENTITY, entityClass.getSimpleName());
    }

    public NotFoundException(Class<?> entityClass, Long id) {
        super(NOT_FOUND_ENTITY_ID, entityClass.getSimpleName(), valueOf(id));
    }

    public NotFoundException(Class<?> entityClass, String value) {
        super(NOT_FOUND_ENTITY_VALUE, entityClass.getSimpleName(), value);
    }




}
