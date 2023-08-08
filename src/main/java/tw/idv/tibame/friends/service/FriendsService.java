package tw.idv.tibame.friends.service;

import java.util.List;

import tw.idv.tibame.friends.vo.Friend;

public interface FriendsService {

	List<Friend> getFriends(Integer uid, String fStatus);

	int confirmFriend(Integer uid, Integer fId, String fStatus);

	int inviteFriend(Integer uid, Integer fId);

	int deleteFriend(Integer uid, Integer fId);

}
