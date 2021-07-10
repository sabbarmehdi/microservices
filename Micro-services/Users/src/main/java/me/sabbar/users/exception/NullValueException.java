package me.sabbar.users.exception;

import java.util.List;

import static java.lang.String.join;

public class NullValueException extends FunctionalException {

    private static final long serialVersionUID = 4896893580503414663L;

    public NullValueException(String field) {
        super(FunctionalErrorCode.NOT_NULL_FIELD, field);
    }

    public NullValueException(List<String> fields) {
        super(FunctionalErrorCode.NOT_NULL_FIELDS, join(", ", fields));
    }


}
