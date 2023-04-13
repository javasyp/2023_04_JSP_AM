package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class ArticleController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void showList() throws ServletException, IOException {
		// 페이징
		int page = 1;		// 현재 페이지
		
		String paramPage = request.getParameter("page");
					
		if (paramPage != null && paramPage.length() != 0) {
			page = Integer.parseInt(paramPage);
		}
		
		int itemsInAPage = 10;		// 한 페이지에 보이는 글 개수
		
		int limitFrom = (page - 1) * itemsInAPage;
		
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article;");
		
		int totalCnt = DBUtil.selectRowIntValue(conn, sql);		// 페이지 총 개수
		int totalPage = (int) Math.ceil((double) totalCnt / itemsInAPage);		// 올림
		
		sql = SecSql.from("SELECT *");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articleRows", articleRows);
		
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
	}
	
}
