package com.jaba.vgl.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String message){
        super(message);
    }
}
