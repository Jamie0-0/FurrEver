package tw.idv.tibame.articles.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "com_report")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComReport {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crepId;
	
	private Integer crepComId;
	
	private Integer crepUserId;
	
	private String crepReason;
	@Transient
	private Timestamp crepTime;
	@Transient
	private String crepStatus;
	
	
	@ManyToOne
	@JoinColumn(name = "crepComId", insertable = false, updatable = false)
	@JsonBackReference
	private Comment comment;

}
