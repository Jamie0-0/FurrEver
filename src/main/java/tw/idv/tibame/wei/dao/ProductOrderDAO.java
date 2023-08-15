<<<<<<< HEAD
package com.wei.dao;

import java.util.List;

import com.wei.model.PersonOrderDetial;
import com.wei.model.ProductDetial;
import com.wei.model.Product_order;
=======
package tw.idv.tibame.wei.dao;

import java.util.List;

import tw.idv.tibame.wei.model.GbOrder;
import tw.idv.tibame.wei.model.PersonOrderDetial;
import tw.idv.tibame.wei.model.ProductDetial;
import tw.idv.tibame.wei.model.Product_order;
>>>>>>> TonyYen

public interface ProductOrderDAO {

	List<Product_order> getProduct_orderById(Integer so_m_id);
	List<Product_order> getProductSearchById(Integer so_m_id, Integer order_s, Integer order_id);
	List<ProductDetial> getDetailSearchById(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id);
	List<PersonOrderDetial> getPersonInfo(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id);
	Integer updateStatus(Integer order_id);
<<<<<<< HEAD
=======
	List<GbOrder> getGbOrderById(Integer p_m_id);
	List<GbOrder> getGbSearchById(Integer p_m_id, Integer gb_id, Integer gb_s);
>>>>>>> TonyYen
}
