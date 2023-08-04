package articles.controller;

import java.io.IOException;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.ReplyService;
import articles.service.ReplyServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/replyInsert")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService service;

	@Override
	public void init() throws ServletException {
		service = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String reply_com_id = request.getParameter("reply_com_id");
		String reply_user_id = request.getParameter("reply_user_id");
		String reply_content = request.getParameter("reply_content");

		System.out.println("reply_com_id=" + reply_com_id);
		System.out.println("reply_user_id=" + reply_user_id);
		System.out.println("reply_content=" + reply_content);

		int status =  service.insertReply(reply_com_id, reply_user_id, reply_content);

		String json = ArticlesUtils.TurnIntoJson(status);
		response.setContentType("application/JSON");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
