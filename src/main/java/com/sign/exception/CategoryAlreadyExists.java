package com.sign.exception;

public class CategoryAlreadyExists extends RuntimeException{
    public CategoryAlreadyExists(String msg){
        super(msg);
    }
}
