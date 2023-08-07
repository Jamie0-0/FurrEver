package tw.idv.tibame.product.vo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductLikeId implements Serializable{

	private static final long serialVersionUID = -4775503562459412482L;


	public Integer plUid;
	

	public Integer plPId;

}
