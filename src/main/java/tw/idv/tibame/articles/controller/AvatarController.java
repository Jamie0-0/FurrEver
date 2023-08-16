package tw.idv.tibame.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.articles.service.AvatarService;

@RestController
public class AvatarController {

	@Autowired
	private AvatarService service;

	@GetMapping(value = "/avatar", produces = MediaType.IMAGE_GIF_VALUE)
	public byte[] selectAvatar(@RequestParam Integer uid) {
		return service.selectAvatar(uid);

	}
}
