package tw.idv.tibame.mytrip.vo;

import java.io.Serializable;

import javax.persistence.Id;

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
public class MyActLikeId implements Serializable {

    private static final long serialVersionUID = 1311253558836476902L;

    @Id
	private Integer tActId;

	@Id
	private Integer uid;


}
