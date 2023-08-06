package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.CommentService;

@RestController
public class ComCountController {

	private final CommentService service;

	@Autowired
	public ComCountController(CommentService service) {
		this.service = service;
	}

	@GetMapping("/artComCount") // 後OK
	public int selectComCount(@RequestParam Integer comArtId) {
		return service.selectComCount(comArtId);
	}

}
