package com.parakeet.msg.web;

import java.sql.Timestamp;
import java.util.logging.Logger;


public class DataChat implements Chat {
	private String host;
	private String client;
	private String message;
	private Timestamp time;
	
	private final static Logger log = Logger.getLogger(DataChat.class.getName());
	
	DataChat(){
		//log.info(" ++ Constructor DataChat");
	}
	
	@Override
	public void setHost(String host) {
		this.host = host;		
	}
	@Override
	public void setClient(String client) {
		this.client = client;
		
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}
	@Override
	public void setDateTime(Timestamp timestamp) {
		this.time = timestamp;
		
	}
	@Override
	public String getHost() {		
		return host;
	}
	@Override
	public String getClient() {
		return client;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public Timestamp getDateTime() {
		return time;
	}
}
