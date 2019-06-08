package com.parakeet.msg.web;

import java.util.List;

public interface DBHelper {
	String getPass(String login);
	List<String> getLogins();
	void create(Login login);	
	void writeChat(Chat  ch);
	List<String> getLogins(String host);
	List<Chat> readChat(String host, String client);
	//void setClient(String client);
}
