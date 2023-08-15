package tw.idv.tibame.myOrders.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.tibame.myOrders.service.MyOrderService;
import tw.idv.tibame.myOrders.vo.MyGb;
import tw.idv.tibame.myOrders.vo.MyProductOrder;



@RestController
public class MyOrderController {

	@Autowired
	private MyOrderService myOrderService;
	
	@PostMapping("myorder")
	public List<MyProductOrder> productOrders(HttpSession session) {
		Integer uid = (Integer) session.getAttribute("uid");
		return myOrderService.findAll(uid);
	}
	
	@PostMapping("mygborder")
	public List<MyGb> gbOrders(HttpSession session){
		Integer uid = (Integer) session.getAttribute("uid");
		return myOrderService.finGbOrder(uid);
	}
}
