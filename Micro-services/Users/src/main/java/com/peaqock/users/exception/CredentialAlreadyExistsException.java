package com.peaqock.users.exception;


import static com.peaqock.users.exception.FunctionalErrorCode.CREDENTIAL_ALREADY_USED;

public class CredentialAlreadyExistsException extends FunctionalException {

    private static final long serialVersionUID = -3372433568922641320L;

    public CredentialAlreadyExistsException(final String property) {
        super(CREDENTIAL_ALREADY_USED, property);
    }

}
