package tw.idv.tibame.articles.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tw.idv.tibame.articles.vo.Article;
import tw.idv.tibame.articles.vo.ArticlePic;

public interface ArticlesDao extends CrudRepository<Article, Integer> {

	@Query(value = "SELECT a FROM Article a JOIN a.artUser u WHERE a.artStatus = '1' ORDER BY a.artLike DESC", countQuery = "SELECT COUNT(a) FROM Article a WHERE a.artStatus = '1'")
	Page<Article> selectHot(Pageable pageable);

	@Query(value = "SELECT a FROM Article a JOIN a.artUser u WHERE a.artStatus = '1' ORDER BY a.artPoTime DESC", countQuery = "SELECT COUNT(a) FROM Article a WHERE a.artStatus = '1'")
	Page<Article> selectNew(Pageable pageable);

	@Query(value = "SELECT a FROM Article a JOIN a.artUser u WHERE a.artStatus = '1' AND a.artTitle LIKE %:searchText% ORDER BY a.artLike DESC")
	List<Article> search(String searchText, Pageable pageable);

	@Query(value = "SELECT a FROM Article a JOIN a.artUser u WHERE a.artStatus = '1' AND a.artTitle LIKE %:searchText% ORDER BY a.artLike DESC")
	List<Article> search(String searchText);

	@Query(value = "SELECT MIN(ap.pic_content) AS pic_content FROM articles_pics ap WHERE ap.pic_art_id = ?1", nativeQuery = true)
	byte[] selectPic(Integer artId);

	@Query(value = "SELECT uPic from USER where uid = ?1", nativeQuery = true)
	ArticlePic selectAvatar(Integer uid);

	@Query(value = "SELECT count(*) FROM articles WHERE art_status = '1'", nativeQuery = true)
	int selectPageCount();

	@Query(value = "SELECT count(*) FROM articles WHERE art_status = '1' AND art_title LIKE %?1%", nativeQuery = true)
	int selectPageSearchCount(String searchText);

	@Query(value = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like FROM articles a JOIN a.user u WHERE a.artStatus = '1' ORDER BY a.artLike DESC", nativeQuery = true)
	List<Article> selectAllHot();

	Article save(Article article);

	@Query(value = "INSERT INTO articles_pics (pic_content, pic_art_id) VALUES (?1, ?2)", nativeQuery = true)
	int insertArticlePic(int pic_art_id, List<byte[]> imageList);

	@Query(value = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like FROM articles a JOIN USER u ON a.art_user_id = u.uid WHERE art_status = '1' ORDER BY art_po_time DESC", nativeQuery = true)
	List<Article> selectAllNew();

//	List<Article> selectRedis(String page, String key);

//	int countByArtId(Integer artId);

}