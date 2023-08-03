package tw.idv.tibame.master.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MASTER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Master {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	@JsonProperty("mId")
	private Integer mid;

	@Column(name = "m_name")
	@JsonProperty("mName")
	private String mName;

	@Column(name = "m_pwd")
	@JsonProperty("mPwd")
	private String mPwd;

	@Column(name = "m_gui")
	@JsonProperty("mGui")
	private String mGui;

	@Column(name = "m_bank_name")
	@JsonProperty("mBankName")
	private Integer mBankName;

	@Column(name = "m_bank_id")
	@JsonProperty("mBankId")
	private String mBankId;

	@Column(name = "m_address")
	@JsonProperty("mAddress")
	private String mAddress;

	@Column(name = "m_reg", insertable = false)
	private Timestamp mReg;

	@Column(name = "m_man_id")
	@JsonProperty("mManId")
	private String mManId;

	@Column(name = "m_man_name")
	@JsonProperty("mManName")
	private String mManName;

	@Column(name = "m_email")
	@JsonProperty("mEmail")
	private String mEmail;

	@Column(name = "m_phone")
	@JsonProperty("mPhone")
	private String mPhone;

	@Column(name = "m_status", insertable = false)
	private String mStatus;

	@Column(name = "gm_id")
	private Integer gmId;

	@Column(name = "gm_date")
	private Timestamp gmDate;

	@Column(name = "m_1")
	private String m1;

	@Column(name = "m_2")
	private String m2;

	@Column(name = "m_3")
	private String m3;
}
