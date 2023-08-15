package tw.idv.tibame.myproduct.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.tibame.myOrders.vo.MyGb;
import tw.idv.tibame.myOrders.vo.MySubProduct;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyProduct implements Serializable{

	private static final long serialVersionUID = 1L;

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
	private List<MyProductLike> productLikes;
	
	@OneToMany
	@JoinColumn(name = "order_id", referencedColumnName = "p_id")
	@JsonManagedReference
	private List<MySubProduct> subProducts;
	
//	@OneToOne
//	@JoinColumn(name = "p_id", insertable = false, updatable = false)
//	private Gb gb;
	
	

}
