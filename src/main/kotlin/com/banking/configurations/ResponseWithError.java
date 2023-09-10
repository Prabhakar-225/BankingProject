package com.banking.configurations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ResponseWithError <P>{
    private P response;
    private boolean error;
    private String errorMsg;
    private HttpStatus httpStatus;
    private ArrayList<HttpHeaders> headers;


    public ResponseWithError() {
    }

    private ResponseWithError(String errorMsg) {
        this.error = true;
        this.errorMsg = errorMsg;
    }
    private ResponseWithError(String errorMsg, HttpStatus httpStatus) {
        this.error = true;
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
    }

    private ResponseWithError(P response) {
        this.response = response;
        this.error=false;
    }
    public static <R> ResponseWithError<R> ofError(String errorMsg){
        return new ResponseWithError<>(errorMsg);
    }

    public static <R> ResponseWithError<R> ofError(String errorMsg, HttpStatus httpStatus){
        return new ResponseWithError<>(errorMsg,httpStatus);
    }

    public static <R> ResponseWithError<R> of(R response){
        return new ResponseWithError<>(response);
    }

    public P getResponse() {
        return response;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
