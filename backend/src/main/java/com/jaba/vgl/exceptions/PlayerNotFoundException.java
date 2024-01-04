package com.jaba.vgl.exceptions;

import java.io.IOException;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String message){
        super(message);
    }

    public PlayerNotFoundException(String message, IOException e) {
        super(message, e);
    }
}
