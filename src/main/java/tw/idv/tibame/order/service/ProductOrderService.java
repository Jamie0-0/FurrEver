package tw.idv.tibame.order.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import tw.idv.tibame.order.vo.Orders;
import tw.idv.tibame.order.vo.ProductOrder;

public interface ProductOrderService {

	boolean createOrders(Orders orders);

	List<String> getMsgs();

	void deleteCartFromRedis(HttpSession session);

	List<ProductOrder> selectByUid(int uid);

//	boolean createOrders(ProductOrder productOrder, SubOrder subOrder, SubProduct subProduct); // 測試用

}