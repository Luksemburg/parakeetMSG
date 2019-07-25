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
	
	@Override
	public boolean isMsgHealthy(String msg) {
		char[] testing = msg.toCharArray();
		for(int i = 0; i < testing.length; i++) {
			if(!Character.isWhitespace(testing[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String enterInsertSpaceDelete(String msg) {
		String res = "";
		
		char[] testing = msg.toCharArray();
		
		if(testing.length == 1) {
			if(Character.isWhitespace(testing[0])) {
				return null;
			}else {
				log.info("I0 = " + testing[0]);
				res = String.valueOf(testing[0]);
				return res;
			}
		}
		
		for(int i = 1; i < testing.length; i++) {
			
			if(!Character.isWhitespace(testing[i - 1]) || !Character.isWhitespace(testing[i])) {
				log.info("i = " + i + "; val = " + testing[i]);
				res += testing[i - 1];
			}
			
			if(i % 80 == 0) {
				res += "\n";
			}
		}
		
		return res;
	}
}
