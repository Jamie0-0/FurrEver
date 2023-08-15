package com.wei.proOrderController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wei.model.PersonOrderDetial;
import com.wei.model.ProductDetial;
import com.wei.model.Product_order;
import com.wei.service.ProductOrderService;

@RestController
@RequestMapping("/backEnd")
public class ProductOrderController {

	@Autowired
	private ProductOrderService productOrderService;

	@PostMapping("order-listValue")
	public ResponseEntity<List<Product_order>> getProductOrderById(@RequestParam Integer order_id) {
	    List<Product_order> productOrder = productOrderService.getProduct_orderById(order_id);
	    if (productOrder != null) {
	        return ResponseEntity.ok(productOrder);
	    }
	    return ResponseEntity.notFound().build();
	}

	@PostMapping("order-Search")
	public ResponseEntity<List<Product_order>> getProductSearchById(@RequestParam Integer so_m_id, Integer order_s, Integer order_id) {
	    List<Product_order> productOrder = productOrderService.getProductSearchById(so_m_id,order_s,order_id);
	    if (productOrder != null) {
	        return ResponseEntity.ok(productOrder);
	    }
	    return null;
	}
	
	
	
	@PostMapping("detial-Search")
	public ResponseEntity<List<ProductDetial>> getDetailSearchById(@RequestParam Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id){
	    List<ProductDetial> productDetial = productOrderService.getDetailSearchById(so_m_id, order_uid, order_s, order_id);
	    if (productDetial != null) {
	        return ResponseEntity.ok(productDetial);
	    }
	    return null;
	}

	@PostMapping("personal-Search")
	public ResponseEntity<List<PersonOrderDetial>> getPersonInfo(@RequestParam Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id){
	    List<PersonOrderDetial> productDetial = productOrderService.getPersonInfo(so_m_id, order_uid, order_s, order_id);
	    if (productDetial != null) {
	        return ResponseEntity.ok(productDetial);
	    }
	    return null;
	}
	
	@PostMapping("update-Status")
	public ResponseEntity<Integer> updateStatus(@RequestParam Integer order_id){
		Integer success = productOrderService.updateStatus(order_id);
	    if (success > 0) {
	        return ResponseEntity.ok(success);
	    }
	    return null;
	}
}
