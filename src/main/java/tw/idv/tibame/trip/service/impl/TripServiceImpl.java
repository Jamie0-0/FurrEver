package tw.idv.tibame.trip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.trip.dao.TripDao;
import tw.idv.tibame.trip.service.TripService;

@Component
public class TripServiceImpl implements TripService{

	@Autowired
	private TripDao tripDao;
}
