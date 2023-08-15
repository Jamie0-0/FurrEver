package tw.idv.tibame.user.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import tw.idv.tibame.user.dao.UserDao;
import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Integer login(String uEmail, String uPwd) {
		Integer result = userDao.findByuEmailAndUPwd(uEmail, uPwd);
		
		return result;
	}

	@Transactional
	@Override
	public User register(User user) {
		User result = userDao.findByuEmail(user.getUEmail());
		if (result == null) {
			return userDao.save(user);
		}
		return null;
	}

	@Override
	public User edit(User user) {
		return null;
	}

	@Override
	public User findUser(Integer uid) {
		Optional<User> userOptional = userDao.findById(uid);
		if (userOptional.isPresent()) {
			// 如果找到了用戶，則返回該用戶對象
			return userOptional.get();
		} else {
			// 如果找不到對應的用戶，可以根據需求處理當找不到用戶時的情況
			return null;
		}
	}

	@Transactional
	@Override
	public Integer updateUser(User user) {
		String uEmail = user.getUEmail();
		String uName = user.getUName();
		String uPwd = user.getUPwd();
		String uPhone = user.getUPhone();
		Date uBirth = user.getUBirth();
		String uAbout = user.getUAbout();
		byte[] uPic = user.getUPic();
		Integer uid= user.getUid();

		Integer result = userDao.updateByuid(uEmail, uName, uPwd, uPhone, uBirth, uAbout,uPic, uid);
		
		return result;
	}

	public User findUserName(String uEmail) {
		return userDao.findByuEmail(uEmail);
		}
}
