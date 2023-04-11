package com.KoreaIT.java.jam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 불러오기
		HttpSession session = request.getSession();
		
		// 현재 로그아웃 상태라고 가정
		boolean isLogined = false;
		int loginedMemberId = -1;
		String loginedMemberName = null;
		
		// 로그인 상태라면? 로그인 상태로 변경
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMemberName = (String) session.getAttribute("loginedMemberName");
		}
		
		// 로그인/로그아웃 상태 저장
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);		// 회원 번호
		request.setAttribute("loginedMemberName", loginedMemberName);	// 회원 이름
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}