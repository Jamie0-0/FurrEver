package tw.idv.tibame.pet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.pet.service.PetService;
import tw.idv.tibame.pet.vo.Pet;

@RestController
@RequestMapping("findPet")
public class FindPetController {

	@Autowired
	private PetService petService;

	@PostMapping
	public ResponseEntity<?> findPet(HttpSession session) {
		Integer uid = (Integer) session.getAttribute("uid");
		System.out.println(uid);
		if (uid != null) {
			List<Pet> petlist = petService.findAllByPetUid(uid);
			System.out.println("123");
			return ResponseEntity.ok(petlist);

		} else {
			return new ResponseEntity<>("查無會員資料", HttpStatus.BAD_REQUEST);
		}
	}
}
