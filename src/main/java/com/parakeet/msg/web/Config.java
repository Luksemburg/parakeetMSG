package com.parakeet.msg.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class Config {
	
	@ExceptionHandler(ExceptionHandling.class)
	
	@Bean
	public Authentication authent() {
		return new Autorize();
	}
	
	@Bean
	public DBHelper db_helper() {
		return new AdapterDB();
	}
	
	@Bean
	public Logic logic() {
		return new Processing();
	}
	
	@Bean
	public LoginRenderer renderer() {
		return new RenderMSG();
	}
	
	@Bean
	public Reject reject() {
		return new WrongPass();
	}
	
	@Bean
	public Login login() {
		return new DataLogin();
	}
	
	@Bean
	public ListenerDaemon daemon() {
		return new Daemon();
	}
	
	@Bean
	public Chat chat() {
		return new DataChat();
	}
	
	@Bean
	public Sender writerhead() {
		return new WriterHead();
	}
	
	@Bean
	public DaemonFace daemoncall() {
		return new DaemonCall();
	}
}
