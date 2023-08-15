package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles_report")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesReport extends Core {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer repId;

	private Integer repArtId;

	private Integer repUserId;

	private String repReason;
	@Column(insertable = false)
	private Timestamp repTime;
	@Column(insertable = false)
	private String repStatus;

}
