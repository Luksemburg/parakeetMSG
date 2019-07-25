package com.parakeet.msg.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

public class Processing implements Logic {
	private ApplicationContext contex;
	private  String login;					
	private  String pass;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String client;
	
	private final static Logger log = Logger.getLogger(Processing.class.getName());
	
	private DaemonFace dFace;
	
	Processing(){
	}	
	
	public void working() {
		Authentication auth = contex.getBean("authent", Authentication.class);
		DBHelper dbHelper = contex.getBean("db_helper", DBHelper.class);
		
		List<String> lns = dbHelper.getLogins();		
		
		if(!auth.isNew(login, lns)) {			
			if(auth.verify(pass, dbHelper.getPass(login))) {
				
				List<Chat> chs = dbHelper.readChat(login, client);				
									
				LoginRenderer lr = contex.getBean("renderer", LoginRenderer.class);	
				
				lr.setLogins(dbHelper.getLogins(login));
				lr.setContext(contex);
				lr.setChats(chs);																					
				lr.setDBHelper(dbHelper);										
				lr.setHttpParam(request, response);
				
				dFace = contex.getBean("daemoncall", DaemonFace.class);
				dFace.setContext(contex);
				dFace.setHelp(dbHelper);
				dFace.setRender(lr);
				dFace.setHttpParam(request, response);
				
				if(lr != null) {
					request.setAttribute("r", lr);
					
					HttpSession session = request.getSession();
					session.setAttribute("r", lr);
				}
				
				lr.render();	
				
				log.info("Processing After Render!");
				

				
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