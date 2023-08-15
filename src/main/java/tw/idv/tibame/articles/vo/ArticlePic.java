package tw.idv.tibame.articles.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
