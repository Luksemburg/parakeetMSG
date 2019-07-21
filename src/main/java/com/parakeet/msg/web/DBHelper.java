package com.parakeet.msg.web;

import java.util.List;

public interface DBHelper {
	String getPass(String login);
	List<String> getLogins();
	void create(Login login);	
	void writeChat(Chat  ch);
	List<String> getLogins(String host);
	List<Chat> readChat(String host, String client);	
	int countMsg(String target, String client);
	int countMsg(String login);
	
	void toZero(String host, String client);
	int getUnread(String host, String client);
	void setUnread(String host, String client, int quantity);
	void editUnread(String host, String client, int quantity);
	boolean isInUnread(String host, String client);
	List<String> searchLogins(String pattern);	
}
