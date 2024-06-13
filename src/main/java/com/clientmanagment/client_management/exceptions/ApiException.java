package com.clientmanagment.client_management.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
