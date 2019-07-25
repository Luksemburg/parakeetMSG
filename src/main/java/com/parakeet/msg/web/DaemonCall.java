package com.parakeet.msg.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet("/daemoncall")
public class DaemonCall extends HttpServlet implements DaemonFace{	

	private static final long serialVersionUID = 6101475872035338824L;
	private static ApplicationContext contex;	
	private static DBHelper helper;
	private  LoginRenderer render;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final static Logger log = Logger.getLogger(DaemonCall.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			ListenerDaemon listener = new Daemon(); 
		
			HttpSession session = request.getSession(true);
			listener.setParams((String) session.getAttribute("login"), (String) session.getAttribute("client"));
			listener.setHttp(request, response);
			listener.setHelp(helper);
			listener.setRender(render);
			listener.setSession(session);
									
			log.info("/daemoncall: " + session.getAttribute("login"));
				
			listener.listen();				
			
			if(render != null) {
				session.setAttribute("r", render);			
				log.info("RENDER: " + render);
			}			
			
	}

	@Override
	public void setContext(ApplicationContext context) {
		contex = context;		
	}

	@Override
	public void setHelp(DBHelper helper) {		
		this.helper = helper;
	}

	@Override
	public void setRender(LoginRenderer render) {
		this.render = render;
		
	}

	@Override
	public void setHttpParam(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}
}
