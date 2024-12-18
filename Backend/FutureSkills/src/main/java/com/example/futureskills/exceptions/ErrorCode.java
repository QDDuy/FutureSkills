package com.example.futureskills.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999,"Uncategorize", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1002,"User existed",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(1005, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1006,"You dont have permission",HttpStatus.FORBIDDEN),
    ROLE_EXISTED(1007,"Role existed",HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(1008,"Password must be at least {min} characters",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1009,"Username must be at least {min} characters",HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1010,"Email is invalid",HttpStatus.BAD_REQUEST),
    DOB_INVALID(1011,"Your age must be at least {min}",HttpStatus.BAD_REQUEST),
    IS_LOGOUT(1012,"You logouted",HttpStatus.UNAUTHORIZED),;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
