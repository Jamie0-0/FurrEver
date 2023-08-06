package tw.idv.tibame.articles.vo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ArticlesLikeId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer likeArticlesId;

	private Integer likeUserId;

}
