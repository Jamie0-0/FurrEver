package tw.idv.tibame.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import tw.idv.tibame.order.service.ProductOrderService;
import tw.idv.tibame.order.service.ProductOrdersServiceImpl;
import tw.idv.tibame.order.vo.Orders;

@WebServlet("/checkoutCart")
public class CheckoutCartController extends HttpServlet {
	@Autowired
	private ProductOrderService service;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		Gson gson = new Gson();

		Orders orders = gson.fromJson(req.getReader(), Orders.class);
		boolean orderIsEstabished = service.createOrders(orders);

		if (orderIsEstabished == true) {
//			////// session版 購物車清空
//			HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
//			cartList.clear();

			////// redis版 購物車清空
			service.deleteCartFromRedis(session);
		}

		String message = "{\"status\":" + gson.toJson(orderIsEstabished) + ",\"msgs\":" + gson.toJson(service.getMsgs())
				+ "}";
		;
		resp.getWriter().write(message);
		System.out.println(service.getMsgs());
		System.out.println(message);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
