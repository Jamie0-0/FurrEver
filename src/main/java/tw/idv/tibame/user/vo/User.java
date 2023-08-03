package tw.idv.tibame.user.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

	@Column(name = "gm_id")
	private Integer gmId;

	@Column(name = "gm_date")
	private Timestamp gmDate;

	@Column(name = "u_about")
	@JsonProperty("uAbout")
	private String uAbout;

	@Column(name = "u_2")
	private String u2;

	@Column(name = "u_3")
	private String u3;

}
