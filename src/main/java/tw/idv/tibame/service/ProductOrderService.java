package com.wei.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.wei.model.PersonOrderDetial;
import com.wei.model.ProductDetial;
import com.wei.model.Product_order;

public interface ProductOrderService {

	List<Product_order> getProduct_orderById(Integer so_m_id);
	List<Product_order> getProductSearchById(Integer so_m_id, Integer order_s, Integer order_id);
	List<ProductDetial> getDetailSearchById(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id);
	List<PersonOrderDetial> getPersonInfo(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id);
	Integer updateStatus(Integer order_id);
}
