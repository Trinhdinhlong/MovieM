package com.mocktest.exceptions;

public enum ErrorCode {
    ERROR_DATA_NOT_MATCH(1, "Data request not match!"),
    ERROR_DATE_NOT_MATCH(2, "date not match"),
    ERROR_DURATION_NOT_MATCH(3, "duration not match"),
    ERROR_PASSWORD_NOT_MATCH(4, "check password fail"),
    ERROR_MOVIE_ROOM_NOT_MATCH(5, "check movie and room fail"),
    ERROR_DB_NOT_FOUND(6, "Data not found in DB"),
    ERROR_NOT_FOUND_USERNAME(7, " account not found in DB"),
    ERROR_USER_NOT_FOUND(8, "user not found in DB"),
    ERROR_TYPE_NOT_FOUND(9, "type not found in DB"),
    ERROR_MOVIE_NOT_FOUND(10, "movie not found in DB"),
    ERROR_ROLE_NOT_FOUND(11, "role not found in DB"),
    ERROR_ROOM_NOT_FOUND(12, "room not found in DB"),
    ERROR_SEAT_NOT_FOUND(13, "seat not found in DB"),
    ERROR_SHOWTIME_NOT_FOUND(14, "showtime not found in DB"),
    ERROR_FORMAT_EMAIL(15, "email not format"),
    ERROR_FORMAT_PASSWORD(16, "password not format"),
    ERROR_FORMAT_PHONE(17, "phone not format"),
    ERROR_FORMAT_IDENTITY_CARD(18, "identity card not format"),
    ERROR_FORMAT_ACTOR(19, "actor not format"),
    ERROR_FORMAT_DIRECTOR(20, "director not format"),
    ERROR_FORMAT_MOVIE_PRODUCTION_COMPANY(21, "movie production company not format"),
    ERROR_ACCOUNT_EXIST(22, "account exist in DB"),
    ERROR_EMAIL_EXISTED(23, "email is existed"),
    ERROR_IDENTITY_CARD_EXISTED(24, "identity card is existed"),
    ERROR_PHONE_EXISTED(25, "phone is existed"),
    ERROR_CAN_NOT_UPDATE_TICKET(26, "can't update active ticket"),
    ERROR_MOVIENAME_NOT_MATCH(27, "Movie name has't in data")
    ;
    ErrorCode(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    private int errCode;
    private String message;

    public int getErrCode() {
        return errCode;
    }

    public String getMessage() {
        return message;
    }
}
