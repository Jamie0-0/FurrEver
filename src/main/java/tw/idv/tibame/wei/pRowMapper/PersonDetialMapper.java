<<<<<<< HEAD
package com.wei.pRowMapper;
=======
package tw.idv.tibame.wei.pRowMapper;
>>>>>>> TonyYen

import java.sql.ResultSet;
import java.sql.SQLException;

<<<<<<< HEAD
import com.wei.model.PersonOrderDetial;
import com.wei.model.ProductDetial;
=======
import tw.idv.tibame.wei.model.PersonOrderDetial;
import tw.idv.tibame.wei.model.ProductDetial;
>>>>>>> TonyYen

public class PersonDetialMapper implements org.springframework.jdbc.core.RowMapper<PersonOrderDetial> {
	@Override
	public PersonOrderDetial mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonOrderDetial productDetial = new PersonOrderDetial();
		productDetial.setOrder_r_name(rs.getString("order_r_name"));
		productDetial.setOrder_dfee(rs.getInt("order_dfee"));
		productDetial.setOrder_r_addr(rs.getString("order_r_addr"));
		productDetial.setOrder_r_phone(rs.getString("order_r_phone"));
		productDetial.setOrder_pay(rs.getInt("order_pay"));
		productDetial.setMoney(rs.getInt("money"));
		return productDetial;
	}
}
