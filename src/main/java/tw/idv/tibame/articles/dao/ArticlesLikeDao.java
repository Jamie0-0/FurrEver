package tw.idv.tibame.articles.dao;

import org.springframework.data.repository.CrudRepository;

import tw.idv.tibame.articles.vo.ArticlesLike;

public interface ArticlesLikeDao extends CrudRepository<ArticlesLike, Integer>{

	int deleteByLikeArticlesIdAndLikeUserId(Integer artId, Integer uid);
	
	ArticlesLike findBylikeArticlesIdAndLikeUserId(Integer artId, Integer uid);

}
