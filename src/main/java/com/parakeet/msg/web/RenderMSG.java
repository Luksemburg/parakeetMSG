package com.parakeet.msg.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/render")
public class RenderMSG extends HttpServlet implements LoginRenderer {

	private static final long serialVersionUID = 1194436370466237332L;
	private static List<String> logins;
	private static List<Chat> chats;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static DBHelper helper;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sender head = new WriterHead();
		head.setHelp(helper);		
		request.getRequestDispatcher("WEB-INF/classes/templates/render.jsp").forward(request, response);
	}
	
	
	public static List<String> getLogins(){
		return logins;
	}
	
	public static List<Chat> getChats(){
		return chats;
	}

	

	@Override
	public void render() {	
				
		try {
			doPost(request, response);
		} catch (ServletException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void setLogins(List<String> logins) {
		this.logins = logins;
	}

	@Override
	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	@Override
	public void setHttpParam(HttpServletRequest request, HttpServletResponse response) {		
		this.request = request;
		this.response = response;
	}

	@Override
	public void setDBHelper(DBHelper helper) {		
		this.helper = helper;
	}

}
