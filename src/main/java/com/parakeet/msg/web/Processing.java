package com.parakeet.msg.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

public class Processing implements Logic {
	private static ApplicationContext contex;
	private static String login;
	private static String pass;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static String client;
	
	Processing(){
	}	
	
	public void working() {
		Authentication auth = contex.getBean("authent", Authentication.class);
		DBHelper dbHelper = contex.getBean("db_helper", DBHelper.class);
		
		//List<String> lns = new ArrayList<String>();		
		//List<Chat> chs = new ArrayList<Chat>();
		
		List<String> lns = dbHelper.getLogins();											//sort by data
		
		
		if(!auth.isNew(login, lns)) {			
			if(auth.verify(pass, dbHelper.getPass(login))) {
				
				List<Chat> chs = dbHelper.readChat(login, client);				//send all lns in renderer in cycle
				//dbHelper.setClient(this.client);
																		//
				LoginRenderer lr = contex.getBean("renderer", LoginRenderer.class);			//+++++
				lr.setLogins(dbHelper.getLogins(login));															//		
				lr.setChats(chs);															//
				lr.setHttpParam(request, response);												//
				lr.setDBHelper(dbHelper);
				lr.render();																//
			}else {
				Reject reject = contex.getBean("reject", Reject.class);						
				reject.show(request, response);																
			}
		}else {
			Login lg = contex.getBean("login", Login.class);
			lg.setAttrs(login, pass);
			dbHelper.create(lg);
		}
	}
	
	public ApplicationContext getContext() {
		return contex;
	}
	
	public void setContext(ApplicationContext contex) {
		this.contex = contex;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPass(String pass) {
		this.pass = pass;		
	}

	@Override
	public void setHttp(HttpServletRequest request, HttpServletResponse response) {		
		this.request = request;
		this.response = response;
	}

	@Override
	public void setClient(String client) {
		this.client = client;
		
	}
	
}