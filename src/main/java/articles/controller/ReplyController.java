package articles.controller;

import java.io.IOException;
import java.util.List;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.CommentService;
import articles.service.CommentServiceImpl;
import articles.vo.Reply;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService service;
       
	@Override
	public void init() throws ServletException {
		service = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Reply> replyList = null;

		String reply_com_id = request.getParameter("reply_com_id");
 		replyList = service.selectReply(reply_com_id);

		String json = ArticlesUtils.TurnIntoJson(replyList);

		response.setContentType("application/json; charset=UTF-8");
		// 寫出
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
