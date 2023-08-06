package tw.idv.tibame.articles.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.Comment;

@Repository
public interface CommentDao extends CrudRepository<Comment, Integer>{
	
	
	List<Comment> findByComArtId(Integer comArtId);

	int countByComArtId(Integer comArtId);

}
