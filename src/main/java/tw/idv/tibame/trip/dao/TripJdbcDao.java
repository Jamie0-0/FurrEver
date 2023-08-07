package tw.idv.tibame.trip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import tw.idv.tibame.trip.vo.Trip;

@Component
public class TripJdbcDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Trip> findByUid(Integer uid) {
		String sql = "SELECT trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1, IFNULL(COUNT(p.uid), 0) as count "
				+ "FROM trip as trip " + "LEFT JOIN participant as p "
				+ "ON trip.t_act_id = p.t_act_id AND p.uid_join = '0' "
				+ "WHERE trip.uid = :uid AND trip.t_act_status = '1' "
				+ "GROUP BY trip.uid, trip.t_act_id, trip.t_act_name, trip.t_act_status, trip.t_act_time, trip.t_act_ppl, trip.t_1 ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<Trip> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Trip.class));
		return trips;
	}
}
