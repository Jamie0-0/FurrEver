package tw.idv.tibame.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@RestController
@RequestMapping("showUserInfor")
public class ShowUserInfor {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> findUser(HttpSession session) {
		Integer uid = (Integer) session.getAttribute("uid");
		if (uid != null) {
		User user =	service.findUser(uid);

		return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("查無會員資料", HttpStatus.BAD_REQUEST);
	}
}
