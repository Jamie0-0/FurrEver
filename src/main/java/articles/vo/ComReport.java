package articles.vo;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "com_report", catalog = "FurrEver")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComReport {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crep_id;
	
	private Integer crep_com_id;
	
	private Integer crep_user_id;
	
	private String crep_reason;
	@Transient
	private Timestamp crep_time;
	@Transient
	private String crep_status;

}
