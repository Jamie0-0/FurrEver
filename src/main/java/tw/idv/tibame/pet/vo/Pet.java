package tw.idv.tibame.pet.vo;


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
	@JsonProperty("petId")
	private Integer petId;

	@Column(name = "pet_uid")
	@JsonProperty("petUid")
	private Integer petUid;

	@Column(name = "pet_type")
	@JsonProperty("petType")
	private Integer petType;

	@Column(name = "pet_name")
	@JsonProperty("petName")
	private String petName;

	@Column(name = "pet_breed")
	@JsonProperty("petBreed")
	private String petBreed;

	@Column(name = "pet_age")
	@JsonProperty("petAge")
	private Integer petAge;

	@Lob
	@Column(name = "pet_pic")
	@JsonProperty("petPic")
	private byte[] petPic;

	@Column(name = "pet_sex")
	@JsonProperty("petSex")
	private String petSex;

	@Column(name = "pet_person")
	@JsonProperty("petPerson")
	private Integer petPerson;
	


	
	
}
