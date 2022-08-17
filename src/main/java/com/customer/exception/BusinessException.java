package com.customer.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
    private String discription;
    private HttpStatus statuscode;

    public BusinessException(String discription, HttpStatus statuscode) {
         super(discription);
         this.discription = discription;
        this.statuscode = statuscode;
    }


}
