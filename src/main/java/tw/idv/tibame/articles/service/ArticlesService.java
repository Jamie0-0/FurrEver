package tw.idv.tibame.articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.idv.tibame.articles.vo.Article;

public interface ArticlesService {

//	Integer insertArticle(Article article);

	int likeArticle(Integer artId, Integer uid);

	void jedisRefresh();

	Page<Article> selectHot(Pageable pageable);

	Page<Article> selectNew(Pageable pageable);

	List<Article> search(String searchText);

	long selectCountById(Integer artId);

	int selectPageCount(String searchText);

	Optional<Article> findByArtId(Integer artId);

	byte[] selectPic(Integer artId);

	Integer insertArticle(Integer artId, int i, String artTitle, String artContent, Integer artLike);

}