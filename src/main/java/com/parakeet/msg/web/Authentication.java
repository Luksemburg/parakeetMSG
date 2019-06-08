package com.parakeet.msg.web;

import java.util.List;

public interface Authentication {	
	boolean verify(String pass1, String pass2);
	boolean isNew(String login, List<String> logins);	
}
