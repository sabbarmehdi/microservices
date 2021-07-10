package me.sabbar.users.exception;


public class CredentialAlreadyExistsException extends FunctionalException {

    private static final long serialVersionUID = -3372433568922641320L;

    public CredentialAlreadyExistsException(final String property) {
        super(FunctionalErrorCode.CREDENTIAL_ALREADY_USED, property);
    }

}
