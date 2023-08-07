package tw.idv.tibame.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("logout")
public class LogoutController {

	@PostMapping
	public ResponseEntity<?> logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		// 清除 Session 中的用戶相關資訊
		session.invalidate();

		// 清除瀏覽器中的Cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("logoutsuccess", 1);
		return  ResponseEntity.ok(responseMap);
	}
}
