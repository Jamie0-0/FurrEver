package tw.idv.tibame.articles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.CommentServiceImpl;
import tw.idv.tibame.articles.vo.Comment;

@RestController
public class CommentController {
	private final CommentServiceImpl service;

	@Autowired
	public CommentController(CommentServiceImpl service) {
		this.service = service;
	}

	@GetMapping("/comment") // 後OK
	public List<Comment> selectComment(@SessionAttribute Integer artId) {
		return service.findByComArtId(artId);
	}
}
