package tw.idv.tibame.user.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	@JsonProperty("uId")
	private Integer uid;

	@Column(name = "u_email")
	@JsonProperty("uEmail")
	private String uEmail;

	@Column(name = "u_name")
	@JsonProperty("uName")
	private String uName;

	@Column(name = "u_pwd")
	@JsonProperty("uPwd")
	private String uPwd;

	@Column(name = "u_phone")
	@JsonProperty("uPhone")
	private String uPhone;

	@Column(name = "u_address")
	@JsonProperty("uAddress")
	private String uAddress;

	@Column(name = "u_birth")
	@JsonProperty("uBirth")
	private Date uBirth;

	@Column(name = "u_gender")
	@JsonProperty("uGender")
	private String uGender;

	@Transient
	@Column(name = "u_reg", insertable = false)
	private Timestamp uReg;

	@Lob
	@Column(name = "u_pic")
	private byte[] uPic;

	@Column(name = "u_report")
	private Integer uReport;

	@Column(name = "u_status",columnDefinition = "INT default 1")
	@Transient
	private Integer uStatus;

	@Transient
	@Column(name = "gm_id")
	private Integer gmId;

	@Transient
	@Column(name = "gm_date")
	private Timestamp gmDate;

	@Column(name = "u_about")
	@JsonProperty("uAbout")
	private String uAbout;

	@Transient
	@Column(name = "u_2")
	private String u2;

	@Transient
	@Column(name = "u_3")
	private String u3;


}
