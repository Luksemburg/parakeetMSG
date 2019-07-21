package com.parakeet.msg.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/index.html")
public class Index extends HttpServlet {
	

	
	private static final long serialVersionUID = 4777357365859614561L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			request.getRequestDispatcher("WEB-INF/classes/templates/index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/classes/templates/index_logged.jsp").forward(request, response);
		}
	} 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			request.getRequestDispatcher("WEB-INF/classes/templates/index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/classes/templates/index_logged.jsp").forward(request, response);
		}
	} 
	
	public static void main(String[] args) {
		
	}
}
