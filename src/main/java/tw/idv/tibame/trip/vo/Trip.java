package tw.idv.tibame.trip.vo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRIP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_act_id")
	private Integer tActId;

	@Column(name = "uid")
	private Integer uid;

	@Column(name = "t_act_type")
	private Integer tActType;

	@Column(name = "t_act_name")
	private String tActName;

	@Column(name = "t_act_desc")
	@Lob
	private String tActDesc;

	@Column(name = "t_act_city")
	private Integer tActCity;

	@Column(name = "t_act_loc")
	private String tActLoc;

	@Column(name = "t_act_time")
	private LocalDateTime tActTime;

	@Column(name = "t_act_ppl")
	private Integer tActPpl;

	@Column(name = "t_act_pic_one")
	@Lob
	private byte[] tActPicOne;

	@Column(name = "t_act_pic_two")
	@Lob
	private byte[] tActPicTwo;

	@Column(name = "t_act_bdg")
	private Integer tActBdg;

	@Column(name = "t_act_status")
	private String tActStatus;

	@Column(name = "gm_id")
	private Integer gmId;

	@Column(name = "gm_date")
	private LocalDateTime gmDate;

	@Column(name = "t_1")
	private String t1;

	@Column(name = "t_2")
	private String t2;

	@Column(name = "t_3")
	private String t3;
}
