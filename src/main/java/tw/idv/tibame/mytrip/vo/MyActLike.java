package tw.idv.tibame.mytrip.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@IdClass(MyActLikeId.class)
@JsonIgnoreProperties("likeactivity")
public class MyActLike {

	@Id
	@Column(name = "t_act_id")
	private Integer tActId;

	@Id
	@Column(name = "uid")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "tActId", insertable = false, updatable = false)
	private MyTrip likeactivity;
}
