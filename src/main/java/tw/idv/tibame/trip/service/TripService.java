package tw.idv.tibame.trip.service;

import java.util.List;

import tw.idv.tibame.trip.vo.Trip;

public interface TripService {

	List<Trip> findTrip(Integer uid);
	
	Integer edit(Integer tId, Integer uid);

	Integer cancle(Integer uid, Integer tActId);

	List<Trip> findTraceTrips(Integer uid);
	
	Integer deleteMyTripLike(Integer tActId, Integer uid );
}
