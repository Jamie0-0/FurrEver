package tw.idv.tibame.articles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.idv.tibame.articles.service.ArticlesService;
import tw.idv.tibame.articles.vo.Article;

@RestController
public class ArticlesController {

	private final ArticlesService service;

	@Autowired
	public ArticlesController(ArticlesService service) {
		this.service = service;
	}

	@GetMapping("/forum/hot") // 前端要改 後OK 可抓文章、留言、發文章的人
	public List<Article> getHotArticles(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Article> hotArticlesPage = service.selectHot(pageable);
		List<Article> hotArticlesList = hotArticlesPage.getContent();
		return hotArticlesList;
	}

	@GetMapping("/forum/getPic") // 後OK
	public byte[] selectPic(@RequestParam Integer artId) {

		return service.selectPic(artId);
	}

	@GetMapping("/forum/new") // 前端要改 後OK 可抓文章、留言、發文章的人
	public List<Article> getNewArticles(@RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		System.out.println("page=" + page);
		System.out.println("size=" + size);
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Article> newArticlesPage = service.selectNew(pageable);
		List<Article> newArticlesList = newArticlesPage.getContent();
		return newArticlesList;
	}

	@GetMapping("/forum/search") // 前端要改 後OK
	public List<Article> search(@RequestParam String searchText) {

		return service.search(searchText.trim());
	}

	@GetMapping("/goToArticle") // ??
	public String forwardToComment(HttpSession httpSession, @RequestParam Integer artId) {

		httpSession.setAttribute("artId", artId);

		return "forward:/comment";
	}

//	@GetMapping("/forum") // 後OK
//	public Optional<Article> find(@RequestParam Integer artId) {
//		return service.findByArtId(artId);
//	}

}
