package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/right")
public class RightServe extends HttpServlet {

	private static final long serialVersionUID = -2562323650948055595L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
        	RenderMSG rend = (RenderMSG)session.getAttribute("r");
        	int to = rend.getChats().size();							
			int line = ((AdapterDB)session.getAttribute("helper")).getUnread((String)session.getAttribute("login"), (String)session.getAttribute("client"));
		
			for(int i = 0; i < to; i++){								
				
				if(to - i == line){
					out.println("<p align=\"center\"><font color=\"Red\">------------------------------------------- Unanswered Messages -------------------------------------------</font></p>");
				}
				
				if(rend.getLogins().indexOf(rend.getChats().get(i).getHost()) < 0){
					out.println("<p align=\"right\"><font color=\"SteelBlue\">");
				}			
											
				out.println("<br>" + rend.getChats().get(i).getMessage() + "<br>" + 
									"<font color=\"Silver\" size=\"1\">" + rend.formatTime(rend.getChats().get(i).getDateTime() ) + "</font><br>");
							
				if(rend.getLogins().indexOf(rend.getChats().get(i).getHost()) < 0){
					out.println("</font></p>");
				}			
			}
        	
        	//out.println("<p align=\"center\"><font color=\"Red\">------------------------------------------- Unanswered Messages -------------------------------------------</font></p>");
        } finally {
        	out.close();  
        }
	}
}
