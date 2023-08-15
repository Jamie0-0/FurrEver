package tw.idv.tibame.myproduct.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tw.idv.tibame.myproduct.vo.MyProduct;

@Component
public interface MyProductDao extends CrudRepository<MyProduct, Integer>{

	@Query(value = "select * \r\n"
			+ "from \r\n"
			+ "	product p \r\n"
			+ "    join product_like pl\r\n"
			+ "		on p.p_id = pl.pl_p_id\r\n"
			+ "where pl.pl_uid = ?1", nativeQuery = true)
	List<MyProduct> findByUid (Integer uid);
		
}
