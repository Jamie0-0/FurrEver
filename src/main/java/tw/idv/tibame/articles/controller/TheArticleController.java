package tw.idv.tibame.articles.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.ArticlesService;
import tw.idv.tibame.articles.vo.Article;

@RestController
public class TheArticleController {

	private final ArticlesService service; // 也可以不用建構子 Autowired在這裡 有紅字還是可以跑

	@Autowired
	public TheArticleController(ArticlesService service) {
		this.service = service;
	}

	// 由forum.html的繼續閱讀進來
	@GetMapping("/article")
	public Optional<Article> selectArticle(@SessionAttribute Integer artId) {
		System.out.println(artId);
		Optional<Article> article = null;

		if (artId != null) {
			article = service.findByArtId(artId);
			if (article.get() == null) {
				System.out.println("找不到此文章，送回forum.html");
				// artList為null傳給前端，前端收到null跳轉回forum.html
			}
		}
		return article;
	}
}
