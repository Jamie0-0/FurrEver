package tw.idv.tibame.articles.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.ComReport;

@Repository
public interface CommentReportDao extends CrudRepository<ComReport, Integer> {

}
