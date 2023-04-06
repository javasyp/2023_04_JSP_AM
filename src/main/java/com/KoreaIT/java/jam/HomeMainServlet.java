package com.KoreaIT.java.jam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeMainServlet")
public class HomeMainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("Hello World?");
		
		String inputedDan = request.getParameter("dan");
		String inputedLimit = request.getParameter("limit");
		
		if (inputedDan == null) {
			inputedDan = "1";
		}
		
		if (inputedLimit == null) {
			inputedLimit = "1";
		}
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);
		
	}

}
