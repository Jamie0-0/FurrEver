package tw.idv.tibame.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.trip.service.TripService;

@RestController
@RequestMapping("findTrip")
public class findTripController {

	@Autowired
	private TripService tripService;
}
