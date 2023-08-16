package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.ArticlesService;

@RestController
public class TheArtDnoneController {

	@Autowired
	private ArticlesService service;

	@GetMapping("/artDnone")
	public long selectCountById(@SessionAttribute Integer artId) {

		long count = service.selectCountById(artId);

		return count;
	}

}
