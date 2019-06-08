package com.parakeet.msg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

public interface Logic {
	void setContext(ApplicationContext contex);
	void working();
	void setLogin(String login);
	void setPass(String pass);
	void setHttp(HttpServletRequest request, HttpServletResponse response);
	void setClient(String client);
}
