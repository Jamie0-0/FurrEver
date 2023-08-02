package articles.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles_like", catalog = "FurrEver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ArticlesLikeId.class)
public class ArticlesLike {
	
	@Id
	@Column
	private Integer like_articles_id;
	@Id
	@Column
	private Integer like_user_id;

}
