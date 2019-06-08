package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;

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
	
	private static String login;
	private static String pass;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("login") != null) {
			this.login = request.getParameter("login");
			this.pass = request.getParameter("pass");
			//session.setAttribute("isComeIn", true);
			session.setAttribute("login", this.login);
			session.setAttribute("pass", this.pass);	
			
		}else {
			this.login = (String) session.getAttribute("login");
			this.pass = (String) session.getAttribute("pass");
		}
		
		
		ApplicationContext contex = new AnnotationConfigApplicationContext(Config.class);
		
		Logic logic = contex.getBean("logic", Logic.class);
		logic.setContext(contex);
		logic.setLogin(login);
		logic.setPass(pass);
		logic.setHttp(request, response);
		
		//if(session.getAttribute("client") != null) {
		logic.setClient((String) session.getAttribute("client"));
		//}
		
		logic.working();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>CONTROLLER!!! Please use POST method</h2>");
        } finally {
            writer.close();  
        }
        
        //this.doPost(request, response);
		/*
		HttpSession session = request.getSession();
		
		if(request.getParameter("login") != null) {
			this.login = request.getParameter("login");
			this.pass = request.getParameter("pass");
			//session.setAttribute("isComeIn", true);
		}else {
			this.login = (String) session.getAttribute("login");
			this.pass = (String) session.getAttribute("pass");
		}
		
		if(this.login != null) {			
			session.setAttribute("login", this.login);
			session.setAttribute("pass", this.pass);		
		}
		
		ApplicationContext contex = new AnnotationConfigApplicationContext(Config.class);
		
		Logic logic = contex.getBean("logic", Logic.class);
		logic.setContext(contex);
		logic.setLogin(login);
		logic.setPass(pass);
		logic.setHttp(request, response);
		
		//if(session.getAttribute("client") != null) {
		logic.setClient((String) session.getAttribute("client"));
		//}
		
		logic.working();
		*/
	}
	

}
