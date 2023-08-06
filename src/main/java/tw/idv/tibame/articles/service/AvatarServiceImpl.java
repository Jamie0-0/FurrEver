package tw.idv.tibame.articles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import tw.idv.tibame.articles.dao.ArtUserDao;

@Service
public class AvatarServiceImpl implements AvatarService{
	
	private final ArtUserDao artUserDao;
	
	@Autowired
	public AvatarServiceImpl(ArtUserDao artUserDao) {
		this.artUserDao = artUserDao;
	}
//	private final RedisTemplate<String, byte[]> avataRedisTemplate;
//	
//	@Autowired
//	public AvatarServiceImpl(UserDao userDao,@Qualifier("myTemplate") RedisTemplate<String, byte[]> avataRedisTemplate) {
//		this.userDao = userDao;
//		this.avataRedisTemplate = avataRedisTemplate;
//	}

	@Override
	public byte[] selectAvatar(Integer uid) {
		return artUserDao.findAvatarDataByUserId(uid);
	//	return avataRedisTemplate.opsForValue().get("user_avatar:"+uid);
	}
}
