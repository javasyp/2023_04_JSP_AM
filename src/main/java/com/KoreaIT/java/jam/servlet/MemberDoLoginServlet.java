package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");

		// DB 연결
		Connection conn = null;
		
		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("예외) 클래스가 없습니다! 프로그램 종료");
			e.printStackTrace();
			return;
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPswd());
			
			// 파라미터 값 받아오기
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			
			Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);
			
			if (memberRow.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('%s(은)는 없는 아이디입니다.'); location.replace('login');</script>", loginId));
				return;
			}
			
			// DB에 저장된 비밀번호와 파라미터로 받은 비밀번호 값이 일치하는지?
			if (memberRow.get("loginPw").equals(loginPw) == false) {
				response.getWriter().append(String.format("<script>alert('비밀번호가 일치하지 않습니다.'); location.replace('login');</script>"));
				return;
			}
			
			// 세션 불러오기
			HttpSession session = request.getSession();
			// 세션에 속성 저장
			session.setAttribute("loginedMemberId", memberRow.get("id"));
			session.setAttribute("loginedMemberLoginId", memberRow.get("loginId"));
			session.setAttribute("loginedMemberName", memberRow.get("name"));
			
			response.getWriter().append(String.format("<script>alert('%s님 환영합니다~'); location.replace('../home/main');</script>", memberRow.get("name")));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}