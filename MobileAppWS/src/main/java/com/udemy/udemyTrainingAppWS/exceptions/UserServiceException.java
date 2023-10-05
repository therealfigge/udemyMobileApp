package com.udemy.udemyTrainingAppWS.exceptions;

public class UserServiceException extends RuntimeException {
    private static final Long serialVersionUID = 4598234098L;
    public UserServiceException(String message) {
        super(message);
    }
}
