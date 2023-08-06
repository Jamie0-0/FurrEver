package tw.idv.tibame.articles.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.ArtUser;
import tw.idv.tibame.articles.vo.ArticlePic;

@Repository
public interface ArticleUserDao extends CrudRepository<ArtUser, Integer>{

	ArticlePic findUPicByUid(Integer uid);

}
