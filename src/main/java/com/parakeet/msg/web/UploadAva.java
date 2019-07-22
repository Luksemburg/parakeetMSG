package com.parakeet.msg.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/update_ava")
public class UploadAva extends HttpServlet {

	private static final long serialVersionUID = -5742728285833565894L;
	private final static Logger log = Logger.getLogger(UploadAva.class.getName());
	
	//private final static String url = "jdbc:postgresql://localhost:5432/parakeetdb?serverTimezone=Europe/Moscow&useSSL=false";
	//private final static String user = "postgres";
	//private final static String pass = "123456";
	
	private final static String url = "jdbc:postgres://euvwufgzacvxmw:cd0d1bbbf84161d30cb52cab4ad733a0c53a675611e2940329fba1016a8c3c57@ec2-54-217-219-235.eu-west-1.compute.amazonaws.com:5432/d8m9mskmbeol84";
	private final static String user = "euvwufgzacvxmw";
	private final static String pass = "cd0d1bbbf84161d30cb52cab4ad733a0c53a675611e2940329fba1016a8c3c57";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			List<FileItem> multiparts = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
	        
			for (FileItem item : multiparts) {
	            if (!item.isFormField()) {
	            	byte[] temp = item.get();
	            	writeBytesToPSQL(temp, (String) session.getAttribute("login"));
	            }
	        }
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/controller").forward(request, response);
	}
	
	private void writeBytesToPSQL(byte[] arr, String avaLogin) {
		//log.info(Arrays.toString(arr));
		
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
				//update
				PreparedStatement ps = connect.prepareStatement("UPDATE ava SET picture = ? WHERE login = ?");
				ps.setBytes(1, arr);
				ps.setString(2, avaLogin);
				ps.executeUpdate();
				ps.close();
			}else {
				//insert				
				PreparedStatement ps = connect.prepareStatement("INSERT INTO ava (picture, login) VALUES (?, ?)");
				ps.setBytes(1, arr);
				ps.setString(2, avaLogin);
				ps.executeUpdate();
				ps.close();
			}
			
			rs.close();
			st.close();
			connect.close();
						
		}catch(Exception e) {
			e.printStackTrace();			
		}
	}
}
