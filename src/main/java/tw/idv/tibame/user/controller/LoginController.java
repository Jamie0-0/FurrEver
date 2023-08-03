package tw.idv.tibame.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.user.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/{uEmail}/{uPwd}")
	public ResponseEntity<?> login(HttpSession session, @PathVariable String uEmail, @PathVariable String uPwd) {
		Integer loginUser = userService.login(uEmail, uPwd);
		if(loginUser == null) {
			return new ResponseEntity<>("查無帳號或密碼錯誤", HttpStatus.BAD_REQUEST);
		}
		session.setAttribute("uid", loginUser);
		return ResponseEntity.ok(loginUser);
	}
}
