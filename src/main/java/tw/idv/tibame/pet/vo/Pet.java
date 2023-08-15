package tw.idv.tibame.pet.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
