package tw.idv.tibame.trip.vo;

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
@Table(name = "act_like")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ActLikeId.class)
public class ActLike {

	@Id
	@Column(name = "t_act_id")
	private Integer tActId;

	@Id
	@Column(name = "uid")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "tActId", insertable = false, updatable = false)
	private Trip likeactivity;
}
