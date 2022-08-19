package com.customer.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class BussinessException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	private Date date;
	private String message;
	private String details;
	
	@Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
