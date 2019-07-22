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
	
	//private final static String url = "jdbc:postgresql://localhost:5432/parakeetdb?serverTimezone=Europe/Moscow&useSSL=false";
	//private final static String user = "postgres";
	//private final static String pass = "123456";
	
	//private final static String url = "jdbc:postgres://euvwufgzacvxmw:cd0d1bbbf84161d30cb52cab4ad733a0c53a675611e2940329fba1016a8c3c57@ec2-54-217-219-235.eu-west-1.compute.amazonaws.com:5432/d8m9mskmbeol84";
	private final static String url = "jdbc:postgresql://ec2-54-217-219-235.eu-west-1.compute.amazonaws.com:5432/d8m9mskmbeol84?serverTimezone=Europe/Moscow&useSSL=false";
	private final static String user = "euvwufgzacvxmw";
	private final static String pass = "cd0d1bbbf84161d30cb52cab4ad733a0c53a675611e2940329fba1016a8c3c57";
	
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
		String temp = "";
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("(SELECT client_login, data_time FROM messages WHERE " + 
												"host_login = '" + host + "' GROUP BY client_login, data_time) UNION "
														+ " (SELECT host_login, data_time FROM messages WHERE client_login = '" 
														+ host + "' GROUP BY host_login, data_time)"
														+ " ORDER BY data_time DESC");
			
			while(rs.next()) {
				if(!temp.contains(rs.getString(1) + " ")) {
					res.add(rs.getString(1));
					temp += rs.getString(1) + " ";
				}
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

			log.info("Writing success! " + n + " string was added!");
			
			st.close();
			connect.close();
			
			log.info("writeChat success!");			
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("writeChat false!");
		}

	}
	
	@Override
	public int countMsg(String login) {
		
		int n = -1;
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS quantity FROM messages WHERE client_login = '" + login + "'");
			
			while(rs.next()) {
				String temp = rs.getString("quantity");
				n = Integer.parseInt(temp);
			}
			
			rs.close();
			st.close();
			connect.close();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}		
			
		return n;
	}
	
	@Override
	public int countMsg(String target, String client) {
		
		int n = -1;
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			
			//ResultSet rs = st.executeQuery("SELECT COUNT(*) AS quantity FROM messages WHERE (client_login = '" + client + "' AND host_login = '" + target + "' ) OR ( " + 
			//															"client_login = '" + target + "' AND host_login = '" + client + "')");
		
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS quantity FROM messages WHERE client_login = '" + target + "' AND host_login = '" + client + "'");
			
			while(rs.next()) {
				String temp = rs.getString("quantity");
				n = Integer.parseInt(temp);
			}
			
			rs.close();
			st.close();
			connect.close();
			
			//log.info("countMsg success!");
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("countMsg false!");
		}
		
		//log.info("countMsg: n = " + n);
			
		return n;		
	}

	@Override
	public void toZero(String host, String client) {
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			st.executeUpdate("UPDATE unread SET newmsg = '" + 0 + "' WHERE client = '" + host + "' AND host = '" + client + "'");
						
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}		
	}

	@Override
	public int getUnread(String host, String client) {
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			ResultSet rs = st.executeQuery("SELECT newmsg FROM unread WHERE client = '" + host + "' AND host = '" + client + "'");
			
			if(rs.next()) {
				return rs.getInt(1);
			}		
			
			rs.close();
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return 0;
	}

	@Override
	public void setUnread(String host, String client, int quantity) {
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			st.executeUpdate("INSERT INTO unread (newmsg, client, host) VALUES ('" + quantity + "' , '" + host + "' , '" + client + "')");
						
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}
	}

	@Override
	public void editUnread(String host, String client, int quantity) {
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			st.executeUpdate("UPDATE unread SET newmsg = '" + quantity + "' WHERE client = '" + host + "' AND host = '" + client + "'");
						
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}
	}

	@Override
	public boolean isInUnread(String host, String client) {
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			ResultSet rs = st.executeQuery("SELECT newmsg FROM unread WHERE client = '" + host + "' AND host = '" + client + "'");
			
			if(rs.next()) {
				return true;
			}		
			
			rs.close();
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}

		return false;
	}
	
	@Override
	public List<String> searchLogins(String pattern) {
	
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
				String temp = rs.getString(1);
				
				if(!pattern.equalsIgnoreCase("") && temp.contains(pattern)) {
					res.add(temp);
				}
			}			
			
			rs.close();
			st.close();
			connect.close();					
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return res;		
	}

}
