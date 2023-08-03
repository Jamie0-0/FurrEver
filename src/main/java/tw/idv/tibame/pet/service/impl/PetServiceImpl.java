package tw.idv.tibame.pet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.idv.tibame.pet.dao.PetDao;
import tw.idv.tibame.pet.service.PetService;
import tw.idv.tibame.pet.vo.Pet;

@Component
public class PetServiceImpl implements PetService {

	@Autowired
	private PetDao petDao;

	@Override
	public Pet add(Pet pet) {
//		Integer petType = pet.getPetType();
//		Integer petAge = pet.getPetAge();
//		Integer petPerson = pet.getPetPerson();
//		String petBreed = pet.getPetBreed();
//		String petName = pet.getPetName();
//		String petSex = pet.getPetSex();
//		byte[] petPic = pet.getPetPic();
//		Integer petUid = pet.getPetUid();
		return petDao.save(pet);
	}

	@Override
	public List<Pet> findAllByPetUid(Integer uid) {

		return petDao.findByPetUid(uid);
	}

}
