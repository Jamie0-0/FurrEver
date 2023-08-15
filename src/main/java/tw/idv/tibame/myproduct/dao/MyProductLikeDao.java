package tw.idv.tibame.myproduct.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tw.idv.tibame.myproduct.vo.MyProductLike;

@Component
public interface MyProductLikeDao extends CrudRepository<MyProductLike, Integer>{
	
	@Modifying
	@Query(value = "delete from product_like where pl_uid = ?1 and pl_p_id = ?2",nativeQuery = true)
	Integer deledeleteByplUidAndplPId(Integer plUid, Integer plPId);
}
