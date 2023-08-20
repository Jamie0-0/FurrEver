package tw.idv.tibame.mytrip.service;

import java.util.List;

import tw.idv.tibame.mytrip.vo.MyTrip;

public interface MyTripService {

	List<MyTrip> selectMyEndTrip(Integer uid);
	
	Integer edit(Integer tId, Integer uid);

	Integer cancle(Integer uid, Integer tActId);

	List<MyTrip> findTraceTrips(Integer uid);
	
	Integer deleteMyTripLike(Integer tActId, Integer uid );
	
	List<MyTrip> selectMyHoldTrip(Integer uid);
	
	List<MyTrip> selectJoinTrip(Integer uid);
	
	List<MyTrip> selectHistoryJoinTrip(Integer uid);
	
	Integer changeStatus(Integer tActId);
}
