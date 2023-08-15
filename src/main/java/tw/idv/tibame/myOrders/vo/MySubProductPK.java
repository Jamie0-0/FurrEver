package tw.idv.tibame.myOrders.vo;

import java.io.Serializable;

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
public class MySubProductPK implements Serializable{

	private static final long serialVersionUID = -4231208841044119361L;
	
	public Integer orderId;
    public Integer pPId;
}
