package com.parakeet.msg.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends Throwable {
	
	private static final long serialVersionUID = -6304577656308220003L;

	@ExceptionHandler(value=Exception.class)
	  public String showErrorPage(){
	    return "error";
	  }
}
