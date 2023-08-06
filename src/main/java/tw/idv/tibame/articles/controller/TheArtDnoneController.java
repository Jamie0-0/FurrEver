package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.ArticlesService;

@RestController
public class TheArtDnoneController {

	private final ArticlesService service;

	@Autowired
	public TheArtDnoneController(ArticlesService service) {
		this.service = service;
	}

	@GetMapping("/artDnone") // 有session=後ok
	public long selectCountById(@SessionAttribute Integer artId) {
		artId = 1;
		long count = service.selectCountById(artId);

		return count;
	}

}
