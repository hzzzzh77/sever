package com.example.server.exception;

public class PoiException extends RuntimeException{
    private PoiException(String msg) {
        super(msg);
    }

    public static PoiException NotFound(){
        return new PoiException("Not Found");
    }

    public static PoiException OperateFail(){
        return new PoiException("Operation Fail");
    }

    public static PoiException Unknown(){
        return new PoiException("unknown error");
    }
}
