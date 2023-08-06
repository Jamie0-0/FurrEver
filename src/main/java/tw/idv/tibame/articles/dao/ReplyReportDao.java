package tw.idv.tibame.articles.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.articles.vo.ReplyReport;

@Repository
public interface ReplyReportDao extends CrudRepository<ReplyReport, Integer>{


}
