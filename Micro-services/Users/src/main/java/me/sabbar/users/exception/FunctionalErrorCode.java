package me.sabbar.users.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

/**
 * This enum represents the functional error codes.
 */
@Getter
public enum FunctionalErrorCode {

    BAD_REQUEST(0, HttpStatus.BAD_REQUEST, "Bad Request"),
    NOT_FOUND_ENTITY_ID(1, HttpStatus.NOT_FOUND, "No record of type %s and with id %s is present in the database"),
    NOT_FOUND_ENTITY_VALUE(1, HttpStatus.NOT_FOUND, "No record of type %s and with value : %s is present in the database"),
    NOT_FOUND_ENTITY(1, HttpStatus.NOT_FOUND, "No record of type %s found"),
    NOT_FOUND(1, HttpStatus.NOT_FOUND, "Not found"),
    NOT_FOUND_BY_VALUE(1, HttpStatus.NOT_FOUND, "No record of type %s and with value %s is present in the database"),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "Login inexist : %s"),
    NOT_NULL_ENTITY(3, HttpStatus.BAD_REQUEST, "Entity %s is required."),
    NOT_NULL_FIELD(4, HttpStatus.BAD_REQUEST, "The following field is required: %s"),
    JUST_MESSAGE(5, HttpStatus.BAD_REQUEST, "%s"),
    NOT_NULL_FIELDS(6, HttpStatus.BAD_REQUEST, "The following fields are required : %s"),
    MUST_BE_NULL_FIELD(7, HttpStatus.BAD_REQUEST, "The following fields must be null : %s"),
    INCORRECT_LENGTH_FIELD(8, HttpStatus.BAD_REQUEST, "The following field is not the right size : %s"),
    MIN_FIELD(9, HttpStatus.BAD_REQUEST, "The following field does not respect the minimum value : %s"),
    MAX_FIELD(10, HttpStatus.BAD_REQUEST, "The following field does not respect the maximum value : %s"),
    PATTERN_FIELD(11, HttpStatus.BAD_REQUEST, "The following field does not respect the correct format : %s"),
    NOT_BLANK_OR_EMPTY_FIELD(12, HttpStatus.BAD_REQUEST, "The following field is empty : %s"),
    INVALID_JWT_TOKEN(13, HttpStatus.UNAUTHORIZED, "Token JWT expired ou invalid"),
    CREDENTIAL_ALREADY_USED(14, HttpStatus.BAD_REQUEST, "%s Already exist"),
    PROPERTY_ALREADY_USED(15, HttpStatus.BAD_REQUEST, "%s Already used"),
    WRONG_PASSWORD(16, HttpStatus.BAD_REQUEST, "Password incorrect"),
    RECORD_ALREADY_EXISTS(17, HttpStatus.BAD_REQUEST, "Coupon code is already exist : %s ."),
    AUTHORIZATION_FAILED(18, HttpStatus.FORBIDDEN, "Authorization failed for access token request."),
    NO_COUPON_FOUND(19, HttpStatus.BAD_REQUEST, "No coupon code found in database."),
    COUPON_IS_NOT_VALIDATE(20, HttpStatus.BAD_REQUEST,"Coupon code is not valid or is expired,please try later."),
    EMAIL_ALREADY_EXISTE_EXCEPTION(21, HttpStatus.BAD_REQUEST,"Email already exist in database."),
    FILE_EXTENSION_NOT_ALLOWED(22, HttpStatus.BAD_REQUEST, "File extension not allowed : %s"),
    ERROR_CHARGE_CARD(23, HttpStatus.BAD_REQUEST, "An error occurred while trying to charge a client. : %s"),
    UPLOAD_ERROR(24, HttpStatus.BAD_REQUEST, "Error while uploading file : %s"),
    FILE_NOT_FOUND(25, HttpStatus.BAD_REQUEST, "file not found : %s"),
    DOWNLOAD_ERROR(26, HttpStatus.BAD_REQUEST, "Error while downloading file : %s"),
    ERROR_WHILE_SAVING_PAYMENT(27, HttpStatus.BAD_REQUEST, "error while saving payment : %s"),
    SENDING_MAIL_ERROR(28, HttpStatus.BAD_REQUEST, "Erreur while sending mail : %s"),
    USER_EMAIL_NOT_FOUND(29, HttpStatus.NOT_FOUND, "Not such a user with email %s exist in database"),
    WRONG_VALUE(30, HttpStatus.BAD_REQUEST, "Wrong value : %s"),
    NO_RESPONSE_URL(31, HttpStatus.BAD_REQUEST, "Enabe getting \"%s\" data from api."),
    CMS_DOCUMENT_EXIST_EXCEPTION(32, HttpStatus.BAD_REQUEST, "Cms document with key : \"%s\" already exist."),
    CMS_DOCUMENT_NOT_EXCEPTION(33, HttpStatus.BAD_REQUEST, "No such a document with key : \"%s\" exist."),
    CANT_UPDATE_SEGMENT(34, HttpStatus.BAD_REQUEST, "You already add a proposition. You can't update segment."),
    UNAUTHORIZED_SEGMENT_TIME(35, HttpStatus.BAD_REQUEST, "You don't have rights to make Segments/Propositions at this time"),
    WRONG_PASSWORD_FORMAT(36, HttpStatus.BAD_REQUEST, "Password wrong format."),
    PROPOSITION_ALREADY_EXISTE_EXCEPTION(37, HttpStatus.BAD_REQUEST,"Proposition already exist in database."),
    BUDGET_ALREADY_SAVED(38, HttpStatus.BAD_REQUEST,"Budget already saved."),
    UNAUTHORIZED_SEGMENT_SAVE(39, HttpStatus.BAD_REQUEST, "You don't have rights to save this segmentation"),
    AFFECTATION_ALREADY_EXIST(40, HttpStatus.BAD_REQUEST, "Affectation with centre gestion %s and country %s and marque %s already exist."),
    UNAUTHORIZED_PROPOSITION_VALID(41,HttpStatus.BAD_REQUEST,"You cannot validatee a proposition when it has expired"),
    UNAUTHORIZED_RIGHT(42,HttpStatus.BAD_REQUEST,"You don't have the right.")
    ;


    private final String code;
    private final HttpStatus httpStatus;
    private final String messageTemplate;

    FunctionalErrorCode(int code, HttpStatus httpStatus, String messageTemplate) {
        this.code = format("%03d", code);
        this.httpStatus = httpStatus;
        this.messageTemplate = messageTemplate;
    }
}
