package tw.idv.tibame.articles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.articles.dao.ArtUserDao;

@Service
public class AvatarServiceImpl implements AvatarService {

	@Autowired
	private ArtUserDao artUserDao;

	@Override
	public byte[] selectAvatar(Integer uid) {
		return artUserDao.findAvatarDataByUserId(uid);
	}
}
