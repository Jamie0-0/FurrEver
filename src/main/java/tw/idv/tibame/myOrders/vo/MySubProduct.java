package tw.idv.tibame.myOrders.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.tibame.myproduct.vo.MyProduct;

@Entity
@Table(name = "sub_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MySubProductPK.class)
public class MySubProduct {

	@Id
    @Column(name = "order_id")
    private Integer orderId;

    @Id
    @Column(name = "p_p_id")
    private Integer pPId;

    @Column(name = "p_m_stock")
    private Integer pMStock;

    @Column(name = "p_m_price")
    private Integer pMPrice;

    @Column(name = "p_m_name")
    private String pMName;

    @Column(name = "sub2")
    private String sub2;

    @Column(name = "sub3")
    private String sub3;
    
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    private MySubOrder subOrder;
//
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "p_p_id", insertable = false, updatable = false)
//    private MyProduct product;
}
