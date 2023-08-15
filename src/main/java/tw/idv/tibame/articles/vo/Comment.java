package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Core {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comId;
	@Column(updatable = false)
	private Integer comArtId;
	@Column(updatable = false)
	private Integer comUserId;

	private String comContent;
	@Column(insertable = false, updatable = false)
	private Timestamp comDateTime;
	@Column(insertable = false)
	private Integer comRepCount;
	@Column(insertable = false)
	private String comStatus;

	@ManyToOne
	@JoinColumn(name = "comUserId", insertable = false, updatable = false) // 自己對應到USER的PK
	@JsonManagedReference
	private ArtUser artUser; // 對應到 artUser 實體

	@ManyToOne
	@JoinColumn(name = "comArtId", insertable = false, updatable = false)
	@JsonBackReference
	private Article article;

//    @OneToMany(mappedBy = "comment")
//    @JsonManagedReference
//    private List<Reply> replies;

//    @OneToMany(mappedBy = "comment")
//    @JsonManagedReference
//    private List<ComReport> comReports;
}
