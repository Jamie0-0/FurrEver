package tw.idv.tibame.product_fe.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import tw.idv.tibame.product_fe.service.ProductService;

@WebServlet("/deleteFromCart")
public class DeleteFromCartController extends HttpServlet {

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
		
		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");

		if (username != null) {
			uid = (int) session.getAttribute("uid");
			cartList = service.getCartListMapForMember(cartList, uid);
		}

		int p_id = Integer.parseInt(req.getParameter("p_id"));

		System.out.println("delete的p_id = " + p_id);
		System.out.println("delete之前的原本的CartList =" + cartList);

		HashMap<Integer, Integer> newCartList = service.deleteProductInCart(p_id, cartList);
		req.getSession().setAttribute("cartList", newCartList);
		
		
		if (username != null) {
			service.deleteCartItemFromRedis(newCartList, uid, p_id);
		}

		int subtotal = service.getCartSubTotal(cartList);
		
		String message = "{\"cartlist\":" + gson.toJson(newCartList)
		+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}"; // 運費固定120

		resp.getWriter().write(message);

		System.out.println(message);

	}

}
