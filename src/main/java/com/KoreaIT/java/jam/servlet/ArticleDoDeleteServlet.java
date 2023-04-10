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

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		// DB 연결
		Connection conn = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("예외) 클래스가 없습니다! 프로그램 종료");
			e.printStackTrace();
			return;
		}

		try {
			conn = DriverManager.getConnection(url, user, password);

			response.getWriter().append("Success!!!");
			
			// 파라미터 값 받아오기
			int id = Integer.parseInt(request.getParameter("id"));
			
			SecSql sql = SecSql.from("DELETE");
			sql.append("FROM article");
			sql.append("WHERE id = ? ;", id);

			DBUtil.delete(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%d번 글이 삭제되었습니다!'); location.replace('list');</script>", id));

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