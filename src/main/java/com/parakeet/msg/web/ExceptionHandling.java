package com.parakeet.msg.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
	@ExceptionHandler(value=Exception.class)
	  public String showErrorPage(){
	    return "error";
	  }
}
