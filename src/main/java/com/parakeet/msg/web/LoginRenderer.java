package com.parakeet.msg.web;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

public interface LoginRenderer {
	void render();
	void setLogins(List<String> logins);
	void setChats(List<Chat> chats);
	void setHttpParam(HttpServletRequest request, HttpServletResponse response);
	void setDBHelper(DBHelper helper);
	void setContext(ApplicationContext context);
	String formatTime(Timestamp stamp);
}
