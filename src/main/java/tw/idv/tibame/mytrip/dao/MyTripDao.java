package tw.idv.tibame.mytrip.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import tw.idv.tibame.mytrip.vo.MyTrip;

@Component
public interface MyTripDao extends CrudRepository<MyTrip, Integer> {
	
	
	






	
	
	
	

//	// 搜尋活動
//	@Query(value = "SELECT trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1, COUNT(p.uid) as countp "
//			+ "FROM trip as trip "
//			+ "JOIN participant as p "
//			+ "ON trip.t_act_id = p.t_act_id "
//			+ "WHERE trip.uid = ?1 AND p.uid_join = '0' AND trip.t_act_status = '1' "
//			+ "GROUP BY trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ", nativeQuery = true)
//	List<Trip> findByUid(Integer uid);
	
	// 退出活動
	@Modifying
	@Query(value = "update PARTICIPANT set uid_join = '1' where t_act_id = ?1 and uid = ?2", nativeQuery = true)
	Integer updateByUid(@Param("tActId") Integer tActId,@Param("uid") Integer uid);
	
	//取消活動
	@Modifying
	@Query(value = "update TRIP set t_act_status ='0' where uid = ?1 and t_act_id =?2", nativeQuery = true)
	Integer updateByUidAndPActId(Integer uid, Integer pActId);
	
	@Query(value = "select t.uid, t.t_act_id, t.t_act_name, t.t_act_status, t.t_act_time, t.t_act_ppl, t.t_1, COUNT(ac.t_act_id) as countp "
			+ "from TRIP t "
			+ "join act_like ac "
			+ "	on t.t_act_id = ac.t_act_id "
			+ "where ac.uid = ?1 "
			+ "group by t.uid, t.t_act_id, t.t_act_name, t.t_act_status, t.t_act_time, t.t_act_ppl, t.t_1 ", nativeQuery = true)
	List<MyTrip> findByAUid(Integer uid);
	
	
}
