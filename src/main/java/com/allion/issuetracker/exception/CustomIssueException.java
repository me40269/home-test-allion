package com.allion.issuetracker.exception;

public class CustomIssueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private Class exception;

    public CustomIssueException(String exceptionMsg) {
        super(exceptionMsg);
        this.message = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Class getException() {
        return exception;
    }

}
