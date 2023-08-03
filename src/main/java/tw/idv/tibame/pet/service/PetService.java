package tw.idv.tibame.pet.service;

import java.util.List;

import tw.idv.tibame.pet.vo.Pet;

public interface PetService {
	
	Pet add(Pet pet);
	
	List<Pet> findAllByPetUid(Integer uid);
}
