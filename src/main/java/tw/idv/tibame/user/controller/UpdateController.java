package tw.idv.tibame.user.controller;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("updatemember")
public class UpdateController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> update(@RequestBody User user){
		int result = userService.updateUser(user);
		Map<String, Object> response = new HashMap<>();
		
		if(result > 0) {
			response.put("status", "success");
			return ResponseEntity.ok(response);
		}
		response.put("status", "error");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
}
