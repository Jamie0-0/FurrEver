package tw.idv.tibame.master.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("logoutMaster")
public class LogoutMasterController {

	@PostMapping
    public ResponseEntity<String> logout(HttpSession session) {
        // 清除 Session 中的用戶相關資訊
        session.invalidate();
        return new ResponseEntity<>("登出成功", HttpStatus.OK);
    }
}
