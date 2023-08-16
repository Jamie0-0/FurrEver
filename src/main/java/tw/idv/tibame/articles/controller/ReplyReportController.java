package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ReplyReportService;
import tw.idv.tibame.articles.vo.ReplyReport;

@RestController
public class ReplyReportController {

	@Autowired
	private ReplyReportService service;

	@GetMapping("/replyReport")
	public int replyReport(@RequestBody ReplyReport replyReport) {

		return service.replyReport(replyReport);
	}

}
