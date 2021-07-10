package me.sabbar.users.exception;


import static me.sabbar.users.exception.FunctionalErrorCode.EMAIL_ALREADY_EXISTE_EXCEPTION;

public class EmailAlreadyExisteException extends FunctionalException {

    private static final long serialVersionUID = 4896893580503414663L;

    public EmailAlreadyExisteException() {
        super(EMAIL_ALREADY_EXISTE_EXCEPTION);
    }


}
