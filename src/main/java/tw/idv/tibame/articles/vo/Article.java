package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Core {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artId;
	@Column(updatable = false)
	private Integer artUserId;

	private String artTitle;

	private String artContent;
	@Column(insertable = false, updatable = false)
	private Timestamp artPoTime;
	@Column(insertable = false)
	private Integer artLike;
	@Transient
	private final Integer comCount = getComCount();
	@Column(insertable = false)
	private Integer artRepCount;
	@Column(insertable = false, updatable = false)
	private String artStatus;

	@OneToMany(mappedBy = "article")
	@JsonManagedReference
	private List<Comment> comments;

	@OneToMany(mappedBy = "article")
	@JsonManagedReference
	private List<ArticlePic> articlePics;

	@ManyToOne
	@JoinColumn(name = "artUserId", insertable = false, updatable = false)
	@JsonManagedReference // 避免stackoverflow
	private ArtUser artUser;

	@Transient
	public Integer getComCount() {
		// 三元運算 comments != null 回傳comments.size() 否則為0
		return comments != null ? comments.size() : 0;
	}
}
