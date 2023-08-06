package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.CommentService;
import tw.idv.tibame.articles.vo.Comment;

@RestController
public class CommentInsertController {

	private final CommentService service;

	@Autowired
	public CommentInsertController(CommentService service) {
		this.service = service;
	}

	@GetMapping("/commentInsert") // 前後OK
	public int insertReply(@RequestParam Integer comArtId, @RequestParam Integer comUserId,
			@RequestParam String comContent) {
		System.out.println(comArtId);
		System.out.println(comUserId);
		System.out.println(comContent);
		Comment comment = Comment.builder().comArtId(comArtId).comUserId(comUserId).comContent(comContent).build();
		comment = service.insertComment(comment);
		if (comment != null) {
			return 1;
		}
		return 0;
	}

}
