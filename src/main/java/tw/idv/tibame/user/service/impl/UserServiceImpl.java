package tw.idv.tibame.user.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.user.dao.UserDao;
import tw.idv.tibame.user.service.UserService;
import tw.idv.tibame.user.vo.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Integer login(String uEmail, String uPwd) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(uPwd.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
			uPwd = DatatypeConverter.printHexBinary(digest).toUpperCase();
			Integer result = userDao.findByuEmailAndUPwd(uEmail, uPwd);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public User register(User user) {
		User result = userDao.findByuEmail(user.getUEmail());
		if (result == null) {
			String uPwd = "";
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(user.getUPwd().getBytes(StandardCharsets.UTF_8));
				byte[] digest = md.digest();
				uPwd = DatatypeConverter.printHexBinary(digest).toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
			user.setUPwd(uPwd);
			return userDao.save(user);
		}
		return null;
	}

	@Override
	public User findUser(Integer uid) {
		Optional<User> userOptional = userDao.findById(uid);
		if (userOptional.isPresent()) {
			// 如果找到了用戶，則返回該用戶對象
			userOptional.get().setUPwd("");
			return userOptional.get();
		}
		return null;
	}

	@Transactional
	@Override
	public Integer updateUser(User user) {
		String uEmail = user.getUEmail();
		String uName = user.getUName();
		String uPwd = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUPwd().getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
			uPwd = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String uPhone = user.getUPhone();
		Date uBirth = user.getUBirth();
		String uAbout = user.getUAbout();
		byte[] uPic = user.getUPic();
		Integer uid = user.getUid();

		Integer result = userDao.updateByuid(uEmail, uName, uPwd, uPhone, uBirth, uAbout, uPic, uid);

		return result;
	}

	@Override
	public User findUserName(String uEmail) {
		return userDao.findByuEmail(uEmail);
	}
}
