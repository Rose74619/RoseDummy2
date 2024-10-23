package com.example.example.exception;

public class ResourceNotFound extends RuntimeException{
    ResourceNotFound(String message){
        super(message);
    }
}
