package tw.idv.tibame.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.trip.service.TripService;

@RestController
@RequestMapping("cancletrip")
public class CancleTripController {
	
	@Autowired
	private TripService tripService;

	@PostMapping
	public ResponseEntity<?> editJoinTrip(HttpSession session, @RequestParam Integer tActId) {
		System.out.println(tActId);
		Integer uid = (Integer) session.getAttribute("uid");
		if (uid != null) {
			Integer triplist = tripService.cancle(uid, tActId);
			return ResponseEntity.ok(triplist);
		}

		return new ResponseEntity<>("查無活動資料", HttpStatus.BAD_REQUEST);
	}
}
