package tw.idv.tibame.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.master.service.MasterService;
import tw.idv.tibame.master.vo.Master;

@RestController
@RequestMapping("registerMaster")
public class RegisterMasterController {

	@Autowired
	private MasterService masterService;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody  Master master) {
		Master registeredMaster = masterService.register(master);
		if(registeredMaster == null) {
			return new ResponseEntity<>("帳號重複", HttpStatus.BAD_REQUEST);
		}
	    return ResponseEntity.ok(registeredMaster);
	}
}
