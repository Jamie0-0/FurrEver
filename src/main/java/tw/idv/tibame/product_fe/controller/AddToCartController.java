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

@WebServlet("/addToCart")
public class AddToCartController extends HttpServlet {
	@Autowired
	private ProductService service;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		String p_id_string = req.getParameter("p_id");
		String quantity_string = req.getParameter("quantity");
		System.out.println("add之前的原本的CartList =" + cartList);

		cartList = service.addToCart(p_id_string, quantity_string, cartList);
		req.getSession().setAttribute("cartList", cartList);
		
		if (username != null) {
			service.saveCartToRedis(cartList, uid);
		}

		// 檢查欲增加數量有沒有大於商品庫存數量, 有的話直接回傳錯誤訊息就結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			return;
		}

		msgs.add("已成功加入購物車");
		resp.getWriter().write(gson.toJson(msgs)); // 回傳成功文字

		String newCartList = gson.toJson(session.getAttribute("cartList"));

		String message = "{\"msgs\":" + gson.toJson(msgs) + ",\"cartlist\":" + gson.toJson(cartList) + "}";
		System.out.println(message);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
