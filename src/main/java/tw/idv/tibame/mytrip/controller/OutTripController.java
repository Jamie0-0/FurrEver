package tw.idv.tibame.mytrip.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.mytrip.service.MyTripService;


@RestController
@RequestMapping("outtrip")
public class OutTripController {
	
	@Autowired
	private MyTripService tripService;

	@PostMapping
	public ResponseEntity<?> editJoinTrip(HttpSession session,@RequestParam Integer tActId) {
		
		Integer uid = (Integer) session.getAttribute("uid");
		if (uid != null) {
			Integer triplist = tripService.edit(tActId, uid);
			return ResponseEntity.ok(triplist);
		}

		return new ResponseEntity<>("查無活動資料", HttpStatus.BAD_REQUEST);
	}
}
