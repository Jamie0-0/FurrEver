package tw.idv.tibame.articles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.idv.tibame.articles.dao.ReplyDao;
import tw.idv.tibame.articles.dao.ReplyReportDao;
import tw.idv.tibame.articles.vo.Reply;
import tw.idv.tibame.articles.vo.ReplyReport;

@Service
public class ReplyReportServiceImpl implements ReplyReportService{

	private final ReplyReportDao dao;
	private final ReplyDao dao1;
	
	@Autowired
	public ReplyReportServiceImpl(ReplyReportDao dao, ReplyDao dao1) {
		this.dao = dao;
		this.dao1 = dao1;
	}

//	@Override  想去問為何這樣寫不行 reply會是null
//	@Transactional
//	public int replyReport(ReplyReport replyReport) {
//		int status = 0;
//		replyReport = dao.save(replyReport);
//		System.out.println("rrepId="+replyReport.getRrepId());
//		Reply reply = replyReport.getReply();
//		reply.setReplyRepCount(reply.getReplyRepCount()+1);
//		status = 1;
//		return status;
//	}
	@Override
	@Transactional
	public int replyReport(ReplyReport replyReport) {
		int status = 0;
		replyReport = dao.save(replyReport);
		Optional<Reply> replyOp = dao1.findById(replyReport.getRrepReplyId());
		Reply reply = replyOp.get();
		reply.setReplyRepCount(reply.getReplyRepCount()+1);
		status = 1;
		return status;
	}
	
	
}
