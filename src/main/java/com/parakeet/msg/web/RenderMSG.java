package com.parakeet.msg.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet("/render")
public class RenderMSG extends HttpServlet implements LoginRenderer {
	
	private final static Logger log = Logger.getLogger(RenderMSG.class.getName());

	private static final long serialVersionUID = 1194436370466237332L;
	private  List<String> logins;
	private  List<Chat> chats;
	private  HttpServletRequest request;
	private  HttpServletResponse response;
	private  DBHelper helper;
	private  ApplicationContext contex;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.getRequestDispatcher("WEB-INF/classes/templates/render.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		Sender head = contex.getBean("writerhead", Sender.class);			
		head.setHelp(helper);	
		
		try {
			HttpSession session = request.getSession();
			session.setAttribute("helper", helper);
			
			request.getRequestDispatcher("WEB-INF/classes/templates/render.jsp").forward(request, response);
		}catch(Exception e) {
			log.info("---");
		}
	}
	
	
	public  List<String> getLogins(){
		return logins;
	}
	
	
	public  List<Chat> getChats(){
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

	@Override
	public void setContext(ApplicationContext context) {
		contex = context;		
	}
	
	@Override
	public String formatTime (Timestamp stamp) {
		String res = "";
		SimpleDateFormat dateFormat = null;
		
		Calendar now = Calendar.getInstance();
		Calendar msg = Calendar.getInstance();
		msg.setTime(stamp);

		int month = now.get(Calendar.MONTH); 
		int day = now.get(Calendar.DAY_OF_MONTH);
		
		int msg_month = msg.get(Calendar.MONTH); 
		int msg_day = msg.get(Calendar.DAY_OF_MONTH);
		
			if(month == msg_month) {
				if(day == msg_day) {
					dateFormat = new SimpleDateFormat("HH:mm");
					res = dateFormat.format(stamp);
				}else {
					dateFormat = new SimpleDateFormat("dd MMM HH:mm");
					res = dateFormat.format(stamp);
				}
			}else {
				dateFormat = new SimpleDateFormat("y dd MMM HH:mm");
				res = dateFormat.format(stamp);
			}
		
		return res;
	}

}
