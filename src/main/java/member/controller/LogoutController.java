package member.controller;



import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = -157690109267310990L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String message = gson.toJson("");
		
		if(req.getSession().getAttribute("email") != null) {
			req.getSession().invalidate();
			message = "{\"status\": \"success\"}";
			resp.getWriter().write(message);
			System.out.println(req.getSession().getAttribute("email"));
		}
	}
}
