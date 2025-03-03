package com.company.topnews.exceptionHandler;

public class AppBadException extends RuntimeException{
    public AppBadException(String message) {
        super(message);
    }
}
