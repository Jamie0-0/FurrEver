package tw.idv.tibame.articles.service;

import java.util.List;

import tw.idv.tibame.articles.vo.Reply;

public interface ReplyService{
	

	List<Reply> selectReply(Integer replyComId);

	int insertReply(Reply reply);

}
