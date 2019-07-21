package com.parakeet.msg.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Daemon implements ListenerDaemon {
	
	private  String login;
	private  String client;
	private DBHelper helper;
	
	private  HttpServletRequest request;
	private  HttpServletResponse response;
	
	private HttpSession session;
	
	private  LoginRenderer render;		
	
	private final static Logger log = Logger.getLogger(Daemon.class.getName());
	
	public Daemon() {
		//log.info("DAEMOOOOOOOOOOONN!!!");
	}

	@Override
	public void run() {		
		listen();
	}

	@Override
	public void listen() {
		
		log.info("LISTEN!!!!");
		log.info("Login: " + login);
		
		String redirectURL = "controller";		
		
		Map<String, String> data = new HashMap<>();
		data.put("redirect", redirectURL);
		String json = new Gson().toJson(data);
		
		int start = helper.countMsg(login);		
		
		while(true) {
			
			if(start != helper.countMsg(login)) {								
				break;				
			}
			
			//SET TIMER
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
		}		
		
			render = new RenderMSG();
					
			render.setLogins(helper.getLogins(login));
			render.setChats(helper.readChat(login, client));			
					
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(json);
			} catch (IOException e1) {			
				e1.printStackTrace();
			}			
	}

	@Override
	public void setParams(String login, String client) {
		this.login = login;
		this.client = client;
	}

	@Override
	public void setHelp(DBHelper helper) {
		this.helper = helper;
		
	}

	@Override
	public void setHttp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}

	@Override
	public void setRender(LoginRenderer render) {
		this.render = render;
	}

	@Override
	public void setSession(HttpSession session) {
		this.session = session;		
	}


}
