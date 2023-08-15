package tw.idv.tibame.myOrders.vo;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.tibame.myproduct.vo.MyProduct;

@Entity
@Table(name = "GB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyGb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gb_id")
	private Integer gbId;

	@Column(name = "gb_p_id")
	private Integer gbPId;
	

	@Column(name = "gb_s_price")
	private Integer gbSPrice;

	@Column(name = "gb_c_max")
	private Integer gbCMax;

	@Column(name = "gb_time_start")
	private Timestamp gbTimeStart;

	@Column(name = "gb_time_end")
	private Timestamp gbTimeEnd;

	@Column(name = "gb_satus")
	private Integer gbStatus;

	@Column(name = "gb1")
	private String gb1;

	@Column(name = "gb2")
	private String gb2;

	@Column(name = "gb3")
	private String gb3;
	
	@OneToMany
	@JsonManagedReference
	@JoinColumn(name = "gb_order_id", referencedColumnName = "gb_id")
	private List<MyGBOrder> gbOrders;
	
	@OneToOne
	@JoinColumn(name = "gb_p_id", insertable = false, updatable = false)
	private MyProduct product;
}
