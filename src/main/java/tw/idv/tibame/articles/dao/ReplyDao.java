package tw.idv.tibame.articles.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.Reply;

@Repository
public interface ReplyDao extends CrudRepository<Reply, Integer>{

	List<Reply> findByReplyComId(Integer replyComId);

}
