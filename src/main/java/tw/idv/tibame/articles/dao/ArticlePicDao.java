package tw.idv.tibame.articles.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.ArticlePic;

@Repository
public interface ArticlePicDao extends CrudRepository<ArticlePic, Integer> {

	@Query(value = "SELECT pic_content FROM FurrEver.articles_pics where pic_art_id = ?1 LIMIT 1 OFFSET ?2", nativeQuery = true)
	Optional<byte[]> selectCarouselPic(Integer artId, Integer picOrder);

	void deleteByPicArtId(Integer picArtId);

}
