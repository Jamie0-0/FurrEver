package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ArticlesService;

//後端可用
@RestController
public class ArticleLikeController{

	
    private final ArticlesService service;

    @Autowired
    public ArticleLikeController(ArticlesService service) {
        this.service = service;
    }
		
		
		@GetMapping("/artLike")
		private int likeArticle(@RequestParam Integer artId,
								 @RequestParam Integer uid) {
			int status = service.likeArticle(artId, uid);
			return status;
		}
}
