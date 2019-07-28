package com.parakeet.msg.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/read")
public class ReadServe extends HttpServlet {

	private static final long serialVersionUID = -8449943618556927643L;
	private final static Logger log = Logger.getLogger(ReadServe.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		AdapterDB helper = (AdapterDB) session.getAttribute("helper");
		String login = (String)session.getAttribute("login");
    	String client = (String)session.getAttribute("client");	
    	
    	log.info("Login: :" + login);
    	log.info("Client: :" + client);
    	log.info("Helper: :" + helper);

		helper.toZero(login, client);
		
		try {
			response.getWriter().write(0);
		} catch (IOException e1) {			
			e1.printStackTrace();
		}	
	}

}
