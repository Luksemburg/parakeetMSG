package com.parakeet.msg.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/searchuser")
public class SearchUser extends HttpServlet {

	private static final long serialVersionUID = -4933237705500735342L;
	private final static Logger log = Logger.getLogger(SearchUser.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		session.setAttribute("patt", request.getParameter("pattern"));
		session.setAttribute("searchList", ((AdapterDB)session.getAttribute("helper")).searchLogins(request.getParameter("pattern")));
	}
}
