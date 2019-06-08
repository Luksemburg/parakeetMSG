package com.parakeet.msg.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/writerhead")
public class WriterHead extends HttpServlet implements Sender {
	
	private final static Logger log = Logger.getLogger(WriterHead.class.getName());
	private static final long serialVersionUID = -8589279421799802526L;
	
	HttpSession session;
			
	private String message;
	private Chat chat = new DataChat();
	private static DBHelper helper;
	
	//private  String old;
	//private  String old_client;	
	
	private final static String empty = "";
	
	@Override
	public void setHelp(DBHelper helper){		
		this.helper = helper;
	}

	@Override
	public void write() {
		this.chat.setMessage(message);
		this.chat.setHost((String) session.getAttribute("login"));
		this.chat.setClient((String) session.getAttribute("client"));	
		
		java.util.Date date= new java.util.Date();       		
		this.chat.setDateTime(new Timestamp(date.getTime()));
		
		if(!empty.equals(this.chat.getMessage())){
			helper.writeChat(this.chat);
		}		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();		
		this.message = request.getParameter("msg");		
		this.write();		
		
		request.getRequestDispatcher("/controller").forward(request, response);
		
	}

}
