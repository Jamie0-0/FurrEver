package tw.idv.tibame.user.service;

import tw.idv.tibame.user.vo.User;

public interface UserService {

	Integer login(String uEmail, String uPW);
	
	User register(User user);
	
	User edit(User user);
	
	User findUser(Integer uid);
	
	Integer updateUser(User user);
	
	User findUserName(String uEmail);
}
