package tw.idv.tibame.trip.service;

import java.util.List;

import tw.idv.tibame.trip.vo.Trip;

public interface TripService {

	List<Trip> selectMyEndTrip(Integer uid);
	
	Integer edit(Integer tId, Integer uid);

	Integer cancle(Integer uid, Integer tActId);

	List<Trip> findTraceTrips(Integer uid);
	
	Integer deleteMyTripLike(Integer tActId, Integer uid );
	
	List<Trip> selectMyHoldTrip(Integer uid);
	
	List<Trip> selectJoinTrip(Integer uid);
	
	List<Trip> selectHistoryJoinTrip(Integer uid);
}
