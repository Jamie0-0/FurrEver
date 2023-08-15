package tw.idv.tibame.pet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tw.idv.tibame.pet.vo.Pet;

public interface PetDao extends CrudRepository<Pet, Integer>{
	

	@Modifying
	@Query(value = "INSERT INTO PET (pet_type, pet_name, pet_breed, pet_age, pet_pic, pet_sex, pet_person, pet_uid) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	Integer insertByUid( Integer petType,  String petName,  String petBreed,  Integer petAge,  byte[] petPic,  String petSex, Integer petPerson,  Integer petUid);


	@Query(value = "select pet_id,pet_uid, pet_type, pet_name, pet_breed, pet_age, pet_pic, pet_sex, pet_person from PET where pet_uid= ?1", nativeQuery = true)
	List<Pet> findByPetUid(@Param("petUid") Integer petUid);
}
