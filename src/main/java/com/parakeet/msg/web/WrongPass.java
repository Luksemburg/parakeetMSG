package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wrongpass")
public class WrongPass extends HttpServlet implements Reject {

	private static final long serialVersionUID = -8326427833277746872L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/classes/templates/wrongpass.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>WRONGPASS PAGE!!! TRY TO USE POST METHOD</h2>");
        } finally {
            writer.close();  
        }*/
		//request.getRequestDispatcher("WEB-INF/classes/templates/wrongpass.jsp").forward(request, response);
	}
	
	public void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
