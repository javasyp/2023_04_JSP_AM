package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {

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
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			SecSql sql = SecSql.from("INSERT INTO article");
			sql.append("SET regDate = NOW(),");
			sql.append("title = ?,", title);
			sql.append("`body` = ?;", body);
			
			int id = DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%d번 글이 생성되었습니다!'); location.replace('list');</script>", id));

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

}