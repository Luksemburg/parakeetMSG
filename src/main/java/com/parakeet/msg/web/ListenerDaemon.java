package com.parakeet.msg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ListenerDaemon extends Runnable{
	void listen();
	void setParams(String login, String client);
	void setHelp(DBHelper helper);
	void setHttp(HttpServletRequest request, HttpServletResponse response);
	void setRender(LoginRenderer render);
	void setSession(HttpSession session);
	//boolean isUpdate();
}
