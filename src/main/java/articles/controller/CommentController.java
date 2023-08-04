package articles.controller;

import java.io.IOException;
import java.util.List;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.CommentService;
import articles.service.CommentServiceImpl;
import articles.vo.Comment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService service;

	@Override
	public void init() throws ServletException {
		service = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Comment> commentList = null;

		HttpSession session = request.getSession();
		String com_art_id = (String) session.getAttribute("art_id");
		commentList = service.selectComment(com_art_id);

		String json = ArticlesUtils.TurnIntoJson(commentList);

		response.setContentType("application/json; charset=UTF-8");
		// 寫出
		response.getWriter().write(json);
	}

}
