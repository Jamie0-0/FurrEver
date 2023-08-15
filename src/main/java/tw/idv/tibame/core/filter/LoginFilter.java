package tw.idv.tibame.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@Component
//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
=======
//@WebFilter("/*")
>>>>>>> TonyYen
public class LoginFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String[] urls = { "/login.html", "/assets/", "/css/", "/sign-up.html", "/loginController",
				"/registerController", "/restigermastercontroller" };
		String url = request.getServletPath();
		String url1 = request.getContextPath() + "/assets";

		System.out.println(url);
		System.out.println(url1);

		HttpSession session = request.getSession();
		Object user = session.getAttribute("username");

		for (String u : urls) {
			if (url.contains(u) || url1.contains(u)) {
				chain.doFilter(request, response);
				return;
			}
		}

<<<<<<< HEAD
		String requestUrl = request.getRequestURI();
		System.out.println(requestUrl);
		if (uid == null) {
			session.setAttribute("location", requestUrl);
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		} else if (mid == null) {
			session.setAttribute("location", requestUrl);
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
=======
		if (user == null) {
			session.setAttribute("loginNotice", true);
//			String ttttt = url1 + "login.html";
			response.sendRedirect(request.getContextPath() + "/" + "login.html");
>>>>>>> TonyYen
		} else {
			chain.doFilter(request, response);

		}

	}
}
