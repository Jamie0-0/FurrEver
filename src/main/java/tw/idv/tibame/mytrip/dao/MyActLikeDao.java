package tw.idv.tibame.mytrip.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tw.idv.tibame.mytrip.vo.MyActLike;

@Component
public interface MyActLikeDao extends CrudRepository<MyActLike, Integer>{
	
	@Modifying
	@Query(value = "delete from act_like where t_act_id = ?1 and uid = ?2 ", nativeQuery = true)
	Integer deledeleteByTActIdAndUid(Integer tActId, Integer uid);
}
