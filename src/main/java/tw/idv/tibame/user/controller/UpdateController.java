package tw.idv.tibame.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@RestController
@RequestMapping("updatemember")
public class UpdateController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> update(@RequestBody User user){
		int result = userService.updateUser(user);
		System.out.println(user.getUPic());
		if(result > 0) {
			return ResponseEntity.ok(result);
		}
		return new ResponseEntity<>("更新失敗", HttpStatus.BAD_REQUEST);
		
	}
}
