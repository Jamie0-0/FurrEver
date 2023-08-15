package tw.idv.tibame.myOrders.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import tw.idv.tibame.myOrders.vo.MyGb;

@Component
public interface MyGbDao extends JpaRepository<MyGb, Integer>{

//	List<GBOrder> findAllByuId(Integer uId);
	
	@Query(value = "select * "
			+ "from GB gb "
			+ "left join gb_order gbo "
			+ "on gb.gb_id = gbo.gb_order_id "
			+ "left join product p "
			+ "on gb.gb_p_id = p.p_id "
			+ "where gbo.uid = :uid ", nativeQuery = true)
	List<MyGb> findAllByuId(Integer uid);
	
}
