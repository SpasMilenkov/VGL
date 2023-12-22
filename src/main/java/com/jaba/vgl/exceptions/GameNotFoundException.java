package com.jaba.vgl.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message){
        super(message);
    }
}
