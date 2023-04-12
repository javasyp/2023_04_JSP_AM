package com.KoreaIT.java.jam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/doLogout")
public class MemberDoLogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		// 로그인 상태 체크
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해 주세요.'); location.replace('../member/login');</script>"));
			return;
		}
		
		session = request.getSession();
		// 저장된 속성 제거
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");
		
		response.getWriter().append(String.format("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main');</script>"));

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}