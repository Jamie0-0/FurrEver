package tw.idv.tibame.articles.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ReplyService;
import tw.idv.tibame.articles.vo.Reply;


@RestController
public class ReplyController {

    private final ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @GetMapping("/reply") //後OK
    public List<Reply> selectReply(@RequestParam Integer replyComId) {
        
        return service.selectReply(replyComId);
    }
}


