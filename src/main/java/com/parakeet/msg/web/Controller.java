package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private final static Logger log = Logger.getLogger(Controller.class.getName());
	private static final long serialVersionUID = -6343757452281131832L;
	private  String login;				
	private  String pass;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		
		if(request.getParameter("login") != null) {
			this.login = request.getParameter("login");
			this.pass = request.getParameter("pass");
			
			session.setAttribute("login", this.login);
			session.setAttribute("pass", this.pass);	
			
		}else {
			this.login = (String) session.getAttribute("login");
			this.pass = (String) session.getAttribute("pass");
		}
		
		log.info("/controller " + session.getAttribute("login"));
		
		ApplicationContext contex = new AnnotationConfigApplicationContext(Config.class);
		
		Logic logic = contex.getBean("logic", Logic.class);
		logic.setContext(contex);
		logic.setLogin(login);
		logic.setPass(pass);
		logic.setHttp(request, response);		
		
		logic.setClient((String) session.getAttribute("client"));		
		
		logic.working();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	

}
