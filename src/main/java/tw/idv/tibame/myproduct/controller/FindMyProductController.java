package tw.idv.tibame.myproduct.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.myproduct.service.MyProductService;
import tw.idv.tibame.myproduct.vo.MyProduct;

@RestController
@RequestMapping("findmyproduct")
public class FindMyProductController {

	@Autowired
	private MyProductService productService;

	@PostMapping
	public ResponseEntity<?> findMyProduct(HttpSession session) {
		Integer uid = (Integer) session.getAttribute("uid");
		Map<String, Object> response = new HashMap<>();
		if (uid != null) {
			List<MyProduct> products = productService.selectMyProduct(uid);
			if (products.isEmpty()) {
				response.put("noProduct", 0);
				return ResponseEntity.ok(response);
			}
			response.put("product", products);
			return ResponseEntity.ok(response);
		}
		response.put("noUser", 0);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
