package com.parakeet.msg.web;

import java.util.List;
import java.util.logging.Logger;

public class Autorize implements Authentication {
	
	private final static Logger log = Logger.getLogger(Autorize.class.getName());

	public boolean verify(String pass1, String pass2) {
		if(pass1.equals(pass2)) {
			log.info("verify == true");			
			return true;
		}else {
			log.info("verify == false");
			return false;
		}
	}

	public boolean isNew(String login, List<String> logins) {
		for(int i = 0; i < logins.size(); i++) {
			if(login.equalsIgnoreCase(logins.get(i))) {
				return false;
			}
		}
		return true;
	}

}
