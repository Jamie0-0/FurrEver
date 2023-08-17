package tw.idv.tibame.order.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import tw.idv.tibame.order.vo.Orders;
import tw.idv.tibame.order.vo.ProductOrder;

public interface ProductOrderService {

	boolean createOrders(Orders orders);

	List<String> getMsgs();

	void deleteCartFromRedis(int uid, String username, HashMap<Integer, Integer> cartList);

	List<ProductOrder> selectByUid(int uid);

}