package tw.idv.tibame.trip.controller;

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
import tw.idv.tibame.product.vo.Product;
import tw.idv.tibame.trip.service.TripService;
import tw.idv.tibame.trip.vo.ActLike;

@RestController
@RequestMapping("deletemytrip")
public class DeleteMyTripController {

	@Autowired
	private TripService tripService;

	@PostMapping
	public ResponseEntity<?> delete(HttpSession session, @RequestBody ActLike actLike) {
		Integer actId = actLike.getTActId();
		System.out.println(actId);
		Integer uid = (Integer) session.getAttribute("uid");
		Map<String, Object> response = new HashMap<>();
//		System.out.println(pldId);
		if (uid != null) {
			Integer result = tripService.deleteMyTripLike(uid, actId);
			if (result > 0) {
				response.put("success", 1);
				return ResponseEntity.ok(response);
			}
		}
		response.put("noUser", 0);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}
}
