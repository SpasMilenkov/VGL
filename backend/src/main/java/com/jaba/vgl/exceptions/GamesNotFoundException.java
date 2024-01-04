package com.jaba.vgl.exceptions;

import java.io.IOException;

public class GamesNotFoundException extends RuntimeException{
    public GamesNotFoundException(String message){
        super(message);
    }

    public GamesNotFoundException(String message, IOException e) {
    }
}
