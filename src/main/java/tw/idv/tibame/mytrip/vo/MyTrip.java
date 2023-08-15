package tw.idv.tibame.mytrip.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class MyTrip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_act_id")
	private Integer tActId;

	@Column(name = "uid")
	private Integer uid;
	
	@Transient
	@Column(name = "t_act_type")
	private Integer tActType;

	@Column(name = "t_act_name")
	private String tActName;
	
	@Transient
	@Column(name = "t_act_desc")
	@Lob
	private String tActDesc;

	@Transient
	@Column(name = "t_act_city")
	private Integer tActCity;

	@Transient
	@Column(name = "t_act_loc")
	private String tActLoc;

	@Column(name = "t_act_time")
	private LocalDateTime tActTime;

	@Column(name = "t_act_ppl")
	private Integer tActPpl;

	@Transient
	@Column(name = "t_act_pic_one")
	@Lob
	private byte[] tActPicOne;

	@Transient
	@Column(name = "t_act_pic_two")
	@Lob
	private byte[] tActPicTwo;

	@Transient
	@Column(name = "t_act_bdg")
	private Integer tActBdg;

	@Column(name = "t_act_status")
	private String tActStatus;

	@Transient
	@Column(name = "gm_id")
	private Integer gmId;

	@Transient
	@Column(name = "gm_date")
	private LocalDateTime gmDate;

	
	@Column(name = "t_1")
	private String t1 = "activity";

	@Transient
	@Column(name = "t_2")
	private String t2;
	
	@Transient
	@Column(name = "t_3")
	private String t3;
	
	@Column(name = "count")
	private Integer count;
	
	@OneToMany(mappedBy = "participants")
	@JsonIgnoreProperties("likeactivity")
    private List<MyParticipant> participants;

    @OneToMany(mappedBy = "likeactivity")
    @JsonIgnoreProperties("participants")
    private List<MyActLike> actLikes;
}
