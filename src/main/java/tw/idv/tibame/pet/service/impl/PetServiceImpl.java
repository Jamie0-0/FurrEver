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
		return petDao.save(pet);
	}

	@Override
	public List<Pet> findAllByPetUid(Integer uid) {

		return petDao.findByPetUid(uid);
	}

}
