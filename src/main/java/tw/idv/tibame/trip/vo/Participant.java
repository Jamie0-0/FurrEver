package tw.idv.tibame.trip.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@IdClass(ParticipantId.class)
public class Participant {

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
	private Trip participants;
}
