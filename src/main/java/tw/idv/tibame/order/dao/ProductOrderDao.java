package tw.idv.tibame.order.dao;

import java.util.List;

import tw.idv.tibame.order.vo.ProductOrder;

public interface ProductOrderDao {

	int insertProductOrder(ProductOrder productOrder);

	ProductOrder selectByUid(int order_uid); // 會員查自己所有訂單 -> join子訂單+子訂單明細?

	List<ProductOrder> selectAllProductOrderByUid(int uid); // 會員查自己所有訂單 -> join子訂單+子訂單明細?

	void deleteKeys(String uid);
}
