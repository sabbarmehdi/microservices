package me.sabbar.users.exception;

import static java.lang.String.valueOf;

public class NotFoundException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String value) {
        super(FunctionalErrorCode.USER_NOT_FOUND, value);
    }

    public NotFoundException() {
        super(FunctionalErrorCode.NOT_FOUND);
    }

    public NotFoundException(Class<?> entityClass) {
        super(FunctionalErrorCode.NOT_FOUND_ENTITY, entityClass.getSimpleName());
    }

    public NotFoundException(Class<?> entityClass, Long id) {
        super(FunctionalErrorCode.NOT_FOUND_ENTITY_ID, entityClass.getSimpleName(), valueOf(id));
    }

    public NotFoundException(Class<?> entityClass, String value) {
        super(FunctionalErrorCode.NOT_FOUND_ENTITY_VALUE, entityClass.getSimpleName(), value);
    }




}
