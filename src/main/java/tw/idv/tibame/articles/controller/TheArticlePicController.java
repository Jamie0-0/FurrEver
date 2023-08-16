package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.ArticlePicService;

@RestController
public class TheArticlePicController {

	@Autowired
	private ArticlePicService service;

	@GetMapping("/carousel")
	public byte[] selectCarouselPic(@SessionAttribute Integer artId, @RequestParam Integer picOrder) {
		return service.selectCarouselPic(artId, picOrder);
	}
}
