package com.parakeet.msg.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private final static Logger log = Logger.getLogger(Controller.class.getName());
	private static final long serialVersionUID = -6343757452281131832L;
	private  String login;				
	private  String pass;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		
		if(request.getParameter("login") != null) {
			this.login = killCase(request.getParameter("login"));
			this.pass = request.getParameter("pass");
			
			session.setAttribute("login", this.login);
			session.setAttribute("pass", this.pass);	
			
		}else {
			try {
				this.login = (String) session.getAttribute("login");
				this.pass = (String) session.getAttribute("pass");
			}catch(Exception ex) {
				request.getRequestDispatcher("WEB-INF/classes/templates/error.jsp").forward(request, response);
			}
		}
		
		//log.info("/controller " + session.getAttribute("login"));
		
		ApplicationContext contex = new AnnotationConfigApplicationContext(Config.class);
		
		Logic logic = contex.getBean("logic", Logic.class);
		logic.setContext(contex);
		logic.setLogin(login);
		logic.setPass(pass);
		logic.setHttp(request, response);		
		
		logic.setClient((String) session.getAttribute("client"));		
		
		logic.working();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	
	public String killCase(String login) {	
		
		String res = "";
		String url = "jdbc:postgresql://ec2-54-217-219-235.eu-west-1.compute.amazonaws.com:5432/d8m9mskmbeol84?serverTimezone=Europe/Moscow&useSSL=false";
		String user = "euvwufgzacvxmw";
		String pass = "cd0d1bbbf84161d30cb52cab4ad733a0c53a675611e2940329fba1016a8c3c57";
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		try {
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT name FROM logins WHERE name ILIKE '" + login + "'");
			
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
}
