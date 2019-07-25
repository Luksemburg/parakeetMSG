package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/left")
public class LeftServe extends HttpServlet {

	private static final long serialVersionUID = 8031143537214272589L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
	
		response.setContentType("text/html");        
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
   try {    
	        String login = (String)session.getAttribute("login");
	        
	        RenderMSG rend = (RenderMSG)session.getAttribute("r");
	    	AdapterDB helper = (AdapterDB)session.getAttribute("helper");
	    	
	    	rend.setLogins(helper.getLogins(login));
	    	
	    	out.println("<!DOCTYPE html>");
			out.println("<head>");
			out.println("</head>");			
			out.println("<body id=\"coops\">");
			
			for(int i = 0; i < rend.getLogins().size(); i++){
				out.println("<form  accept-charset=\"utf-8\" method=\"POST\" action=\"setclient?client=" + rend.getLogins().get(i) + "\">");
					out.println("<div style=\"text-align:center\">");
					out.println("<a href=\"getava/" + rend.getLogins().get(i) + "\" target=\"_blank\" >");
						out.println("<img src=\"getava/" + rend.getLogins().get(i) + "\" width=\"41\" height=\"41\" style=\"vertical-align:top; \" alt=\"[ava]\" /></a>");	
						
						out.println("<button style=\"width:290px\"><font color=\"LimeGreen\" size=\"6\">");													
						out.println(rend.getLogins().get(i)); 

							int temp = helper.getUnread(login, rend.getLogins().get(i));
							if(temp > 0){	
								out.println("<font color=\"Red\"> +"	+ temp);
							}
							
						out.println("</font></font></button>");
					out.println("</div>");
				out.println("</form>");																	
			}
			
			out.println("</body>");
		
	} finally {
    	out.close();  
    }
	
	}

}
