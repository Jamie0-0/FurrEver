package tw.idv.tibame.trip.vo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
public class ParticipantId implements Serializable {

	private static final long serialVersionUID = 1311253558836476902L;
	
	@Id
	private Integer tActId;

	@Id
	private Integer uid;

}
