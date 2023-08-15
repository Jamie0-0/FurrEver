package tw.idv.tibame.articles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.idv.tibame.articles.dao.ArticlesDao;
import tw.idv.tibame.articles.dao.ArticlesReportDao;
import tw.idv.tibame.articles.dao.CommentDao;
import tw.idv.tibame.articles.dao.CommentReportDao;
import tw.idv.tibame.articles.dao.ReplyDao;
import tw.idv.tibame.articles.dao.ReplyReportDao;
import tw.idv.tibame.articles.vo.Article;
import tw.idv.tibame.articles.vo.ArticlesReport;
import tw.idv.tibame.articles.vo.ComReport;
import tw.idv.tibame.articles.vo.Comment;
import tw.idv.tibame.articles.vo.Reply;
import tw.idv.tibame.articles.vo.ReplyReport;

@Service
public class ArticlesReportServiceImpl implements ArticlesReportService {

	@Autowired
	private ArticlesReportDao articlesReportDao;
	@Autowired
	private ArticlesDao articlesDao;
	@Autowired
	private CommentReportDao commentReportDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ReplyReportDao replyReportDao;
	@Autowired
	private ReplyDao replyDao;

//	@Override
//	@Transactional
//	public int artReport(ArticlesReport articlesReport) {
//		int status = 0;
//		dao.save(articlesReport);
//		Optional<Article> articleOptional = dao1.findById(articlesReport.getRepArtId());
//		articleOptional.get().setArtRepCount(articleOptional.get().getArtRepCount()+1);
//		// Optional 要用get()取
//		status = 1;
//		return status;
//	}

	@Override
	@Transactional
	public int artReport(Integer repArtId, Integer crepComId, Integer rrepReplyId, Integer uid, String repReason) {

		switch (repReason) {
		case "1":
			repReason = "不喜歡";
			break;
		case "2":
			repReason = "謾罵";
			break;
		case "3":
			repReason = "18禁";
			break;
		case "4":
			repReason = "內容空泛";
			break;
		case "5":
			repReason = "虐待寵物";
			break;
		case "6":
			repReason = "違反隱私";
			break;
		case "7":
			repReason = "釣魚連結";
			break;
		default:
			repReason = "";
			break;
		}
		int status = 0;
		System.out.println(repReason);

		if (repArtId != 0) {
			// 檢舉文章
			ArticlesReport articlesReport = ArticlesReport.builder().repArtId(repArtId).repUserId(uid)
					.repReason(repReason).build();
			articlesReportDao.save(articlesReport);
			Optional<Article> articleOptional = articlesDao.findById(articlesReport.getRepArtId());
			articleOptional.get().setArtRepCount(articleOptional.get().getArtRepCount() + 1);
			// Optional 要用get()取
			status = 1;

		} else if (crepComId != 0) {
			// 檢舉留言
			ComReport commentReport = ComReport.builder().crepComId(crepComId).crepUserId(uid).crepReason(repReason)
					.build();
			commentReportDao.save(commentReport);
			Optional<Comment> comOptional = commentDao.findById(commentReport.getCrepComId());
			comOptional.get().setComRepCount(comOptional.get().getComRepCount() + 1);
			status = 1;
		} else if (rrepReplyId != 0) {
			// 檢舉留言的回覆
			ReplyReport replyReport = ReplyReport.builder().rrepReplyId(rrepReplyId).rrepUserId(uid)
					.rrepReason(repReason).build();
			replyReportDao.save(replyReport);
			Optional<Reply> replyOptional = replyDao.findById(replyReport.getRrepReplyId());
			replyOptional.get().setReplyRepCount(replyOptional.get().getReplyRepCount());
			status = 1;
		}

		return status;
	}
}
