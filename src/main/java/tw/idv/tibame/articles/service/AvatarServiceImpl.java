package tw.idv.tibame.articles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.articles.dao.ArtUserDao;

@Service
public class AvatarServiceImpl implements AvatarService {

	private final ArtUserDao artUserDao;

	@Autowired
	public AvatarServiceImpl(ArtUserDao artUserDao) {
		this.artUserDao = artUserDao;
	}

	@Override
	public byte[] selectAvatar(Integer uid) {
		return artUserDao.findAvatarDataByUserId(uid);
	}
}
