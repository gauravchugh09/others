package com.app.exceptionhandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHnadler{
	
	@ExceptionHandler(value = NullPointerException.class)
	public String nullPointerHandler(Exception e) {
		System.out.println(e.getMessage());
		return "nullPointer";
	}

	@ExceptionHandler(value = IOException.class)
	public String ioHnadler(Exception e) {
		System.out.println(e.getMessage());
		return "io";
	}

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String genericExceptioHandler(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		return "exception";
	}
}
