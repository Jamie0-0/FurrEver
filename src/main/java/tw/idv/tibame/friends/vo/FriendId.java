package tw.idv.tibame.friends.vo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FriendId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer fUid;

	private Integer fId;
}
