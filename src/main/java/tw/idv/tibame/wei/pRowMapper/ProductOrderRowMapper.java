package tw.idv.tibame.wei.pRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import tw.idv.tibame.wei.model.Product_order;

public class ProductOrderRowMapper implements org.springframework.jdbc.core.RowMapper<Product_order>{

	@Override
	public Product_order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product_order product_order = new Product_order();
		product_order.setGm_date(rs.getTimestamp("gm_date").toLocalDateTime());
		product_order.setGm_id(rs.getInt("gm_id"));
		product_order.setOrder_1(rs.getString("order_1"));
		product_order.setOrder_2(rs.getString("order_2"));
		product_order.setOrder_3(rs.getString("order_3"));
		product_order.setOrder_id(rs.getInt("order_id"));
		product_order.setOrder_pay(rs.getString("order_pay"));
		product_order.setOrder_r_addr(rs.getString("order_r_addr"));
		product_order.setOrder_dfee(rs.getInt("order_dfee"));
		product_order.setOrder_r_name(rs.getString("order_r_name"));
		product_order.setOrder_r_phone(rs.getString("order_r_phone"));
		product_order.setOrder_s(rs.getString("order_s"));
		product_order.setOrder_t(rs.getInt("order_t"));
		product_order.setOrder_uid(rs.getInt("order_uid"));
		
		return product_order;
	}
}
