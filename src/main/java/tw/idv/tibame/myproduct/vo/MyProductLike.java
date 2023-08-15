package tw.idv.tibame.myproduct.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@IdClass(MyProductLikeId.class)
public class MyProductLike {

	@Id
	@Column(name = "pl_uid")
	private Integer plUid;
	
	@Id
	@Column(name = "pl_p_id")
	private Integer plPId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "pl_p_id", insertable = false, updatable = false)
	private MyProduct product;

}