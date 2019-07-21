package com.parakeet.msg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

public interface DaemonFace {
	void setContext(ApplicationContext context);
	void setHelp(DBHelper helper);
	void setRender(LoginRenderer render);
	void setHttpParam(HttpServletRequest request, HttpServletResponse response);
}
