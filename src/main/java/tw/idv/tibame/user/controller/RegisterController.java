package tw.idv.tibame.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@RestController
@RequestMapping("register")
public class RegisterController {
	
	@Autowired
	private UserService userservice;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody  User user) {
		User registeredUser = userservice.register(user);
		if(registeredUser == null) {
			return new ResponseEntity<>("帳號重複", HttpStatus.BAD_REQUEST);
		}
	    return ResponseEntity.ok(registeredUser);
	}
	
}
