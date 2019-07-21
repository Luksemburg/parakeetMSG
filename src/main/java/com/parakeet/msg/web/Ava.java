package com.parakeet.msg.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getava/*")
public class Ava extends HttpServlet {

	private static final long serialVersionUID = 4995666542398735736L;
	private final static Logger log = Logger.getLogger(Ava.class.getName());
	
	private final static String url = "jdbc:postgresql://localhost:5432/parakeetdb?serverTimezone=Europe/Moscow&useSSL=false";
	private final static String user = "postgres";
	private final static String pass = "123456";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String avaLogin = request.getPathInfo().substring(1);
		log.info(avaLogin);
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();			
			
			ResultSet rs = st.executeQuery("SELECT picture FROM ava WHERE login = '" + avaLogin + "'");
			
			if(rs.next()) {			
				
				byte[] content = rs.getBytes(1);
                response.setContentType("image/png");
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
				
			}	else {				
				
				ResultSet rs2 = st.executeQuery("SELECT picture FROM ava WHERE login = 'default ava'");
				
				if(rs2.next()) {
					byte[] content = rs2.getBytes(1);
	                response.setContentType("image/png");
	                response.setContentLength(content.length);
	                response.getOutputStream().write(content);
				}
				
                rs2.close();
			}	
			
			rs.close();
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}
	}

}
