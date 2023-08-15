package tw.idv.tibame.core.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@Component
//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

	private static final long serialVersionUID = 2576427519315890522L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String[] urls = { "/login.html", "/assets/", "/css/", "/sign-up.html", "/register", "/login", "/loginMaster",
				"/registerMaster" };
//		System.out.println("123");
		String url = request.getServletPath();
		String url1 = request.getContextPath() + "/assets";

//		System.out.println(url);
//		System.out.println(request.getContextPath());

		HttpSession session = request.getSession();
		Object uid = session.getAttribute("uid");
		Object mid = session.getAttribute("mid");

		for (String u : urls) {
			if (url.contains(u) || url1.contains(u)) {
				chain.doFilter(request, response);
				return;
			}
		}

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
		} else {
			chain.doFilter(request, response);

		}

	}

}
