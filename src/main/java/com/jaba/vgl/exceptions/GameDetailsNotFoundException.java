package com.jaba.vgl.exceptions;

public class GameDetailsNotFoundException extends RuntimeException{
    public GameDetailsNotFoundException(String message){
        super(message);
    }
}
