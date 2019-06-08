package com.parakeet.msg.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdapterDB implements DBHelper {
	
	private final static String url = "jdbc:postgresql://localhost:5432/parakeetdb?serverTimezone=Europe/Moscow&useSSL=false";
	private final static String user = "postgres";
	private final static String pass = "123456";
	private final static Logger log = Logger.getLogger(AdapterDB.class.getName());
	
	//private static String client;

	@Override
	public String getPass(String login) {	
		
		String res = "";
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		try {
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT pass FROM logins WHERE name = '" + login + "'");
			
			while(rs.next()) {
				res = rs.getString(1);	
				//log.info("RESULT SET: " + rs.getString(1));
			}
			
			rs.close();
			st.close();
			connect.close();
			
			//log.info("getPass success!");
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("getPass failed!");
		}
		
		return res;
	}
	
	@Override
	public List<String> getLogins(String host) {
		
		List<String> res = new ArrayList<String>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT client_login FROM messages WHERE host_login = '" + host + "'");
			
			while(rs.next()) {
					res.add(rs.getString(1));
					//log.info("RESULT SET: " + rs.getString(1));
			}
			
			
			rs.close();
			st.close();
			connect.close();
			
			//log.info("getLogins_host success!");			
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("getLogins_host false!");
		}
		
		return res;	
	}

	@Override
	public List<String> getLogins() {
	
		List<String> res = new ArrayList<String>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT name FROM logins");
			
			while(rs.next()) {
					res.add(rs.getString(1));
					//log.info("RESULT SET: " + rs.getString(1));
			}
			
			
			rs.close();
			st.close();
			connect.close();
			
			//log.info("getLogins success!");			
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("getLogins false!");
		}
		
		return res;		
	}

	@Override
	public void create(Login login) {
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO logins (name, pass) VALUES ('" + 
												login.getLogin() + "' , '" + login.getPass() + "')");						
			
			//log.info("Creating success! " + n + " string was added!");
			
			st.close();
			connect.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("Creating false!");
		}
	}

	@Override
	public List<Chat>  readChat(String host, String client) {
		
		List<Chat> res = new ArrayList<Chat>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM messages WHERE (host_login = '" + host + "' "
					 + " AND client_login = '" + client + "')" 
					 + " OR (host_login = '" + client + "' " 
					 + " AND client_login = '" + host 
					 + "') ORDER BY data_time");
			
			//log.info("--- Read Chat! --- ");
			
			while(rs.next()) {
				Chat chat = new DataChat();
				
				chat.setHost(rs.getString(1));
				chat.setClient(rs.getString(2));
				chat.setMessage(rs.getString(4));
				chat.setDateTime(rs.getTimestamp(3));
				
				res.add(chat);
				//log.info("CHAT: " + rs.getString(4));
			}
			
			
			rs.close();
			st.close();
			connect.close();
			
			//log.info("readChat success!");			
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("readChat false!");
		}
		
		return res;
	}

	@Override
	public void writeChat(Chat  ch) {
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			
			int n = st.executeUpdate("INSERT INTO messages (msg, data_time, client_login, host_login) VALUES ('" + 
					ch.getMessage() + "' , '" + ch.getDateTime() + "' , '"  + ch.getClient() + "' , '" + ch.getHost() + "')");						

			//log.info("Writing success! " + n + " string was added!");
			
			st.close();
			connect.close();
			
			//log.info("writeChat success!");			
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("writeChat false!");
		}

	}

/*	@Override
	public void setClient(String client) {
		this.client = client;
		
	}*/

}
