package tw.idv.tibame.myproduct.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import tw.idv.tibame.myproduct.vo.MyProduct;

@Component
public class MyProductJdbcDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
	public List<MyProduct> findMyProductByUid(Integer uid) {
		String sql = "SELECT p.p_id, p.p_name, p.p_price "
		           + "FROM product p "
		           + "LEFT JOIN product_like pl "
		           + "ON p.p_id = pl.pl_p_id "
		           + "WHERE pl.pl_uid = :uid ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("uid", uid);

		List<MyProduct> trips = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(MyProduct.class));
		return trips;
	}
	
}
