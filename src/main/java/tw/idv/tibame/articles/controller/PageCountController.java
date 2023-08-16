package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ArticlesService;

@RestController
public class PageCountController {

	@Autowired
	private ArticlesService service;

	@GetMapping("/forumPage")
	public int selectPageCount(@RequestParam(defaultValue = "") String searchText) {
		return service.selectPageCount(searchText);
	}
}
