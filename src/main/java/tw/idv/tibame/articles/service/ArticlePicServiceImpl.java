package tw.idv.tibame.articles.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.idv.tibame.articles.dao.ArticlePicDao;
import tw.idv.tibame.articles.vo.ArticlePic;

@Service
public class ArticlePicServiceImpl implements ArticlePicService {

	@Autowired
	private ArticlePicDao dao;

	@Override
	public byte[] selectCarouselPic(Integer artId, Integer picOrder) {
		Optional<byte[]> optional = dao.selectCarouselPic(artId, picOrder);
		Resource resource = new ClassPathResource("static/images/steal.jpg");
		byte[] picture = null;
		try {
			picture = optional.isPresent() ? optional.get()
					: optional.orElse(Files.readAllBytes(resource.getFile().toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return picture;
	}

	@Override
	@Transactional
	public int save(Integer picArtId, List<byte[]> imageList) {
		int status = 0;

		dao.deleteByPicArtId(picArtId);
		for (byte[] image : imageList) {
			ArticlePic articlePic = new ArticlePic();
			articlePic.setPicArtId(picArtId);
			articlePic.setPicContent(image);
			dao.save(articlePic);
		}
		status = 1;
		return status;
	}

}
