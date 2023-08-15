package tw.idv.tibame.mytrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import tw.idv.tibame.mytrip.vo.MyTrip;

@Component
public class MyTripJdbcDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<MyTrip> findMyEndTripByUid(Integer uid) {
		String sql = "SELECT " + "    trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, "
				+ "    trip.t_act_time, trip.t_act_ppl, trip.t_1, " + "    IFNULL(COUNT(p.uid), 0) as count " + "FROM "
				+ "    trip as trip " + "LEFT JOIN " + "    participant as p ON trip.t_act_id = p.t_act_id " + "WHERE "
				+ "    trip.uid = :uid " + "    AND p.uid_join = '0' "
				+ "    AND (trip.t_act_status = '0' OR trip.t_act_status = '1') " + "GROUP BY "
				+ "    trip.uid, trip.t_act_id, trip.t_act_name, "
				+ "    trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyTrip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyTrip.class));
		return trips;
	}

	public List<MyTrip> findMyHoldTripByUid(Integer uid) {
		String sql = "SELECT " + "    trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, "
				+ "    trip.t_act_time, trip.t_act_ppl, trip.t_1, " + "    IFNULL(COUNT(p.uid), 0) as count " + "FROM "
				+ "    trip as trip " + "LEFT JOIN " + "    participant as p ON trip.t_act_id = p.t_act_id " + "WHERE "
				+ "    trip.uid = :uid " + "    AND p.uid_join = '0' "
				+ "    AND (trip.t_act_status = '2' OR trip.t_act_status = '3') " + "GROUP BY "
				+ "    trip.uid, trip.t_act_id, trip.t_act_name, "
				+ "    trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyTrip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyTrip.class));
		return trips;
	}

	public List<MyTrip> findJoinTripByUid(Integer uid) {
		String sql = "SELECT p.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1, IFNULL(COUNT(p.uid), 0) as count "
				+ "	FROM trip as trip LEFT JOIN participant as p "
				+ "	ON trip.t_act_id = p.t_act_id "
				+ "	WHERE p.uid = :uid "
				+ " AND p.uid_join = '0' "
				+ " AND (trip.t_act_status = '0' OR trip.t_act_status = '1') "
				+ "	GROUP BY trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyTrip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyTrip.class));
		return trips;
	}
	
	public List<MyTrip> findJoinHistoryTripByUid(Integer uid) {
		String sql = "SELECT p.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1, IFNULL(COUNT(p.uid), 0) as countp "
				+ "	FROM trip as trip LEFT JOIN participant as p "
				+ "		ON trip.t_act_id = p.t_act_id "
				+ "	WHERE p.uid = :uid "
				+ "		AND p.uid_join = '0' "
				+ "		AND trip.t_act_time <  CURDATE() "
				+ "	GROUP BY p.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyTrip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyTrip.class));
		return trips;
	}
	
	public List<MyTrip> findTraceTripByUid(Integer uid) {
		String sql = "select * "
				+ "from trip p "
				+ "left join act_like ac "
				+ "on p.t_act_id = ac.t_act_id "
				+ "where ac.uid = :uid ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyTrip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyTrip.class));
		return trips;
	}
	
	

}
