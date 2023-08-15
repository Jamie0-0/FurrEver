package tw.idv.tibame.friends.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.friends.service.FriendsService;
import tw.idv.tibame.friends.vo.Friend;

@RestController
public class FriendsController {

	@Autowired
	private FriendsService service;

	@GetMapping("/getFriends")
	public List<Friend> getFriends(@RequestParam Integer uid, @RequestParam String fStatus) {

		return service.getFriends(uid, fStatus);
	}

	@GetMapping("/confirmFriend")
	public int confirmFriends(@RequestParam Integer uid, @RequestParam Integer fId, @RequestParam String fStatus) {

		return service.confirmFriend(uid, fId, fStatus);
	}

	@GetMapping("/inviteFriend")
	public int inviteFriend(@RequestParam Integer uid, @RequestParam Integer fId) {

		return service.inviteFriend(uid, fId);
	}

	@GetMapping("/deleteFriend")
	public int deleteFriend(@RequestParam Integer uid, @RequestParam Integer fId) {
		return service.deleteFriend(uid, fId);

	}
}
