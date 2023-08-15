package tw.idv.tibame.wei.pRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tw.idv.tibame.wei.model.GbOrder;

public class GbOrderMapper implements org.springframework.jdbc.core.RowMapper<GbOrder> {
	@Override
	public GbOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		GbOrder gbDetial = new GbOrder();
		gbDetial.setGb_id(rs.getInt("gb_id"));
		gbDetial.setPeople(rs.getInt("people"));
		gbDetial.setGb_c_max(rs.getInt("gb_c_max"));
		gbDetial.setGb_s_price(rs.getInt("gb_s_price"));
	
		gbDetial.setGb_time_end(rs.getTimestamp("gb_time_end").toLocalDateTime());
		gbDetial.setGb_s(rs.getString("gb_s"));
		return gbDetial;
	}
}
