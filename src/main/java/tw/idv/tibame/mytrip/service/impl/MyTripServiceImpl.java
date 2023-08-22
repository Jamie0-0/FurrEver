package tw.idv.tibame.mytrip.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.mytrip.dao.MyActLikeDao;
import tw.idv.tibame.mytrip.dao.MyTripDao;
import tw.idv.tibame.mytrip.dao.MyTripJdbcDao;
import tw.idv.tibame.mytrip.service.MyTripService;
import tw.idv.tibame.mytrip.vo.MyTrip;

@Component
public class MyTripServiceImpl implements MyTripService {

	@Autowired
	private MyTripDao tripDao;

	@Autowired
	private MyActLikeDao actLikeDao;

	@Autowired
	private MyTripJdbcDao tripJdbcDao;

	@Override
	public List<MyTrip> selectMyEndTrip(Integer uid) {
		return tripJdbcDao.findMyEndTripByUid(uid);
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
	public List<MyTrip> findTraceTrips(Integer uid) {
		return tripJdbcDao.findTraceTripByUid(uid);
	}

	@Override
	@Transactional
	public Integer deleteMyTripLike(Integer uid, Integer tActId) {
		return actLikeDao.deledeleteByTActIdAndUid(tActId, uid);

	}

	@Override
	public List<MyTrip> selectMyHoldTrip(Integer uid) {
		return tripJdbcDao.findMyHoldTripByUid(uid);
	}

	@Override
	public List<MyTrip> selectJoinTrip(Integer tActId) {
		return tripJdbcDao.findJoinTripByUid(tActId);
	}

	@Override
	public List<MyTrip> selectHistoryJoinTrip(Integer uid) {
		return tripJdbcDao.findJoinHistoryTripByUid(uid);
	}

	@Transactional
	@Override
	public Integer changeStatus(Integer tActId) {
		return tripDao.updateBypActId(tActId);
	}

}
