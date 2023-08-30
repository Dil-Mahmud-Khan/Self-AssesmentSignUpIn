package com.project2morrow.lapp.exception;

public class CategoryAlreadyExists extends RuntimeException{
    public CategoryAlreadyExists(String msg){
        super(msg);
    }
}
