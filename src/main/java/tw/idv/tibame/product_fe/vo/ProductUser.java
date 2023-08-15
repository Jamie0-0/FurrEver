package tw.idv.tibame.product_fe.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class ProductUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer uid;
	@Transient
	private String u_phone;
	@Column
	private String u_name;
	@Transient
	private String u_pwd;
	@Transient
	private String u_email;
	@Transient
	private String u_address;
	@Transient
	private Date u_birth;
	@Transient
	private String u_gender;
	@Transient
	private Timestamp u_reg;
	@Transient
	private byte[] u_pic;
	@Transient
	private Integer u_report;
	@Transient
	private String u_status;
	@Transient
	private Integer gm_ID;
	@Transient
	private Timestamp gm_date;

//	@Transient
//	private String u_1;
//	@Transient
//	private String u_2;
//	@Transient
//	private String u_3;
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public Date getU_birth() {
		return u_birth;
	}

	public void setU_birth(Date u_birth) {
		this.u_birth = u_birth;
	}

	public String getU_gender() {
		return u_gender;
	}

	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public Timestamp getU_reg() {
		return u_reg;
	}

	public void setU_reg(Timestamp u_reg) {
		this.u_reg = u_reg;
	}

	public byte[] getU_pic() {
		return u_pic;
	}

	public void setU_pic(byte[] u_pic) {
		this.u_pic = u_pic;
	}

	public Integer getU_report() {
		return u_report;
	}

	public void setU_report(Integer u_report) {
		this.u_report = u_report;
	}

	public String getU_status() {
		return u_status;
	}

	public void setU_status(String u_status) {
		this.u_status = u_status;
	}

	public Integer getGm_ID() {
		return gm_ID;
	}

	public void setGm_ID(Integer gm_ID) {
		this.gm_ID = gm_ID;
	}

	public Timestamp getGm_date() {
		return gm_date;
	}

	public void setGm_date(Timestamp gm_date) {
		this.gm_date = gm_date;
	}

//	@OneToMany
//	@JoinColumn(name = "pet_uid",
//			referencedColumnName = "uid")
//	private List<Pet> pets;

}
