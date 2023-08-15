package tw.idv.tibame.articles.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles_like")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ArticlesLikeId.class)
@Builder
public class ArticlesLike {

	@Id
	private Integer likeArticlesId;
	@Id
	private Integer likeUserId;

}
