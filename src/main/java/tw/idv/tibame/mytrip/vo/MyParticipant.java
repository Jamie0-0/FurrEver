package tw.idv.tibame.mytrip.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MyParticipantId.class)
public class MyParticipant {

	@Id
	@Column(name = "t_act_id")
	private Integer tActId;

	@Id
	@Column(name = "uid")
	private Integer uid;

	@Column(name = "uid_join", nullable = false)
	private String joinStatus;

	@ManyToOne
	@JoinColumn(name = "tActId", insertable = false, updatable = false)
	@JsonBackReference
	private MyTrip participants;
}
