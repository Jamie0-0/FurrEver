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

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import tw.idv.tibame.product_fe.dao.ProductUserDao;
import tw.idv.tibame.product_fe.dao.ProductUserDaoImpl;
import tw.idv.tibame.product_fe.service.ProductService;
import tw.idv.tibame.product_fe.service.ProductServiceImpl;
import tw.idv.tibame.product_fe.vo.ProductUser;

@WebServlet("/cartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService service;
	private ProductUserDao productUserDao;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
		productUserDao = new ProductUserDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();

		String username = (String) session.getAttribute("uName");
		int uid = 0;
		ProductUser productUser = new ProductUser();

		HashMap<Integer, Integer> cartList = null;

		if (username == null) {
			cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
		} else if (username != null) {
			productUser = productUserDao.selectByUserNameForCart(username);
			uid = (int) session.getAttribute("uid");
			cartList = service.getCartListMapForMember(session, uid);
		}

		JsonArray cartArray = service.getCartListJSON(cartList);
		// 若購物車是空的, 傳給前端訊息並結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			System.out.println(gson.toJson(msgs));
			return;
		}

		int subtotal = service.getCartSubTotal(cartList);

		String message = "{\"user\":" + gson.toJson(productUser) + ",\"cartList\":" + gson.toJson(cartArray)
				+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}";
		resp.getWriter().write(message);
		System.out.println(message);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
