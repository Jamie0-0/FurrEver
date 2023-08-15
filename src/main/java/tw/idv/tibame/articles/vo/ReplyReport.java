package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reply_report")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyReport extends Core {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rrepId;

	private Integer rrepReplyId;

	private Integer rrepUserId;

	private String rrepReason;
	@Transient
	private Timestamp rrepTime;
	@Transient
	private String rrepStatus;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonBackReference
	@JoinColumn(name = "rrepReplyId", insertable = false, updatable = false)
	private Reply reply;

}
