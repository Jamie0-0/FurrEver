package tw.idv.tibame.articles.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import tw.idv.tibame.articles.service.ArticlePicService;
import tw.idv.tibame.articles.service.ArticlesService;

@RestController
public class ArticleInsertController {

	@Autowired
	private ArticlesService service;
	@Autowired
	private ArticlePicService service1;

	@PostMapping("/insertArticle")
	public int insertArticle(@SessionAttribute Integer uid, @RequestParam(required = false) Integer artId,
			@RequestParam String artTitle, @RequestParam String artContent,
			@RequestParam(required = false) Integer artLike,
			@RequestParam(required = false) List<MultipartFile> images) {
		System.out.println("發文人" + uid);
		List<byte[]> imageList = new ArrayList<>();
		if (images != null) {
			for (MultipartFile image : images) {

				InputStream inputStream;
				try {
					inputStream = image.getInputStream();
					byte[] imageData = inputStream.readAllBytes();
					imageList.add(imageData);
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Integer artPicId = service.insertArticle(artId, uid, artTitle, artContent, artLike);
		int status = service1.save(artPicId, imageList);

		return status;
	}
}
