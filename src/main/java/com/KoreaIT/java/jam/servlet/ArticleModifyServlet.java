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

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
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
			int id = Integer.parseInt(request.getParameter("id"));
			
			// 작성자 수정 권한 체크
			// 세션 값 가져오기
			int memberId = (int) session.getAttribute("loginedMemberId");

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ?;", id);

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);
			
			if ((int) articleRow.get("memberId") != memberId) {
				response.getWriter().append(String.format("<script>alert('해당 게시물에 대한 권한이 없습니다.'); location.replace('list');</script>"));
				return;
			}
			
			// 수정 (페이지 이동)
			response.getWriter().append(articleRow.toString());
			
			request.setAttribute("articleRow", articleRow);
			
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

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