package tw.idv.tibame.articles.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.ArtUser;

@Repository
public interface ArtUserDao extends CrudRepository<ArtUser, Integer>{

	@Query("SELECT u.uPic FROM User u WHERE u.uid = :userId")
	byte[] findAvatarDataByUserId(Integer userId);

}
