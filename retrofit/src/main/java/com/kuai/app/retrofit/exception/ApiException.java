package com.kuai.app.retrofit.exception;

/**
 * api 异常
 */
public class ApiException extends Exception{
    public ApiException(){
        super("api exception");
    }

    public ApiException(String msg){
        super(msg);
    }

    public ApiException(Throwable e){
        super(e);
    }
}
