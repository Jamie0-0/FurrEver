package tw.idv.tibame.articles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ReplyService;
import tw.idv.tibame.articles.vo.Reply;

@RestController
public class ReplyController {

	@Autowired
	private ReplyService service;

	@GetMapping("/reply")
	public List<Reply> selectReply(@RequestParam Integer replyComId) {

		return service.selectReply(replyComId);
	}
}
