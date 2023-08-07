package tw.idv.tibame.articles.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.idv.tibame.articles.dao.ArticlesDao;
import tw.idv.tibame.articles.dao.ArticlesLikeDao;
import tw.idv.tibame.articles.vo.Article;
import tw.idv.tibame.articles.vo.ArticlesLike;

@Service
public class ArticlesServiceImpl implements ArticlesService {
	private final ArticlesDao dao1;
	private final ArticlesLikeDao dao2;
	private final RedisTemplate<String, String> redisTemplate;

	@Autowired
	public ArticlesServiceImpl(ArticlesDao dao1, ArticlesLikeDao dao2, RedisTemplate<String, String> redisTemplate) {
		this.dao1 = dao1;
		this.dao2 = dao2;
		this.redisTemplate = redisTemplate;
	}

//	@Override
//	@Transactional
//	public Integer insertArticle(Article article) {
//		Article newArticle = dao1.save(article);
//		return newArticle.getArtId();
//	}

	@Override
	@Transactional
	public Integer insertArticle(Integer artId, int i, String artTitle, String artContent, Integer artLike) {
		Article article = Article.builder().artId(artId).artUserId(2).artTitle(artTitle).artContent(artContent)
				.artLike(artLike).build();

		Article existingArticle = new Article();
		if (artId != null) {
			existingArticle = dao1.findById(artId).orElse(null);
		}

		if (existingArticle != null) {
			article.setArtRepCount(existingArticle.getArtRepCount());
		}
		article = dao1.save(article);

		return article.getArtId();
	}

	@Override
	@Transactional
	public int likeArticle(Integer artId, Integer uid) {
		ArticlesLike articleLike = ArticlesLike.builder().likeArticlesId(artId).likeUserId(uid).build();
		int likeCount = 0;
		Optional<Article> articleOp = dao1.findById(artId);

		if (dao2.findBylikeArticlesIdAndLikeUserId(artId, uid) != null) {
			dao2.delete(articleLike);
			likeCount = likeCount(articleOp.get(), -1);
			System.out.println("-1路線=" + likeCount);
		} else {
			dao2.save(articleLike);
			likeCount = likeCount(articleOp.get(), 1);
			System.out.println("+1路線=" + likeCount);
		}
		return likeCount;
	}

	// 參數傳入 按讚+1 或 -1
	private int likeCount(Article article, int plusMinus) {
		int like = article.getArtLike() + plusMinus;
		article.setArtLike(like);
		dao1.save(article);
		System.out.println("方法=" + like);
		return like;
	}

	@Override
	public void jedisRefresh() {
		redisTemplate.expire("hot", 0, TimeUnit.SECONDS); // key, 數量, 單位
		redisTemplate.expire("new", 0, TimeUnit.SECONDS);
		System.out.println("jedisRefresh");
	}

	@Override
	public Page<Article> selectHot(Pageable pageable) {

		return dao1.selectHot(pageable);
	}

	@Override
	public Page<Article> selectNew(Pageable pageable) {
		return dao1.selectNew(pageable);
	}

	@Override
	public List<Article> search(String searchText) {

		return dao1.search(searchText);
	}

	@Override
	public long selectCountById(Integer artId) {

		return dao1.count();
	}

	@Override
	public int selectPageCount(String searchText) {

		if (searchText == null || searchText.equals("")) {
			return dao1.selectPageCount();
		} else {
			return dao1.selectPageSearchCount(searchText);
		}
	}

	@Override
	public Optional<Article> findByArtId(Integer artId) {

		return dao1.findById(artId);
	}

	@Override
	public byte[] selectPic(Integer artId) {

		return dao1.selectPic(artId);
	}
}
