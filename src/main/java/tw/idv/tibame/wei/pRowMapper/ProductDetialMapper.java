package tw.idv.tibame.wei.pRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tw.idv.tibame.wei.model.ProductDetial;

public class ProductDetialMapper implements org.springframework.jdbc.core.RowMapper<ProductDetial>{
	@Override
	public ProductDetial mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDetial productDetial = new ProductDetial();
		productDetial.setP_id(rs.getInt("p_id"));
		productDetial.setP_name(rs.getString("p_name"));
		productDetial.setP_m_price(rs.getInt("p_m_price"));
		productDetial.setP_m_stock(rs.getInt("p_m_stock"));
		return productDetial;
	}
}

