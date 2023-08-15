package tw.idv.tibame.myOrders.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.tibame.user.vo.User;

@Entity
@Table(name = "gb_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyGBOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gb_id")
	private Integer gbId;

	@Column(name = "uid")
	private Integer uId;

	@Column(name = "gb_date")
	private Date gbDate;

	@Column(name = "gb_t")
	private Integer gbT;

	@Column(name = "gb_s")
	private String gbS;

	@Column(name = "gb_pay")
	private String gbPay;

	@Column(name = "gb_p_num")
	private Integer gbPNum;

	@Column(name = "gb_p_dfee")
	private Integer gbPDfee;

	@Column(name = "gb_1")
	private String gb1;

	@Column(name = "gb_2")
	private String gb2;

	@Column(name = "gb_3")
	private String gb3;
	
	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "gb_order_id", insertable = false, updatable = false)
    private MyGBOrder gb;
}
