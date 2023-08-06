package tw.idv.tibame.articles.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles_Pics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePic extends Core {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer picId;

	private Integer picArtId;

	private byte[] picContent;

	@ManyToOne
	@JoinColumn(name = "picArtId", insertable = false, updatable = false)
	@JsonBackReference
	private Article article;

}
