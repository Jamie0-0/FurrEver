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
@RequestMapping("/changetripstatus")
public class ChangeTripStatusController {
	
	@Autowired
	private MyTripService tripService;

	@PostMapping
	public ResponseEntity<?> editHandleTrip(@RequestParam Integer tActId) {
		System.out.println(tActId);
		if(tActId != null) {
			return ResponseEntity.ok(tripService.changeStatus(tActId));		
		}

		return new ResponseEntity<>("查無活動資料", HttpStatus.BAD_REQUEST);
	}
}
