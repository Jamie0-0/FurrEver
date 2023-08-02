package articles.controller;

import java.io.IOException;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/refresh")
public class JedisRefreshController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;
	
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service.jedisRefresh();
	}
}
