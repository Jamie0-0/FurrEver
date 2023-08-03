package tw.idv.tibame.pet.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.pet.service.PetService;
import tw.idv.tibame.pet.vo.Pet;

@RestController
@RequestMapping("addPet")
public class AddPetController {
	
	@Autowired
	private PetService petService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody List<Pet> petlist) {
		for (Pet pet2 : petlist) {
			Pet result = petService.add(pet2);
			if(result != null) {
				return ResponseEntity.ok(result);
			}		
		}
	
		return new ResponseEntity<>("錯誤", HttpStatus.BAD_REQUEST);
	}
}
