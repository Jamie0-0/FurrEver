package tw.idv.tibame.product.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tw.idv.tibame.product.vo.ProductLike;

@Component
public interface ProductLikeDao extends CrudRepository<ProductLike, Integer>{
	
	@Modifying
	@Query(value = "delete from product_like where pl_uid = ?1 and pl_p_id = ?2",nativeQuery = true)
	Integer deledeleteByplUidAndplPId(Integer plUid, Integer plPId);
}
