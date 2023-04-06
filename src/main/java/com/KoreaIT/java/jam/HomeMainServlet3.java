package com.KoreaIT.java.jam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printDan")
public class HomeMainServlet3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		// dan 이라는 파라미터 값 가져오기
		String inputedDan = request.getParameter("dan");
		String inputedLimit = request.getParameter("limit");
		String inputedColor = request.getParameter("color");
		
		// 파라미터 없을 시, 기본값 넣어주기
		if (inputedDan == null) {
			inputedDan = "1";
		}
		
		if (inputedLimit == null) {
			inputedLimit = "1";
		}
		
		if (inputedColor == null) {
			inputedColor = "black";
		}
		
		// 파라미터(문자열) 값을 숫자로 변환
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);
		
		// text/html 형식이므로 포맷팅 해주기
		response.getWriter().append(String.format("<div style='color: %s;'>==%d단==</div>", inputedColor, dan));
		
		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style='color: %s;'>%d * %d = %d</div>", inputedColor, dan, i, dan * i));
		}
	}
}
