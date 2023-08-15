package tw.idv.tibame.product.vo;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	@JsonProperty("pId")
	private int pId;

	@Column(name = "p_m_id")
	private int pMId;

	@Column(name = "p_name", length = 40)
	private String pName;

	@Column(name = "p_price")
	private int pPrice;

	@Column(name = "p_stock")
	private int pStock;

	@Column(name = "p_count")
	private Integer pCount;

	@Column(name = "p_type")
	private int pType;

	@Column(name = "p_class")
	private int pClass;

	@Column(name = "p_upload_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp pUploadTime;

	@Column(name = "p_des")
	private String pDes;

	@Column(name = "p_status")
	private int pStatus;

	@Column(name = "p_pic_one")
	private byte[] pPicOne;

	@Column(name = "p_pic_two")
	private byte[] pPicTwo;

	@Column(name = "p_pic_three")
	private byte[] pPicThree;

	@Column(name = "p_pic_four")
	private byte[] pPicFour;

	@Column(name = "p_1")
	private String p1 = "product";

	@Column(name = "p_2")
	private String p2;

	@Column(name = "p_3")
	private String p3;

	@OneToMany(mappedBy = "product")
	@JsonIgnoreProperties("product")
//	@JsonBackReference
	private List<ProductLike> productLikes;
	

}
