package tw.idv.tibame.friends.vo;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "friend")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FriendId.class)
@Builder
public class Friend {

	@Id
	private Integer fUid;
	@Id
	private Integer fId;

	private String fStatus;
	@Column(insertable = false, updatable = false)
	private Timestamp fDate;
}