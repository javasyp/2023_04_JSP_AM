package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.service.ArticleService;

public class ArticleController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
		
		this.articleService = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {
		// 페이징
		int page = 1;		// 현재 페이지
		
		String paramPage = request.getParameter("page");
					
		if (paramPage != null && paramPage.length() != 0) {
			page = Integer.parseInt(paramPage);
		}
		
		// 한 페이지에 보이는 글 개수
		int itemsInAPage = articleService.getItemsInAPage();
		// 전체 페이지 개수
		int totalPage = articleService.getTotalPage();
		
		// 게시판 목록 가져오기
		List<Map<String, Object>> articleRows = articleService.getForPrintArticleRows(page);
		
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articleRows", articleRows);
		
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
	}
	
}
