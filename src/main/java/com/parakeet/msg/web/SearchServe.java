package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/find")
public class SearchServe extends HttpServlet {

	private static final long serialVersionUID = -8922864549011079790L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
	
		response.setContentType("text/html");        
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
	   try {    
		   
		   	out.println("<!DOCTYPE html>");
			out.println("<head>");
			out.println("</head>");			
			out.println("<body id=\"res\">");
			
			if(session.getAttribute("searchList") != null){
				List<String> list = (List<String>)session.getAttribute("searchList");
				for(int i = 0; i < list.size(); i++){									
					out.println("<form accept-charset=\"utf-8\" method=\"POST\" action=\"setclient?client=" + list.get(i) + "\">");
					out.println("<a  href=\"getava/" + list.get(i) + "\" target=\"_blank\" >");
						out.println("<img src=\"getava/" + list.get(i) + "\" style=\"vertical-align:top\" width=\"41\" height=\"41\" alt=\"[ava]\" /></a>");
						out.println("<button style=\"width:290px; \"><font color=\"Yellow\" size=\"6\">");										
						out.println(list.get(i));
						out.println("</font></button>");
					out.println("</form>");	
				}
			}	
			
			out.println("</body>");
		   
	   } finally {
	   	out.close();  
	   }
	}
}
