package tw.idv.tibame.order.dao;

import java.util.List;

import tw.idv.tibame.order.vo.SubProduct;

public interface SubProductDao {

	int insertSubProduct(SubProduct subProduct);

	List<SubProduct> selectByOrderId(int order_id);

}