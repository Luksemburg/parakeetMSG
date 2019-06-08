package com.parakeet.msg.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/setclient")
public class SetClient extends HttpServlet {

	private final static Logger log = Logger.getLogger(SetClient.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();					
		//session.setAttribute("client", request.getParameter("client"));		
		//request.getRequestDispatcher("/controller").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();					
		session.setAttribute("client", request.getParameter("client"));		
		
		log.info(" =========== SET CLIENT");
		log.info((String) session.getAttribute("client"));
		
		request.getRequestDispatcher("/controller").forward(request, response);
		//request.getRequestDispatcher("/writerhead").forward(request, response);		
	}
}
