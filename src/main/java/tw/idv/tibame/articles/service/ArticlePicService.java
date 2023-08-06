package tw.idv.tibame.articles.service;

import java.util.List;

public interface ArticlePicService {

	byte[] selectCarouselPic(Integer artId, Integer picOrder);

	int save(Integer picArtId, List<byte[]> imageList);

}
