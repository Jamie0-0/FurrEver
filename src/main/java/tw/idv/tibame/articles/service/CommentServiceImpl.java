package tw.idv.tibame.articles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import tw.idv.tibame.articles.dao.CommentDao;
import tw.idv.tibame.articles.vo.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private CommentDao dao;

	public List<Comment> findByComArtId(Integer comArtId) {
		return dao.findByComArtId(comArtId);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public List<Comment> selectComment(Integer comArtId) {

		List<Comment> list = null;

		try {

			String comments = redisTemplate.opsForValue().get("comArtid:" + comArtId); // redis

			if (comments == null || comments.equals("")) {
				list = dao.findByComArtId(comArtId); // mySql

				String listString = list.toString();
				redisTemplate.opsForValue().set("comArtid:" + comArtId, listString); // redis
			}
		} catch (Exception e) {
			System.out.println("selectComRedis錯誤");
			e.printStackTrace();
			list = dao.findByComArtId(comArtId);
		}
		return list;

	}

	@Override
	public Comment updateComment(Comment comment) {

		return dao.save(comment);
	}

	@Override
	public int selectComCount(Integer comArtId) {

		return dao.countByComArtId(comArtId);
	}

	@Override
	public Comment insertComment(Comment comment) {

		return dao.save(comment);
	}

}
