package tw.idv.tibame.articles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.idv.tibame.articles.dao.ReplyDao;
import tw.idv.tibame.articles.dao.ReplyReportDao;
import tw.idv.tibame.articles.vo.Reply;
import tw.idv.tibame.articles.vo.ReplyReport;

@Service
public class ReplyReportServiceImpl implements ReplyReportService {

	@Autowired
	private ReplyReportDao dao;
	@Autowired
	private ReplyDao dao1;

	@Override
	@Transactional
	public int replyReport(ReplyReport replyReport) {
		int status = 0;
		replyReport = dao.save(replyReport);
		Optional<Reply> replyOp = dao1.findById(replyReport.getRrepReplyId());
		Reply reply = replyOp.get();
		reply.setReplyRepCount(reply.getReplyRepCount() + 1);
		status = 1;
		return status;
	}

}
