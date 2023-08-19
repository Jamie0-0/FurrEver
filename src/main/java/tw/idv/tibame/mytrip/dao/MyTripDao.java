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
	
	@Modifying
	@Query(value = "update trip set t_act_status = '1' where t_act_id = ?1 ", nativeQuery = true)
	Integer updateBypActId(@Param("tActId") Integer tActId);
}
