package tw.idv.tibame.product_fe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import tw.idv.tibame.product_fe.service.ProductService;
import tw.idv.tibame.product_fe.service.ProductServiceImpl;

@WebServlet("/updateCart")
public class UpdateCartController extends HttpServlet {
	@Autowired
	private ProductService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();

		String username = (String) session.getAttribute("uName");
		int uid = 0;

		HashMap<Integer, Integer> cartList = null;

		if (username == null) {
			cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
		} else if (username != null) {
			uid = (int) session.getAttribute("uid");
			cartList = service.getCartListMapForMember(session, uid);
		}

		int p_id = Integer.parseInt(req.getParameter("p_id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		System.out.println("update的p_id = " + p_id);
		System.out.println("update的quantity = " + quantity);

		System.out.println("update之前的原本的CartList =" + cartList);
		service.updateCart(req, p_id, quantity, cartList);

		if (username != null) {
			service.saveCartToRedis(session, uid);
		}

		// 檢查欲增加數量有沒有大於商品庫存數量, 有的話直接回傳錯誤訊息就結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			return;
		}

		int subtotal = service.getCartSubTotal(cartList);
		String newCartList = gson.toJson(session.getAttribute("cartList"));

		String message = "{\"status\":\"true\",\"cartlist\":" + gson.toJson(newCartList) + ",\"subtotal\":" + subtotal
				+ ",\"total\":" + (subtotal + 120) + "}"; // 運費固定120

		resp.getWriter().write(message);

		System.out.println(message);

		System.out.println("====== 購物車數量已update ========");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
