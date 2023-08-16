package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.idv.tibame.articles.service.CommentService;
import tw.idv.tibame.articles.vo.Comment;

@RestController
public class CommentInsertController {

	@Autowired
	private CommentService service;

	@GetMapping("/commentInsert")
	public int insertReply(@RequestParam Integer comArtId, @SessionAttribute Integer uid,
			@RequestParam String comContent) {

		Comment comment = Comment.builder().comArtId(comArtId).comUserId(uid).comContent(comContent).build();
		comment = service.insertComment(comment);
		if (comment != null) {
			return 1;
		}
		return 0;
	}

}
