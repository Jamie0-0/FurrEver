package tw.idv.tibame.myOrders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.myOrders.dao.MyGbDao;
import tw.idv.tibame.myOrders.dao.myOrderDao;
import tw.idv.tibame.myOrders.service.MyOrderService;
import tw.idv.tibame.myOrders.vo.MyGb;
import tw.idv.tibame.myOrders.vo.MyProductOrder;

@Component
public class MyOrderServiceImpl implements MyOrderService {
	
	@Autowired
	private myOrderDao myOrderDao;
	
	@Autowired
	private MyGbDao myGbDao;
	
	public List<MyProductOrder> findAll(Integer orderUid){
		return myOrderDao.findAllByorderUid(orderUid);
	}
	
//	public List<GBOrder> finGbOrders(Integer uId) {
//		return myGbDao.findAllByuId(uId);
//	}
	
	public List<MyGb> finGbOrder(Integer uid) {
		return myGbDao.findAllByuId(uid);
	}
	
}
