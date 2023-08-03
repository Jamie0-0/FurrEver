package tw.idv.tibame.master.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.master.vo.Master;

@Repository
public interface MasterDao extends CrudRepository<Master, Integer>{
	
	Master findBymEmail(String mEmail);
	
	@Query(value = "select m_id from MASTER where m_email =:mEmail and m_pwd =:mPwd", nativeQuery = true)
	Integer findBymEmailAndmPwd(@Param("mEmail") String mEmail, @Param("mPwd") String mPwd);
}
