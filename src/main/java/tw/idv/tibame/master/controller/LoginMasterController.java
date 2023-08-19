package tw.idv.tibame.master.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.master.service.MasterService;
import tw.idv.tibame.master.vo.Master;

@RestController
@RequestMapping("loginMaster")
public class LoginMasterController {

	@Autowired
	private MasterService masterService;

	@GetMapping("/{mEmail}/{mPwd}")
	public ResponseEntity<?> login(HttpSession session, @PathVariable String mEmail, @PathVariable String mPwd) {
		Integer loginMaster = masterService.login(mEmail, mPwd);
		String companyName = "";
		Map<String, Object> response = new HashMap<>();
		
		Master masterlist = masterService.findMasterName(mEmail);
		System.out.println(masterlist);
		
		if(masterlist != null) {
			 companyName = masterlist.getMName();			
		}
		else {
			return new ResponseEntity<>("查無帳號或密碼錯誤", HttpStatus.BAD_REQUEST);
		}
		
		
		if (loginMaster == null) {
			return new ResponseEntity<>("查無帳號或密碼錯誤", HttpStatus.BAD_REQUEST);
		}
		session.setAttribute("mid", loginMaster);
		String location = (String) session.getAttribute("location");

		if (location == null || location.isBlank()) {
			location = "";
		}

		session.removeAttribute("location");

		
		response.put("status", "success");
		response.put("Masterid", loginMaster);
		response.put("companyName", companyName);
		response.put("location", location);
		return ResponseEntity.ok(response);
	}
}
