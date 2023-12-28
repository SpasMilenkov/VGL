package com.jaba.vgl.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String message){
        super(message);
    }
}
