package com.parakeet.msg.web;

public class DataLogin implements Login {
	String login;
	String pass;
	Chat[] chats;
	
	@Override
	public void setAttrs(String login, String pass) {		
		this.login = login;
		this.pass = pass;
	}

	@Override
	public String getLogin() {		
		return this.login;
	}

	@Override
	public String getPass() {		
		return this.pass;
	}
}
