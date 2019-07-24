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
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        try {
        	RenderMSG rend = (RenderMSG)session.getAttribute("r");
        	int to = rend.getChats().size();							
			int line = ((AdapterDB)session.getAttribute("helper")).getUnread((String)session.getAttribute("login"), (String)session.getAttribute("client"));
			
			out.println("<!DOCTYPE html>");
			out.println("<head>");
			//out.println("<meta charset=\"utf-8\" />");
			out.println("<script src=\"https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/js/jquery-3.4.1.min.js\"></script>");
			out.println("</head>");
		
			out.println("<body id=\"scroll\">");
			
			for(int i = 0; i < to; i++){								
				
				if(to - i == line){
					out.println("<p align=\"center\"><font color=\"Red\">------------------------------------------- Unanswered Messages -------------------------------------------</font></p>");
				}
				
				//if(rend.getLogins().indexOf(rend.getChats().get(i).getHost()) < 0){
				if(rend.getChats().get(i).getHost().equals(session.getAttribute("login"))) {
					out.println("<p align=\"right\"><font color=\"SteelBlue\">");
				}else {
					out.println("<p align=\"left\"><font color=\"Black\">");
				}			
											
				out.println("<br>" + rend.getChats().get(i).getMessage() + "</font></p><br>" + 
									"<font color=\"Silver\" size=\"1\">" + rend.formatTime(rend.getChats().get(i).getDateTime() ) + "</font><br>");
							
				//if(rend.getLogins().indexOf(rend.getChats().get(i).getHost()) < 0){
					//out.println("</font></p>");
				//}			
			}
			
						
			out.println("<script type=\"text/javascript\">");
				out.println("$(document).ready(function(){");
					out.println("window.scrollTo(0,document.body.scrollHeight);");
				out.println("});");	
			out.println("</script>");
			
				
			out.println("</body>");
        	
        } finally {
        	out.close();  
        }
	}
}
