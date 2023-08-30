package com.project2morrow.lapp.exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String msg){
        super(msg);
    }
}
