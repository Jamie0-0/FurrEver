package tw.idv.tibame.articles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.articles.dao.ReplyDao;
import tw.idv.tibame.articles.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao dao;

	@Override
	public List<Reply> selectReply(Integer replyComId) {

		return dao.findByReplyComId(replyComId);
	}

	@Override
	public int insertReply(Reply reply) {

		dao.save(reply);

		return 1;
	}

}
