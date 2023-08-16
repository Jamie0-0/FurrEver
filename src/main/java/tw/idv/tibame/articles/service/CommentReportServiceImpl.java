package tw.idv.tibame.articles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.idv.tibame.articles.dao.CommentDao;
import tw.idv.tibame.articles.dao.CommentReportDao;
import tw.idv.tibame.articles.vo.ComReport;
import tw.idv.tibame.articles.vo.Comment;

@Service
public class CommentReportServiceImpl implements CommentReportService {

	@Autowired
	private CommentReportDao dao;
	@Autowired
	private CommentDao dao1;

	@Override
	@Transactional
	public int comReport(ComReport comReport) {
		int status = 0;
		dao.save(comReport);
		Optional<Comment> commentOptional = dao1.findById(comReport.getCrepComId());
		commentOptional.get().setComRepCount(commentOptional.get().getComRepCount() + 1);
		// Optional 要用get()取
		status = 1;
		return status;
	}
}
