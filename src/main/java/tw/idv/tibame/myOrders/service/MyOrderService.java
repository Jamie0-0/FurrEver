package tw.idv.tibame.myOrders.service;

import java.util.List;

import tw.idv.tibame.myOrders.vo.MyGb;
import tw.idv.tibame.myOrders.vo.MyProductOrder;

public interface MyOrderService {

	List<MyProductOrder> findAll(Integer orderUid);

	List<MyGb> finGbOrder(Integer uid);
}