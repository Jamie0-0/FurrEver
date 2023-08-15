package tw.idv.tibame.wei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.wei.dao.*;
import tw.idv.tibame.wei.model.GbOrder;
import tw.idv.tibame.wei.model.PersonOrderDetial;
import tw.idv.tibame.wei.model.ProductDetial;
import tw.idv.tibame.wei.model.Product_order;

@Component
public class ProductOrderServiceImpl implements ProductOrderService{

	@Autowired
	private ProductOrderDAO productOrderDAO;
	
	@Override
	public List<Product_order> getProduct_orderById(Integer so_m_id) {
		return productOrderDAO.getProduct_orderById(so_m_id);
	}

	@Override
	public List<Product_order> getProductSearchById(Integer so_m_id, Integer order_s, Integer order_id){
		return productOrderDAO.getProductSearchById(so_m_id, order_s, order_id);
	}
	
	@Override
	public List<ProductDetial> getDetailSearchById(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id){
		return productOrderDAO.getDetailSearchById(so_m_id, order_uid, order_s, order_id);
	}
	
	@Override
	public List<PersonOrderDetial> getPersonInfo(Integer so_m_id, Integer order_uid, Integer order_s, Integer order_id) {
		return productOrderDAO.getPersonInfo(so_m_id, order_uid, order_s, order_id);
	}

	@Override
	public Integer updateStatus(Integer order_id) {
		return productOrderDAO.updateStatus(order_id);
	}

	@Override
	public List<GbOrder> getGbOrderById(Integer p_m_id) {
		return productOrderDAO.getGbOrderById(p_m_id);
	}
	
	
	@Override
	public List<GbOrder> getGbSearchById(Integer p_m_id, Integer gb_id, Integer gb_s) {
		return productOrderDAO.getGbSearchById(p_m_id,gb_id,gb_s);
	}
	
}
