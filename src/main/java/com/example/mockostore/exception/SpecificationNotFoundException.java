package com.example.mockostore.exception;

public class SpecificationNotFoundException extends RuntimeException{
    public SpecificationNotFoundException(String message){
        super(message);
    }
}
