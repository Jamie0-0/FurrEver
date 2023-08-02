package articles.controller;

import java.io.IOException;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveAllArticlesController
 */
@WebServlet("/saveAllArt")
public class SaveAllArticlesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service.saveAllHotArticles();
		service.saveAllNewArticles();
		System.out.println("存取文章成功");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
