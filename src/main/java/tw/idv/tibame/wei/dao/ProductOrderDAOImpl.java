<<<<<<< HEAD
package com.wei.dao;

import java.sql.Types;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.wei.model.PersonOrderDetial;
import com.wei.model.ProductDetial;
import com.wei.model.Product_order;
import com.wei.pRowMapper.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
=======
package tw.idv.tibame.wei.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import tw.idv.tibame.wei.model.GbOrder;
import tw.idv.tibame.wei.model.PersonOrderDetial;
import tw.idv.tibame.wei.model.ProductDetial;
import tw.idv.tibame.wei.model.Product_order;
import tw.idv.tibame.wei.pRowMapper.GbOrderMapper;
import tw.idv.tibame.wei.pRowMapper.PersonDetialMapper;
import tw.idv.tibame.wei.pRowMapper.ProductDetialMapper;
import tw.idv.tibame.wei.pRowMapper.ProductOrderRowMapper;
>>>>>>> TonyYen


@Component
public class ProductOrderDAOImpl implements ProductOrderDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Product_order> getProduct_orderById(Integer so_m_id) {
		String sql="select * "
				+ " from FurrEver.product_order"
				+ " where order_id in ("
				+ " select so_order_id"
				+ " from sub_order"
				+ " where so_m_id= :so_m_id)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("so_m_id",so_m_id);
		

		List<Product_order> product_orderList = namedParameterJdbcTemplate.query(sql, map,new ProductOrderRowMapper());
		
		if(product_orderList.size() > 0) {
			return product_orderList;
		} else {
			return null;
		}
	}

	@Override
	public List<Product_order> getProductSearchById(Integer so_m_id, Integer order_s, Integer order_id) {

		String sql="select * "
				+ " from FurrEver.product_order"
				+ " where order_id in ("
				+ " select so_order_id"
				+ " from sub_order"
				+ " where so_m_id= "+String.valueOf(so_m_id)+"";

		if(order_id != null && String.valueOf(order_id).length() > 0) {
			sql = sql + " and order_id = "+String.valueOf(order_id)+"";
		}

		if(order_s == 2) {
			
		} else {
			sql = sql + " and order_s = '"+String.valueOf(order_s)+"'";
		}
		sql = sql + ")";

		List<Product_order> product_orderList = namedParameterJdbcTemplate.query(sql,new ProductOrderRowMapper());
		if(product_orderList.size() > 0) {
			return product_orderList;
		} else {
			return null;
		}
	}

	@Override
	public List<ProductDetial> getDetailSearchById(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id) {
	    String sql = "SELECT p_id, p_name, p_m_price, p_m_stock "
	            + " FROM FurrEver.product_order a, FurrEver.sub_order b, FurrEver.sub_product c, product"
	            + " WHERE so_m_id = :so_m_id"
	            + " AND so_m_id = p_m_id"
	            + " AND order_uid = :order_uid"
	            + " AND a.order_s = :order_s"
	            + " AND a.order_id = :order_id"
	            + " AND a.order_id = so_order_id"
	            + " AND b.so_order_id = c.order_id"
	            + " AND p_id = p_p_id";

	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("so_m_id", so_m_id);
	    paramMap.addValue("order_uid", order_uid);
	    paramMap.addValue("order_s", order_s, Types.VARCHAR);
	    paramMap.addValue("order_id", order_id);

	    List<ProductDetial> productDetialList = namedParameterJdbcTemplate.query(sql, paramMap, new ProductDetialMapper());
	    
	    if (productDetialList.size() > 0) {
	        return productDetialList;
	    } else {
	        return null;
	    }
	}

	@Override
	public List<PersonOrderDetial> getPersonInfo(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id) {
	    String sql = "SELECT order_r_name,order_dfee,order_r_addr,order_r_phone,order_pay,sum(p_m_price*p_m_stock) as money"
	    		+ " FROM FurrEver.product_order a, FurrEver.sub_order b, FurrEver.sub_product c, product"
	    		+ " WHERE so_m_id = :so_m_id"
	    		+ " AND so_m_id = p_m_id"
	    		+ " AND order_uid = :order_uid"
	    		+ " AND a.order_s = :order_s"
	    		+ " AND a.order_id = :order_id"
	    		+ " AND a.order_id = so_order_id"
	    		+ " AND b.so_order_id = c.order_id"
	    		+ " AND p_id = p_p_id"
	    		+ " group by order_r_name,order_dfee,order_r_addr,order_r_phone,order_pay";

	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("so_m_id", so_m_id);
	    paramMap.addValue("order_uid", order_uid);
	    paramMap.addValue("order_s", order_s, Types.VARCHAR);
	    paramMap.addValue("order_id", order_id);

	    List<PersonOrderDetial> personalList = namedParameterJdbcTemplate.query(sql, paramMap, new PersonDetialMapper());
	    
	    if (personalList.size() > 0) {
	        return personalList;
	    } else {
	        return null;
	    }
	}

	@Override
	public Integer updateStatus(Integer order_id) {
	    String sql = "UPDATE FurrEver.product_order"
	    		+ " SET order_s = '0'"
	    		+ " WHERE order_id = :order_id";

	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("order_id", order_id);
		
	    Integer successRow = namedParameterJdbcTemplate.update(sql, paramMap);
	    
	    if (successRow > 0) {
	        return successRow;
	    } else {
	        return null;
	    }
	}
<<<<<<< HEAD
=======

	@Override
	public List<GbOrder> getGbOrderById(Integer p_m_id) {
		String sql = "select a.gb_id,count(*) as people,gb_c_max,gb_s_price,gb_time_end,gb_s"
				+ " from gb_order a,gb b,product"
				+ " where a.gb_id = b.gb_id"
				+ " and b.gb_p_id=p_id"
				+ " and p_m_id = :p_m_id"
				+ " and gb_satus = 0"
				+ " group by a.gb_id,gb_c_max";

	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("p_m_id", p_m_id);

	    List<GbOrder> gbDetialList = namedParameterJdbcTemplate.query(sql, paramMap, new GbOrderMapper());
	    
	    if (gbDetialList.size() > 0) {
	        return gbDetialList;
	    } else {
	        return null;
	    }
	}

	@Override
	public List<GbOrder> getGbSearchById(Integer p_m_id, Integer gb_id, Integer gb_s) {
		String sql = "select a.gb_id,count(*) as people,gb_c_max,gb_s_price,gb_time_end,gb_s"
				+ " from gb_order a,gb b,product"
				+ " where a.gb_id = b.gb_id"
				+ " and b.gb_p_id=p_id"
				+ " and p_m_id = "+p_m_id;

				if(gb_id != null && String.valueOf(gb_id).length() > 0) {
					sql = sql + " and a.gb_id = "+gb_id;
				}

				if(gb_s == 2) {
					
				} else {
					sql = sql + " and gb_s = '"+String.valueOf(gb_s)+"'";
				}
				sql = sql + " group by a.gb_id,gb_c_max,gb_s_price,gb_time_end,gb_s";

	    List<GbOrder> gbDetialList = namedParameterJdbcTemplate.query(sql, new GbOrderMapper());
	    
	    if (gbDetialList.size() > 0) {
	        return gbDetialList;
	    } else {
	        return null;
	    }
	}
	
>>>>>>> TonyYen
}
