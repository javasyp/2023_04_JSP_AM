package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
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
			String name = request.getParameter("name");
			
			// 아이디 중복 체크
			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?", loginId);

			int isJoinableId = DBUtil.selectRowIntValue(conn, sql);

			if (isJoinableId == 1) {
				response.getWriter().append(String.format("<script>alert('%s(은)는 이미 사용중인 아이디입니다.'); location.replace('join');</script>", loginId));
				return;
			}
			
			// 회원가입
			sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);
			
			DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%s님 가입되었습니다!'); location.replace('../home/main');</script>", name));

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