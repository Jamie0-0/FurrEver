package tw.idv.tibame.articles.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
