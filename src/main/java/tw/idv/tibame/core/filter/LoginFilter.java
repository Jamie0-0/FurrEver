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

@Component
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

	private static final long serialVersionUID = 2576427519315890522L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String[] excludeUrls = { "/login.html", "/assets/", "/css/", "/sign-up.html", "/register", "/login",
				"/loginMaster", "/registerMaster", "/member_center.html", "/memberCenter", "/findPet" };

		String url = request.getServletPath();
		String url1 = request.getContextPath() + "/assets";

		HttpSession session = request.getSession();
		Object uid = session.getAttribute("uid");
		Object mid = session.getAttribute("mid");

		boolean isExcluded = false;
		for (String excludedUrl : excludeUrls) {
			if (url.contains(excludedUrl) || url1.contains(excludedUrl)) {
				isExcluded = true;
				break;
			}
		}

		if (isExcluded) {
			chain.doFilter(request, response);
		} else if (uid == null || mid == null) {
			session.setAttribute("location", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/login.html");
		} else {
			chain.doFilter(request, response);
		}
	}

}
