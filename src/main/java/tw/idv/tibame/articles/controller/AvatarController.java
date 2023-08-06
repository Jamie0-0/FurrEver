package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.AvatarService;

@RestController
public class AvatarController {

	private final AvatarService service;

	@Autowired
	public AvatarController(AvatarService service) {
		this.service = service;
	}
	// 後OK
	@GetMapping(value = "/avatar", produces = MediaType.IMAGE_GIF_VALUE)
	public byte[] selectAvatar(@RequestParam Integer uid) {
		return service.selectAvatar(uid);

	}
}
