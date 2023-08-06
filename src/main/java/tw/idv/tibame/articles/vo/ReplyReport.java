package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "reply_report")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyReport extends Core{
	
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
