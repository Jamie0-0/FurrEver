package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.ArticlesReportService;

@RestController
public class ArticleReportController {

	private final ArticlesReportService service;

	@Autowired
	public ArticleReportController(ArticlesReportService service) {
		this.service = service;
	}

	@GetMapping("/artReport")
	public int artReport(@RequestParam Integer repArtId, @RequestParam Integer crepComId,
			@RequestParam Integer rrepReplyId, @RequestParam String repReason, @RequestParam Integer uid) {

		int status = service.artReport(repArtId, crepComId, rrepReplyId, uid, repReason);

		return status;
	}

}
