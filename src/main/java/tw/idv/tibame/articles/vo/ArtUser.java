package tw.idv.tibame.articles.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", catalog = "FURREVER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtUser extends Core {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer uid;
//	@Transient
//	private String  uPhone;

	private String uName;
//	@Transient
//	private String uPwd;
//	@Transient
//	private String uEmail;
//	@Transient
//	private String uAddress;
//	@Transient
//	private Date uBirth;
//	@Transient
//	private String uGender;
//	@Transient
//	private Timestamp uReg;
//	@Column
//	private byte[] uPic;
//	@Transient
//	private Integer uReport;
//	@Transient
//	private String uStatus;
//	@Transient
//	private Integer gmID;
//	@Transient
//	private Timestamp gmDate;
//	@Transient
//	private String u1;
//	@Transient
//	private String u2;
//	@Transient
//	private String u3;

	@OneToMany(mappedBy = "artUser")
	@JsonBackReference
	private List<Article> articles;

}
