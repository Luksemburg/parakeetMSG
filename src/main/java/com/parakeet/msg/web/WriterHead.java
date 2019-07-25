package com.parakeet.msg.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.Gson;

@WebServlet("/writerhead")
public class WriterHead extends HttpServlet implements Sender {
	
	private final static Logger log = Logger.getLogger(WriterHead.class.getName());
	private static final long serialVersionUID = -8589279421799802526L;	
	
	HttpSession session;
	private String message;	
	private Chat chat = new DataChat();
	private  DBHelper helper;	
	
	private final static String empty = "";
	
	@Override
	public void setHelp(DBHelper helper){		
		this.helper = helper;
	}

	@Override
	public void write() {
		
		log.info("WRIIIIIITEEEEE!!!");
		String client = (String) session.getAttribute("client");
		String login = (String) session.getAttribute("login");
		int start_client = helper.countMsg(client, login);
		
		this.chat.setMessage(message);
		this.chat.setHost(login);
		
		if(client != null) {
			this.chat.setClient(client);	
		}else {
			session.setAttribute("client", "My Private Notes");
			this.chat.setClient("My Private Notes");
		}
		
		java.util.Date date= new java.util.Date();       		
		this.chat.setDateTime(new Timestamp(date.getTime()));
		
		if(!empty.equals(this.chat.getMessage())){
			helper.writeChat(this.chat);
		}		
		
		helper.toZero(login, client);
		
		
		if(!helper.isInUnread(client, login)) {
			helper.setUnread(client, login, helper.countMsg(client, login) - start_client);
			//log.info("INSERT IN UNREAD");					
		}else {
			helper.editUnread(client, login, helper.getUnread(client, login) + 1);			
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		session = request.getSession();			
		
		this.message = request.getParameter("msg");	
		if(this.message != null && !this.message.equals("\n")) {
			this.write();		
		}
		
		String redirectURL = "controller";		
		Map<String, String> data = new HashMap<>();
		data.put("redirect", redirectURL);
		String json = new Gson().toJson(data);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e1) {			
			e1.printStackTrace();
		}		
		
	}	
}
