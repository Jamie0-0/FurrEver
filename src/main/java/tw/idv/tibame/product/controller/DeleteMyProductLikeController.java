package tw.idv.tibame.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.product.service.ProductService;
import tw.idv.tibame.product.vo.Product;
import tw.idv.tibame.product.vo.ProductLike;

@RestController
@RequestMapping("deletemyproductlike")
public class DeleteMyProductLikeController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> delete (HttpSession session, @RequestBody Product product){
		Integer pldId = product.getPId();
		Integer uid = (Integer) session.getAttribute("uid");
		Map<String, Object> response = new HashMap<>();
//		System.out.println(pldId);
		if (uid != null) {
			Integer result = productService.deleteMyProductLike(uid, pldId);
			if(result > 0) {
				response.put("success", 1);
				return ResponseEntity.ok(response);
			}
		}
		response.put("noUser", 0);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
}
