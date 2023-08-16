package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.ArticlesService;

@RestController
public class ArticleLikeController {

	@Autowired
	private ArticlesService service;

	@GetMapping("/artLike")
	private int likeArticle(@RequestParam Integer artId, @SessionAttribute Integer uid) {
		int status = service.likeArticle(artId, uid);
		return status;
	}
}
