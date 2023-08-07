package tw.idv.tibame.trip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import tw.idv.tibame.trip.dao.ActLikeDao;
import tw.idv.tibame.trip.dao.TripDao;
import tw.idv.tibame.trip.dao.TripJdbcDao;
import tw.idv.tibame.trip.service.TripService;
import tw.idv.tibame.trip.vo.Trip;

@Component
public class TripServiceImpl implements TripService{

	@Autowired
	private TripDao tripDao;
	
	@Autowired
	private ActLikeDao actLikeDao;
	
	@Autowired
	private TripJdbcDao tripJdbcDao;

	@Override
	public List<Trip> findTrip(Integer uid) {
		return tripJdbcDao.findByUid(uid);
	}
	
	@Transactional
	@Override
	public Integer edit(Integer tId, Integer uid) {

		return tripDao.updateByUid(tId, uid);
	}

	@Transactional
	@Override
	public Integer cancle(Integer uid, Integer tActId) {
		
		return tripDao.updateByUidAndPActId(uid, tActId);
	}

	@Override
	public List<Trip> findTraceTrips(Integer uid) {
		return tripDao.findByAUid(uid);
	}

	@Override
	@Transactional
	public Integer deleteMyTripLike(Integer tActId, Integer uid ) {
		return actLikeDao.deledeleteByTActIdAndUid(tActId, uid);
		
	}

}
