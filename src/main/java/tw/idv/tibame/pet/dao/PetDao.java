package tw.idv.tibame.pet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tw.idv.tibame.pet.vo.Pet;
import tw.idv.tibame.pet.vo.PetSex;

public interface PetDao extends CrudRepository<Pet, Integer>{
	

	@Query(value = "insert into PET (pet_type =?1, pet_name =?2, pet_breed =?3, pet_age =?4, pet_pic =?5, pet_sex =?6, pet_person =?7 where pet_uid =?8", nativeQuery = true)
	Integer insertByUid(@Param("petType") Integer petType, @Param("petName") String petName, @Param("petBreed") String petBreed, @Param("petAge") Integer petAge, @Param("petPic") byte[] petPic, @Param("petSex") PetSex petSex, @Param("petPerson") Integer petPerson, @Param("petUid") Integer petUid);

	@Query(value = "select pet_id,pet_uid, pet_type, pet_name, pet_breed, pet_age, pet_pic, pet_sex, pet_person from PET where pet_uid= ?1", nativeQuery = true)
	List<Pet> findByPetUid(@Param("petUid") Integer petUid);
}
