package tw.idv.tibame.order.dao;

import java.util.List;

import tw.idv.tibame.order.vo.SubOrder;

public interface SubOrderDao {

	int insertSubOrder(SubOrder subOrder);

	List<SubOrder> selectBySoOrderNum(int so_order_num);

}