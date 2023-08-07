package tw.idv.tibame.product.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_like")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductLikeId.class)
public class ProductLike {

	@Id
	@Column(name = "pl_uid")
	private Integer plUid;
	
	@Id
	@Column(name = "pl_p_id")
	private Integer plPId;
	
	@ManyToOne
	@JsonIgnore
//	@JsonManagedReference
	@JoinColumn(name = "pId", insertable = false, updatable = false)
	private Product product;

}