package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

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
@Table(name = "com_report")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crepId;

	private Integer crepComId;

	private Integer crepUserId;

	private String crepReason;
	@Transient
	private Timestamp crepTime;
	@Transient
	private String crepStatus;

	@ManyToOne
	@JoinColumn(name = "crepComId", insertable = false, updatable = false)
	@JsonBackReference
	private Comment comment;

}
