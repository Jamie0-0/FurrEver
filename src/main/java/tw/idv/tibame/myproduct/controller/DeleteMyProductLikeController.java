package tw.idv.tibame.myproduct.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.myproduct.service.MyProductService;

@RestController
@RequestMapping("deletemyproductlike")
public class DeleteMyProductLikeController {

	@Autowired
	private MyProductService productService;
	
	@PostMapping
	public ResponseEntity<?> delete (HttpSession session, @RequestParam Integer pid){
		Integer uid = (Integer) session.getAttribute("uid");
		Map<String, Object> response = new HashMap<>();

		if (uid != null) {
			Integer result = productService.deleteMyProductLike(uid, pid);
			if(result > 0) {
				response.put("success", 1);
				return ResponseEntity.ok(response);
			}
		}
		response.put("noUser", 0);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
}
