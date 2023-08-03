package tw.idv.tibame.pet.vo;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "PET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private Integer petId;

	@Column(name = "pet_uid")
	private Integer petUid;

	@Column(name = "pet_type")
	private Integer petType;

	@Column(name = "pet_name")
	private String petName;

	@Column(name = "pet_breed")
	private String petBreed;

	@Column(name = "pet_age")
	private Integer petAge;

	@Lob
	@Column(name = "pet_pic")
	private byte[] petPic;

	@Column(name = "pet_sex")
	private String petSex;

	@Column(name = "pet_person")
	private Integer petPerson;
	


	
	
}
