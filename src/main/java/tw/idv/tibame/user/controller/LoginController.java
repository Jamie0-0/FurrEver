package tw.idv.tibame.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/{uEmail}/{uPwd}")
	public ResponseEntity<?> login(HttpSession session, @PathVariable String uEmail, @PathVariable String uPwd) {
		Integer loginUser = userService.login(uEmail, uPwd);
		User userlist = userService.findUserName(uEmail);
		String userName="";
		if (loginUser == null || userlist == null) {
			return new ResponseEntity<>("查無帳號或密碼錯誤", HttpStatus.BAD_REQUEST);
		} else {
			userName = userlist.getUName();
		}

		session.setAttribute("uid", loginUser);
		session.setAttribute("uName", userName);

		// 獲取之前儲存的造訪頁面的 URL
		String location = (String) session.getAttribute("location");

		// 如果沒有之前的造訪頁面 URL，將它設為預設值（例如首頁）
		if (location == null || location.isBlank()) {
			location = "";
		}
		System.out.println(location);
		// 清除之前儲存的造訪頁面 URL
		session.removeAttribute("location");

		Map<String, Object> response = new HashMap<>();
		response.put("loginUser", loginUser);
		response.put("location", location);
		response.put("userName", userName);
		return ResponseEntity.ok(response);
	}

}
